package org.opus.explore.spring.springsecurityauthorization.config;

import lombok.extern.slf4j.*;
import org.opus.explore.spring.springsecurityauthorization.model.*;
import org.opus.explore.spring.springsecurityauthorization.service.*;
import org.springframework.context.annotation.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.method.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.provisioning.*;
import org.springframework.security.web.*;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableMethodSecurity
@Slf4j
public class SecurityConfiguration {

    private CustomerService customerService;

    public SecurityConfiguration(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

        String password = passwordEncoder().encode("password");

        // Customer users
        customerService.getAllCustomers().forEach(
                customer -> userDetailsManager.createUser(
                        User.withUsername(customer.getUsername()).password(password).authorities("CUSTOMER").build()));

        // Admin users
        userDetailsManager.createUser(
                User.withUsername("super-admin").password(password).authorities("CUSTOMER_ADMIN").build());
        userDetailsManager.createUser(
                User.withUsername("admin").password(password).authorities("CUSTOMER_MANAGER").build());

        return userDetailsManager;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        http.authorizeRequests(authorizeRequestsCustomizer -> authorizeRequestsCustomizer
            .requestMatchers(GET, "/api/customers").hasAnyAuthority("CUSTOMER_ADMIN", "CUSTOMER_MANAGER")
            .requestMatchers(POST, "/api/customers").hasAuthority("CUSTOMER_ADMIN")

            .requestMatchers(GET, "/api/customers/{id}")
                .access("hasAnyAuthority('CUSTOMER_ADMIN', 'CUSTOMER_MANAGER') " +
                    "or #id == @customerService.getCustomerByUsername(authentication.name).id.toString()")
            .requestMatchers(DELETE, "/api/customers/{id}").hasAuthority("CUSTOMER_ADMIN")
            .requestMatchers(PUT, "/api/customers/{id}").hasAnyAuthority("CUSTOMER_ADMIN", "CUSTOMER_MANAGER")

            .requestMatchers("/api/customers/faq").permitAll()
            .requestMatchers("/error").permitAll()

            .anyRequest().authenticated());

        // @formatter:on
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"))
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}

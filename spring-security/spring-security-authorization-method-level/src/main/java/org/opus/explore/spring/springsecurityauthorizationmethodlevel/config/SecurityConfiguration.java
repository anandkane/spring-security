package org.opus.explore.spring.springsecurityauthorizationmethodlevel.config;

import lombok.extern.slf4j.*;
import org.opus.explore.spring.springsecurityauthorizationmethodlevel.model.*;
import org.opus.explore.spring.springsecurityauthorizationmethodlevel.service.*;
import org.springframework.context.annotation.*;
import org.springframework.security.config.*;
import org.springframework.security.config.annotation.method.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.provisioning.*;
import org.springframework.security.web.*;

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
        http.authorizeRequests(authorizeRequests -> authorizeRequests
            .requestMatchers("/error").permitAll()
            .anyRequest().authenticated());

        return http
            .csrf(csrfConfigurer -> csrfConfigurer.ignoringRequestMatchers("/api/**"))
            .formLogin(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults()).build();
        // @formatter:on
    }
}

package org.opus.explore.spring.springsecurityoauthcustomerservice;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.web.*;

@Configuration
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        // formatter:off
        return security
                .csrf(configurer -> configurer.ignoringRequestMatchers("/api/**"))
                .authorizeHttpRequests(reqCustomizer -> reqCustomizer
                        .requestMatchers("/api/customers/faq").permitAll()
                        .requestMatchers("/error").permitAll()
                        .anyRequest().authenticated()
                )
                .build();
        // formatter:on
    }
}

package org.opus.explore.spring.springsecurityconfiguserservice.config;

import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.authentication.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.provisioning.*;

import java.util.*;

@Configuration
@Slf4j
public class SecurityConfiguration {

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                log.info("Loading user: {}", username);
                return super.loadUserByUsername(username);
            }
        };
        userDetailsManager.createUser(User.withUsername("admin").password(passwordEncoder().encode("admin")).build());
        userDetailsManager.createUser(User.withUsername("user1").password(passwordEncoder().encode("password")).build());
        userDetailsManager.createUser(User.withUsername("user2").password(passwordEncoder().encode("changeme")).build());
        return userDetailsManager;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

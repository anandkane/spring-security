package org.opus.explore.spring.springsecurityconfigauthprovider.config;

import lombok.extern.slf4j.*;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.*;
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
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                log.info("Loading user: {}", username);
                return super.loadUserByUsername(username);
            }
        };
        userDetailsManager.createUser(User.withUsername("admin").password(passwordEncoder().encode("admin")).build());
        userDetailsManager.createUser(
                User.withUsername("user1").password(passwordEncoder().encode("password")).build());
        userDetailsManager.createUser(
                User.withUsername("user2").password(passwordEncoder().encode("changeme")).build());
        return userDetailsManager;
    }

    //    @Bean
    AuthenticationProvider authenticationProvider() {
        return new AuthenticationProvider() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                log.info("Authenticating user in custom authenticator: {}", authentication.getName());

                if (!"custom".equals(authentication.getName()))
                    throw new UsernameNotFoundException("Failed to find user: " + authentication.getName());

                // Does not make use of UserDetailsService. Directly validates the user instead.
                UserDetails userDetails = User.withUsername("custom").password(passwordEncoder().encode("custom"))
                        .build();
                if (passwordEncoder().matches((CharSequence) authentication.getCredentials(),
                                              userDetails.getPassword())) {
                    return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
                                                                   userDetails.getAuthorities());
                }
                throw new BadCredentialsException("Bad credentials");
            }

            @Override
            public boolean supports(Class<?> authentication) {
                log.info("Checking supports: {}", authentication.getName());
                return authentication.equals(UsernamePasswordAuthenticationToken.class);
            }
        };
    }

    DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Arrays.asList(daoAuthenticationProvider(), authenticationProvider()));
    }
}

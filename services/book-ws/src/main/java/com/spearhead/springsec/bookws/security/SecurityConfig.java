package com.spearhead.springsec.bookws.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                // add ant matchers here
                .antMatchers("/v1/books/{book-id}").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/v1/books/").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/v1/books/").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/v1/books").hasAuthority("ADMIN")
                .antMatchers("/v1/books/").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtAuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}

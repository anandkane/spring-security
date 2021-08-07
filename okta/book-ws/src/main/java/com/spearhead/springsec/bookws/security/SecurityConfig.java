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
                .antMatchers("/v1/books/{book-id}").hasAuthority("GET_BOOK")
                .antMatchers(HttpMethod.GET, "/v1/books").hasAuthority("GET_BOOKS")
                .antMatchers(HttpMethod.GET, "/v1/books/").hasAuthority("GET_BOOKS")
                .antMatchers("/v1/books").hasAuthority("CREATE_BOOK")
                .antMatchers("/v1/books/").hasAuthority("CREATE_BOOK")
                .anyRequest().authenticated()
                .and()
//                .addFilter(new JwtAuthorizationFilter(authenticationManager()))
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                .oauth2Login();
    }
}

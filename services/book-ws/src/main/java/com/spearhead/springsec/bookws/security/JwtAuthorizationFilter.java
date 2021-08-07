package com.spearhead.springsec.bookws.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || authHeader.trim().length() == 0 || !authHeader.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        authHeader = authHeader.replace("Bearer ", "");

        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512("mysecret")).build().verify(authHeader);
        if (decodedJWT != null) {
            String subject = decodedJWT.getSubject();
            if (subject != null) {
                Set<SimpleGrantedAuthority> roles = Arrays.stream(decodedJWT.getClaim("roles").asArray(String.class))
                        .map(s -> new SimpleGrantedAuthority(s)).collect(Collectors.toSet());
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(subject, null, roles);
                SecurityContextHolder.getContext().setAuthentication(token);
                chain.doFilter(request, response);
            }
        }
    }
}

package com.spearhead.springsecurity.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private UserDetailServiceImpl userDetailService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserDetailServiceImpl userDetailService) {
        super(authenticationManager);
        this.userDetailService = userDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || authHeader.trim().length() == 0 || !authHeader.startsWith("Bearer")) {
            super.doFilterInternal(request, response, chain);
        }

        String subject = JWT.require(Algorithm.HMAC512("mysecret")).build().verify(authHeader
                .replace("Bearer ", "")).getSubject();

        UserDetails userDetails = userDetailService.loadUserByUsername(subject);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(subject, null,
                userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);
        chain.doFilter(request, response);
    }
}

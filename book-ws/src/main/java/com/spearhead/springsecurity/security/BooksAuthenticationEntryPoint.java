package com.spearhead.springsecurity.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class BooksAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
//        response.addHeader("WWW-Authenticate", "Basic realm=\"" + getRealmName() + "\"");
//        response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid or missing user credentials");
        super.commence(request, response, authException);
    }

    @Override
    public void afterPropertiesSet() {
        setRealmName("my realm");
        super.afterPropertiesSet();
    }

}

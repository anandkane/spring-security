package com.spearhead.springsec.userws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class UserWsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserWsApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @RequestMapping("/v1/login")
    public String login(@AuthenticationPrincipal OAuth2User user) {
        return "User: " + user.getName() + "<br>" + "Authorities: " + user.getAuthorities();
    }
}

package org.opus.explore.spring.springsecurityconfiguserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping("/api/v1")
public class SpringSecurityConfigUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityConfigUserServiceApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello(@AuthenticationPrincipal UserDetails userDetails) {
        return String.format("Hello, %s!", userDetails.getUsername());
    }
}

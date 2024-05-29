package org.opus.explore.spring.springsecurityconfigauthprovider;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.security.core.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping("/api/v2")
public class SpringSecurityConfigAuthProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityConfigAuthProviderApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello(@AuthenticationPrincipal UserDetails user) {
        return String.format("Hello, %s!", user.getUsername());
    }
}

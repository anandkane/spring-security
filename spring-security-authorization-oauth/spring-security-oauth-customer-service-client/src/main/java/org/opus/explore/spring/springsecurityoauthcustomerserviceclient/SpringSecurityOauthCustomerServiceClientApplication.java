package org.opus.explore.spring.springsecurityoauthcustomerserviceclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping
@Configuration
public class SpringSecurityOauthCustomerServiceClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityOauthCustomerServiceClientApplication.class, args);
        System.exit(0);
    }

    @GetMapping("/api/oauth2/callback")
    public String printCode(@RequestParam String code) {
        System.out.println(code);

        return code;
    }

    @GetMapping("/api/customers")
    public void listCustomers() {

    }

    @Bean
    CommandLineRunner commandLineRunner(MainClass mainClass) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                try {
                    mainClass.getToken();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
    }
}

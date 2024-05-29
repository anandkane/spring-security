package org.opus.explore.spring.springsecurityoauthcustomerservice.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private long id;
    private String username;
    private String name;
    private String email;
}

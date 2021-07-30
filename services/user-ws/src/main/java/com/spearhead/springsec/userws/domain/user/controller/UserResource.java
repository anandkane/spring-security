package com.spearhead.springsec.userws.domain.user.controller;

import com.spearhead.springsec.userws.domain.user.model.User;
import com.spearhead.springsec.userws.domain.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserResource {

    private UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = "application/json")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "/{username}", produces = "application/json")
    public User getByUsername(String username) {
        return userService.getByUsername(username);
    }
}

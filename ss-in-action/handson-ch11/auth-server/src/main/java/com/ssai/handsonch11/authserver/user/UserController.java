package com.ssai.handsonch11.authserver.user;

import com.ssai.handsonch11.authserver.user.dao.entity.OtpEntity;
import com.ssai.handsonch11.authserver.user.dao.entity.UserEntity;
import com.ssai.handsonch11.authserver.user.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = "application/json")
    public void addUser(@RequestBody UserEntity user) {
        userService.addUser(user);
    }

    @PostMapping(path = "/auth", consumes = "application/json")
    public void auth(UserEntity user) {
        userService.auth(user);
    }

    @PostMapping(path = "/otp/validate")
    public void validate(@RequestBody OtpEntity otp, HttpServletResponse response) {
        response.setStatus(userService.validate(otp) ? OK.value() : FORBIDDEN.value());
    }
}

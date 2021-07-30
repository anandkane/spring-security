package com.spearhead.springsec.userws.domain.user.service;

import com.spearhead.springsec.userws.domain.user.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getByUsername(String username);
}

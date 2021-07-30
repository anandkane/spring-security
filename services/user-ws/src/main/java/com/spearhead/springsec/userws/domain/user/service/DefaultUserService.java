package com.spearhead.springsec.userws.domain.user.service;

import com.spearhead.springsec.userws.domain.user.entity.UserEntity;
import com.spearhead.springsec.userws.domain.user.model.User;
import com.spearhead.springsec.userws.domain.user.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DefaultUserService implements UserService {
    private UsersRepository usersRepository;

    public DefaultUserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<User> getAllUsers() {
        List<UserEntity> userEntities = usersRepository.findAll();
        if (userEntities != null) {
            List<User> users = new ArrayList<>(userEntities.size());
            userEntities.forEach(entity -> users.add(new User(entity)));

            return users;
        }

        return Collections.EMPTY_LIST;
    }

    @Override
    public User getByUsername(String username) {
        UserEntity entity = usersRepository.findByUsername(username);
        return entity != null ? new User(entity) : null;
    }
}

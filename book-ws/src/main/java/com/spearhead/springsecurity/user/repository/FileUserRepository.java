package com.spearhead.springsecurity.user.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spearhead.springsecurity.user.model.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ConditionalOnProperty(value = "user.storage", havingValue = "file")
public class FileUserRepository {
    private Map<String, User> usersMap = new ConcurrentHashMap<>();

    public FileUserRepository() {
        loadUsers();
    }

//    @Override
    public User findByUsername(String username) {
        return usersMap.get(username);
    }

    private void loadUsers() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = ResourceUtils.getFile("classpath:user-credentials.json");
            List<User> users = mapper.readValue(file, new TypeReference<List<User>>() {
            });

            users.forEach(user -> usersMap.put(user.getUsername(), user));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

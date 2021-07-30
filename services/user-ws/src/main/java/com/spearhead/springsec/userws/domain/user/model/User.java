package com.spearhead.springsec.userws.domain.user.model;

import com.spearhead.springsec.userws.domain.user.entity.AuthorityEntity;
import com.spearhead.springsec.userws.domain.user.entity.UserEntity;

import java.util.HashSet;
import java.util.Set;

public class User {

    private String username;
    private String password;
    private boolean enabled;
    private Set<Authority> authorities;

    public User() {
    }

    public User(UserEntity entity) {
        username = entity.getUsername();
        password = entity.getPassword();
        enabled = entity.isEnabled();

        Set<AuthorityEntity> authorityEntities = entity.getAuthorityEntities();
        if (authorityEntities != null) {
            authorities = new HashSet<>(authorityEntities.size());
            authorityEntities.forEach(entity1 -> {
                authorities.add(new Authority(entity1));
            });
        }
    }

    public User(String username, String password, boolean enabled, Set<Authority> authorities) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", authorities=" + authorities +
                '}';
    }
}

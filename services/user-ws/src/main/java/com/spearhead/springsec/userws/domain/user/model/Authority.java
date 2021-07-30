package com.spearhead.springsec.userws.domain.user.model;

import com.spearhead.springsec.userws.domain.user.entity.AuthorityEntity;

public class Authority {
    private int id;
    private String username;
    private String authority;

    public Authority() {
    }

    public Authority(AuthorityEntity entity) {
        id = entity.getId();
        username = entity.getUserEntity().getUsername();
        authority = entity.getAuthority();
    }

    public Authority(int id, String username, String authority) {
        this.id = id;
        this.username = username;
        this.authority = authority;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", authority='" + authority + '\'' +
                '}';
    }
}

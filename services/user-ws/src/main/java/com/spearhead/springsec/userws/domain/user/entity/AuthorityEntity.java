package com.spearhead.springsec.userws.domain.user.entity;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
public class AuthorityEntity {
    @Id
    @Column(name = "authority_id")
    private int id;
    @Column(name = "authority")
    private String authority;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "username")
    private UserEntity userEntity;

    public AuthorityEntity() {
    }

    public AuthorityEntity(int id, String authority, UserEntity userEntity) {
        this.id = id;
        this.authority = authority;
        this.userEntity = userEntity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public String toString() {
        return "AuthorityEntity{" +
                "id=" + id +
                ", authority='" + authority + '\'' +
                ", userEntity=" + userEntity.getUsername() +
                '}';
    }
}

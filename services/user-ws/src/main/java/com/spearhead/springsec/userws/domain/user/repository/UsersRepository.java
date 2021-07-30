package com.spearhead.springsec.userws.domain.user.repository;

import com.spearhead.springsec.userws.domain.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends CrudRepository<UserEntity, String> {
    UserEntity findByUsername(String username);

    @Override
    List<UserEntity> findAll();
}

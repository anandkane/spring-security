package com.spearhead.springsecurity.user.repository;

import com.spearhead.springsecurity.user.entity.AuthorityEntity;
import org.springframework.data.repository.CrudRepository;

public interface AuthorityRepository extends CrudRepository<AuthorityEntity, Integer> {

}

package com.ssai.handsonch11.authserver.user.dao.repo;

import com.ssai.handsonch11.authserver.user.dao.entity.OtpEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepository extends CrudRepository<OtpEntity, String> {

    Optional<OtpEntity> findByUsername(String username);
}

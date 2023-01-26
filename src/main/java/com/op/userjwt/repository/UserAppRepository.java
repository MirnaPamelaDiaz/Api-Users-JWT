package com.op.userjwt.repository;

import com.op.userjwt.entity.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAppRepository extends JpaRepository<UserApp, Integer> {
    Optional<UserApp> findByEmail(String email);
}

package com.virtuwear.rest.repository;

import com.virtuwear.rest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUid(String uid);
}

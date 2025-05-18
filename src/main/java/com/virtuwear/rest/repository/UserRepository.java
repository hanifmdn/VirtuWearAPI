package com.virtuwear.rest.repository;

import com.virtuwear.rest.entity.SingleGarment;
import com.virtuwear.rest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

}

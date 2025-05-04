package com.virtuwear.rest.repository;

import com.virtuwear.rest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT COUNT(u) FROM User u WHERE u.reedemedReferral = :referralCode")
    Integer countByReedemedReferral(String referralCode);

}

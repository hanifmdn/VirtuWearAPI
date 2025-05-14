package com.virtuwear.rest.repository;

import com.virtuwear.rest.entity.Referral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReferralRepository extends JpaRepository<Referral, String> {

}

package com.virtuwear.rest.repository;

import com.virtuwear.rest.entity.RedeemLog;
import com.virtuwear.rest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RedeemLogRepository extends JpaRepository<RedeemLog, Long> {
    boolean existsByInviterEmail_EmailAndInvitedEmail_Email(String inviterEmail, String invitedEmail);
}

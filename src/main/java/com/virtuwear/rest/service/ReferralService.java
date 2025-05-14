package com.virtuwear.rest.service;


import com.virtuwear.rest.dto.ReferralDto;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public interface ReferralService {

    ReferralDto updateUsedReferral(String referralCode, Long totalUsed);

    ReferralDto updateCooldownReferral(String referralCode,  Timestamp cooldown);

    ReferralDto getReferralByCode(String referralCode);

    List<ReferralDto> getAllReferrals();

    Integer getTotalReedemedReferral(String referralCode);

}

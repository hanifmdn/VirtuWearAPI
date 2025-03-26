package com.virtuwear.rest.service;


import com.virtuwear.rest.dto.ReferralDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReferralService {
    ReferralDto createReferral(ReferralDto referralDto);

    ReferralDto getReferralByCode(String referralCode);

    List<ReferralDto> getAllUser();

    ReferralDto updateReferral(String referralCode, ReferralDto updatedReferral);

    void deleteReferral (String referralCode);
}

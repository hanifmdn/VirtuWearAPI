package com.virtuwear.rest.mapper;

import com.virtuwear.rest.dto.ReferralDto;
import com.virtuwear.rest.entity.Referral;

public class ReferralMapper {
    public static ReferralDto mapToReferralDto(Referral referral) {
        return new ReferralDto(
                referral.getReferralCode(),
                referral.getTotalUsed(),
                referral.getCooldown(),
                referral.getCreatedDate(),
                referral.getUpdatedDate()
        );
    }


    public static Referral mapToReferral(ReferralDto referralDto) {
        return new Referral(
                referralDto.getReferralCode(),
                referralDto.getTotalUsed(),
                referralDto.getCooldown(),
                referralDto.getCreatedDate(),
                referralDto.getUpdatedDate()
        );
    }
}


package com.virtuwear.rest.mapper;

import com.virtuwear.rest.dto.ReferralDto;
import com.virtuwear.rest.entity.Referral;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class ReferralMapper {
    public ReferralDto toDto(Referral referral) {
        return new ReferralDto(
                referral.getReferralCode(),
                referral.getTotalUsed()
        );

    }

    public Referral toEntity(ReferralDto referralDto) {
        Referral referral = new Referral();
        referral.setReferralCode(referralDto.getReferralCode());
        referral.setTotalUsed(referralDto.getTotalUsed());
        return referral;

    }

    public List<ReferralDto> toDtoList(List<Referral> referrals) {
        return referrals.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

}


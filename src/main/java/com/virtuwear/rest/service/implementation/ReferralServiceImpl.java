package com.virtuwear.rest.service.implementation;

import com.virtuwear.rest.dto.ReferralDto;
import com.virtuwear.rest.dto.UserDto;
import com.virtuwear.rest.entity.Referral;
import com.virtuwear.rest.entity.User;
import com.virtuwear.rest.mapper.ReferralMapper;
import com.virtuwear.rest.mapper.UserMapper;
import com.virtuwear.rest.repository.ReferralRepository;
import com.virtuwear.rest.service.ReferralService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReferralServiceImpl implements ReferralService {

    @Autowired
    private ReferralRepository referralRepository;


    @Override
    public ReferralDto createReferral(ReferralDto referralDto) {
        Referral referral = ReferralMapper.mapToReferral(referralDto);
        Referral savedReferral = referralRepository.save(referral);
        return ReferralMapper.mapToReferralDto(savedReferral);
    }

    @Override
    public ReferralDto getReferralByCode(String referralCode) {
        return null;
    }

    @Override
    public List<ReferralDto> getAllUser() {
        return List.of();
    }

    @Override
    public ReferralDto updateReferral(String referralCode, ReferralDto updatedReferral) {
        return null;
    }

    @Override
    public void deleteReferral(String referralCode) {

    }
}

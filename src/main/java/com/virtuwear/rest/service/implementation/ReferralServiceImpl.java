package com.virtuwear.rest.service.implementation;

import com.virtuwear.rest.dto.ReferralDto;
import com.virtuwear.rest.dto.UserDto;
import com.virtuwear.rest.entity.Referral;
import com.virtuwear.rest.entity.User;
import com.virtuwear.rest.exception.ResourceNotFoundException;
import com.virtuwear.rest.mapper.ReferralMapper;
import com.virtuwear.rest.mapper.UserMapper;
import com.virtuwear.rest.repository.ReferralRepository;
import com.virtuwear.rest.repository.UserRepository;
import com.virtuwear.rest.service.CoinService;
import com.virtuwear.rest.service.ReferralService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.virtuwear.rest.utility.CalculateReward;

import java.sql.Ref;
import java.sql.Timestamp;
import java.util.List;

@Service
@AllArgsConstructor
public class ReferralServiceImpl implements ReferralService {

    @Autowired
    private final ReferralRepository referralRepository;
    @Autowired
    private final ReferralMapper referralMapper;
    @Autowired
    private final CoinService coinService;




    //  Update TotalUsed
    @Override
    public ReferralDto updateUsedReferral(String referralCode, Long totalUsed) {
        Referral referral = referralRepository.findById(referralCode)
                .orElseThrow(() -> new RuntimeException("Referral not found"));

        referral.setTotalUsed(totalUsed);
        referral = referralRepository.save(referral);

        return referralMapper.toDto(referral);
    }

    //  Update Cooldown
    @Override
    public ReferralDto updateCooldownReferral(String referralCode, Timestamp cooldown) {
        Referral referral = referralRepository.findById(referralCode)
                .orElseThrow(() -> new RuntimeException("Referral not found"));

        referral = referralRepository.save(referral);

        return referralMapper.toDto(referral);
    }

    //  Get Referral by Code
    @Override
    public ReferralDto getReferralByCode(String referralCode) {
        Referral referral = referralRepository.findById(referralCode)
                .orElseThrow(() -> new ResourceNotFoundException("Referral not found with code: " + referralCode));
        return referralMapper.toDto(referral);
    }

    @Override
    public List<ReferralDto> getAllReferrals() {
        List<Referral> referrals = referralRepository.findAll();
        return referralMapper.toDtoList(referrals);
    }



    @Override
    public void checkRewardMilestone(Referral referral, Long totalInvitation) {
        int[] milestones = {3, 7, 12, 20, 35, 50};

        for (int milestone : milestones) {
            if (totalInvitation >= milestone && referral.getMilestone() < milestone) {
                int reward = CalculateReward.calculateReward(milestone);
                System.out.println("Reward untuk milestone "+ milestone+" adalah: " + reward);

                // addcoin
                coinService.addRewardCoin(referral.getUser(), reward);

                referral.setMilestone(milestone);
                referralRepository.save(referral);


                break;
            }
        }
    }
}

package com.virtuwear.rest.dto;

import com.virtuwear.rest.entity.Referral;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDto {
    private int token;
    private int totalTryon;
    private int totalGenerate;
    private String redeemedReferral;
    private ReferralDto referral;
}

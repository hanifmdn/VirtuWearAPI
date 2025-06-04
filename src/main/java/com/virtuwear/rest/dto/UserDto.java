package com.virtuwear.rest.dto;

import com.virtuwear.rest.entity.Coin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String uid;
    private String email;
    private int totalGenerate;
    private String redeemedReferral;
    private ReferralDto referral;
    private CoinDto coin;
}
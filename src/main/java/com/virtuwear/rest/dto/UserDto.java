package com.virtuwear.rest.dto;

import com.virtuwear.rest.entity.DoubleGarment;
import com.virtuwear.rest.entity.Referral;
import com.virtuwear.rest.entity.SingleGarment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String uid;
    private String email;
    private String name;
    private int token;
    private int totalTryon;
    private int totalGenerate;
    private String redeemedReferral;
    private ReferralDto referral;

}
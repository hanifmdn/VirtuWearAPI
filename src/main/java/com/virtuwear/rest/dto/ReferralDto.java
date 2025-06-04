package com.virtuwear.rest.dto;

import com.virtuwear.rest.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReferralDto {
    private String referralCode;
    private Long totalUsed;
    private Integer milestone;
}

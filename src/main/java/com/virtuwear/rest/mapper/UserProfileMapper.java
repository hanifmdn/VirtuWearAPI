package com.virtuwear.rest.mapper;

import com.virtuwear.rest.dto.UserProfileDto;
import com.virtuwear.rest.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
//public class UserProfileMapper {
//    @Autowired
//    private ReferralMapper referralMapper;
//
//    public UserProfileDto toDto(User user) {
//        return new UserProfileDto(
//                user.getToken(),
//                user.getTotalGenerate(),
//                user.getRedeemedReferral(),
//                user.getReferral() != null ? referralMapper.toDto(user.getReferral()) : null
//        );
//    }
//}

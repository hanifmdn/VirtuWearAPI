package com.virtuwear.rest.mapper;

import com.virtuwear.rest.dto.ReferralDto;
import com.virtuwear.rest.dto.UserDto;
import com.virtuwear.rest.entity.User;
import com.virtuwear.rest.mapper.ReferralMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    @Autowired
    private ReferralMapper referralMapper;

    public UserDto toDto(User user) {
        return new UserDto(
                user.getUid(),
                user.getEmail(),
                user.getName(),
                user.getToken(),
                user.getTotalTryon(),
                user.getTotalGenerate(),
                user.getReedemedReferral(),
                user.getReferral() != null ? referralMapper.toDto(user.getReferral()) : null
        );
    }

    public User toEntity(UserDto userDto) {
        User user = new User();
        user.setUid(userDto.getUid());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setToken(userDto.getToken());
        user.setTotalTryon(userDto.getTotalTryon());
        user.setTotalGenerate(userDto.getTotalGenerate());
        user.setReedemedReferral(userDto.getRedeemedReferral());
        return user;
    }
}
package com.virtuwear.rest.mapper;

import com.virtuwear.rest.dto.UserDto;
import com.virtuwear.rest.entity.Coin;
import com.virtuwear.rest.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    @Autowired
    private ReferralMapper referralMapper;
    @Autowired
    private CoinMapper coinMapper;


    public UserDto toDto(User user) {
        return new UserDto(
                user.getUid(),
                user.getEmail(),
                user.getToken(),
                user.getTotalGenerate(),
                user.getRedeemedReferral(),
                user.getReferral() != null ? referralMapper.toDto(user.getReferral()) : null,
                user.getCoin() != null ? coinMapper.toDto(user.getCoin()) : null
        );
    }

    public User toEntity(UserDto userDto) {
        User user = new User();
        user.setUid(userDto.getUid());
        user.setEmail(userDto.getEmail());
        user.setToken(userDto.getToken());
        user.setTotalGenerate(userDto.getTotalGenerate());
        user.setRedeemedReferral(userDto.getRedeemedReferral());
        return user;
    }
}
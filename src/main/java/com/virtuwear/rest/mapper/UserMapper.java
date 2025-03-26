package com.virtuwear.rest.mapper;

import com.virtuwear.rest.dto.ReferralDto;
import com.virtuwear.rest.dto.UserDto;
import com.virtuwear.rest.entity.User;
import com.virtuwear.rest.mapper.ReferralMapper;

public class UserMapper {

    public static UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getUid(),
                user.getEmail(),
                user.getName(),
                user.getToken(),
                user.getTotalTryon(),
                user.getTotalGenerate(),
                (user.getReferral() != null) ? user.getReferral().getReferralCode() : null,
                user.getCreatedDate(),
                user.getUpdatedDate()
        );
    }

    public static User mapToUser(UserDto userDto) {
        User user = new User();
        user.setUid(userDto.getUid());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setToken(userDto.getToken());
        user.setTotalTryon(userDto.getTotalTryon());
        user.setTotalGenerate(userDto.getTotalGenerate());
        user.setCreatedDate(userDto.getCreatedDate());
        user.setUpdatedDate(userDto.getUpdatedDate());

        return user;
    }

}
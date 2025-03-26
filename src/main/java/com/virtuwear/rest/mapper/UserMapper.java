package com.virtuwear.rest.mapper;

import com.virtuwear.rest.dto.UserDto;
import com.virtuwear.rest.entity.User;
import com.virtuwear.rest.mapper.ReferralMapper;

public class UserMapper {

    public static UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getUid(),
                user.getSingleGarments(),
                user.getDoubleGarments(),
                user.getReferral() != null ? ReferralMapper.mapToReferralDto(user.getReferral()) : null,
                user.getEmail(),
                user.getName(),
                user.getToken(),
                user.getTotalTryon(),
                user.getTotalGenerate(),
                user.getCreatedDate(),
                user.getUpdatedDate()
        );
    }

    public static User mapToUser(UserDto userDto) {
        return new User(
                userDto.getUid(),
                userDto.getSingleGarments(),
                userDto.getDoubleGarments(),
                null,
                userDto.getEmail(),
                userDto.getName(),
                userDto.getToken(),
                userDto.getTotalTryon(),
                userDto.getTotalGenerate(),
                userDto.getCreatedDate(),
                userDto.getUpdatedDate()
        );
    }
}
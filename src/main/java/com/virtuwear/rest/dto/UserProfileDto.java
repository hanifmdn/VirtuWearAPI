package com.virtuwear.rest.dto;

import com.virtuwear.rest.entity.Referral;
import com.virtuwear.rest.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDto {
    private UserDto userDto;
    private Integer totalTryOn;

}

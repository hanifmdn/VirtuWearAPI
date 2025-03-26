package com.virtuwear.rest.service;

import com.virtuwear.rest.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserByUID(String uid);

    List<UserDto> getAllUser();

    UserDto updateUser(String uid, UserDto updatedUser);

    void deleteUser (String uid);
}

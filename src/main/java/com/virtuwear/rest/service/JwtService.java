package com.virtuwear.rest.service;

import com.virtuwear.rest.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface JwtService {
    String generateToken(UserDto userDto);
}

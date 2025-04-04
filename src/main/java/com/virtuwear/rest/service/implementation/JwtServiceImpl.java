package com.virtuwear.rest.service.implementation;

import com.virtuwear.rest.dto.UserDto;
import com.virtuwear.rest.service.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class JwtServiceImpl implements JwtService {
    private static final String SECRET_KEY = "BarcanbututPameungpeukCucuklauk1234567890maaf..@#$"; // Gantilah dengan kunci rahasia yang aman

    @Override
    public String generateToken(UserDto userDto) {
        return Jwts.builder()
                .setSubject(userDto.getUid())
                .claim("email", userDto.getEmail())
                .claim("name", userDto.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Berlaku 1 jam
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}



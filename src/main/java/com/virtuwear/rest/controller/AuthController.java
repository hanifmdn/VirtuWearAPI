package com.virtuwear.rest.controller;

import com.virtuwear.rest.dto.ReferralDto;
import com.virtuwear.rest.dto.UserDto;
import com.virtuwear.rest.repository.UserRepository;
import com.virtuwear.rest.service.JwtService;
import com.virtuwear.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Map;
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    // Perbaikan: Terima uid, email, dan name, bukan idToken
    @PostMapping("/firebase")
    public ResponseEntity<?> authenticateWithFirebase(@RequestBody UserDto user) {
        try {
            // Cari atau buat user baru jika belum ada
            UserDto userDto = userService.createUser(user);

            // Buatkan JWT untuk session
            String customToken = jwtService.generateToken(userDto);

            return ResponseEntity.ok(Map.of("jwt", customToken, "user", userDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: " + e.getMessage());
        }
    }
}
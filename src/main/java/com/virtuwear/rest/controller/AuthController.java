package com.virtuwear.rest.controller;

import com.virtuwear.rest.dto.UserDto;
import com.virtuwear.rest.service.JwtService;
import com.virtuwear.rest.service.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    // Perbaikan: Terima uid, email, dan name, bukan idToken
    @PostMapping("/firebase")
    public ResponseEntity<?> authenticateWithFirebase(@RequestBody UserRequest request) {
        try {
            // Cari atau buat user baru jika belum ada
            UserDto userDto = userService.findOrCreateUser(request.getUid(), request.getEmail(), request.getName());

            // Buatkan JWT untuk session
            String customToken = jwtService.generateToken(userDto);

            return ResponseEntity.ok(Map.of("jwt", customToken, "user", userDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: " + e.getMessage());
        }
    }
}

// Perbaikan: Tambahkan class untuk request agar lebih rapi
@Getter
class UserRequest {
    // Getter dan Setter
    private String uid;
    private String email;
    private String name;

    public void setUid(String uid) { this.uid = uid; }

    public void setEmail(String email) { this.email = email; }

    public void setName(String name) { this.name = name; }
}
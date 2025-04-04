package com.virtuwear.rest.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${kling.api.access-key}")
    private String accessKey;

    @Value("${kling.api.secret-key}")
    private String secretKey;

    public String generateToken() {
        try {
            Date expiredAt = new Date(System.currentTimeMillis() + 1800 * 1000); // 30 menit
            Date notBefore = new Date(System.currentTimeMillis() - 5 * 1000); // -5 detik

            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            Map<String, Object> header = new HashMap<>();
            header.put("alg", "HS256");

            return JWT.create()
                    .withIssuer(accessKey)
                    .withHeader(header)
                    .withExpiresAt(expiredAt)
                    .withNotBefore(notBefore)
                    .sign(algorithm);
        } catch (Exception e) {
            throw new RuntimeException("Error generating JWT token", e);
        }
    }
}


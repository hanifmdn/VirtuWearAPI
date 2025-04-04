package com.virtuwear.rest.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtilTest {
    public static String generateToken(String accessKey, String secretKey) {
        try {
            Date expiredAt = new Date(System.currentTimeMillis() + 1800 * 1000); // 30 menit
            Date notBefore = new Date(System.currentTimeMillis() - 5 * 1000); // -5 detik

            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            Map<String, Object> header = new HashMap<>();
            header.put("alg", "HS256");
            header.put("typ", "JWT"); // Tambahkan "typ": "JWT"

            return JWT.create()
                    .withIssuer(accessKey)
                    .withHeader(header)
                    .withExpiresAt(expiredAt)
                    .withNotBefore(notBefore)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

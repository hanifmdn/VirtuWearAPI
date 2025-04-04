package com.virtuwear.rest;

import com.virtuwear.rest.utility.JwtUtilTest;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class KlingAiRequestQuerySingle {
    public static void main(String[] args) {
        try {
            // Masukkan Access Key dan Secret Key secara manual atau dari input
            String accessKey = "4e95d0cf579441c0822e0158a7dccb2f";
            String secretKey = "b02045ea67e14e8581b6ecb196f9dceb";

            // Generate Token (Asumsikan metode generateToken tersedia)
            String token = JwtUtilTest.generateToken(accessKey, secretKey);
            if (token == null) {
                System.err.println("Gagal menghasilkan token.");
                return;
            }
            System.out.println("Generated Token: " + token);

            String id = "ClwhDWfqP0cAAAAAAMbQvg";

            // API Endpoint
            String url = "https://api.klingai.com/v1/images/kolors-virtual-try-on/"+id;


            // Headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + token);

            // Debug: Cetak header sebelum request dikirim
            System.out.println("Request Headers:");
            headers.forEach((key, value) -> System.out.println(key + ": " + value));

            // HTTP Request
            HttpEntity<String> requestEntity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Response Body: " + response.getBody());
        } catch (Exception e) {
            System.err.println("Error saat mengirim request: " + e.getMessage());
        }
    }
}

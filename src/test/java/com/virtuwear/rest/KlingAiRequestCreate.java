package com.virtuwear.rest;

import com.virtuwear.rest.utility.JwtUtilTest;
import org.json.JSONException;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


import org.json.JSONObject;

public class KlingAiRequestCreate {

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

            // API Endpoint
            String url = "https://api.klingai.com/v1/images/kolors-virtual-try-on";

            // Request Body
            JSONObject requestBody = new JSONObject();
            requestBody.put("model_name", "kolors-virtual-try-on-v1");
            requestBody.put("human_image", "https://media.discordapp.net/attachments/1178349754393055344/1356936707500408922/download_35.jpg");
            requestBody.put("cloth_image", "https://media.discordapp.net/attachments/1178349754393055344/1356936707827699853/Vintage_T-Shirts__A_Timeless_Retro_Look.jpg");
            requestBody.put("callback_url", "http://yourserver.com/callback");

            // Headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + token);

            // Debug: Cetak header sebelum request dikirim
            System.out.println("Request Headers:");
            headers.forEach((key, value) -> System.out.println(key + ": " + value));

            // HTTP Request
            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody.toString(), headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Response Body: " + response.getBody());
        } catch (JSONException e) {
            System.err.println("JSONException terjadi: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error saat mengirim request: " + e.getMessage());
        }

    }

}

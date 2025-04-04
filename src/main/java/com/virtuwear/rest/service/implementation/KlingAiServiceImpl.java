package com.virtuwear.rest.service.implementation;

import com.virtuwear.rest.dto.*;
import com.virtuwear.rest.service.KlingAiService;
import com.virtuwear.rest.utility.JwtUtil;
import com.virtuwear.rest.utility.JwtUtilTest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@Service
public class KlingAiServiceImpl implements KlingAiService {
    @Value("${kling.api.access-key}")
    String accessKey;
    @Value("${kling.api.secret-key}")
    String secretKey;


    private final RestTemplate restTemplate;

    private String apiDomain = "https://api.klingai.com";

    public KlingAiServiceImpl() {
        this.restTemplate = new RestTemplate();
    }

    // Membuat Task Try-On
    public KlingAiCreateResponseDto createTryOnTask(KlingAiRequestDto requestDto) {
        String token = JwtUtilTest.generateToken(accessKey, secretKey);
        System.out.println("Generated Token: " + token);

        if (token == null) {
            throw new RuntimeException("Failed to generate token");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        // Pastikan human_image adalah URL yang valid
        String humanImage = requestDto.getHuman_image();
        if (!humanImage.startsWith("http")) {
            throw new IllegalArgumentException("Invalid image format. Only public URLs are allowed.");
        }

        HttpEntity<KlingAiRequestDto> requestEntity = new HttpEntity<>(requestDto, headers);
        ResponseEntity<KlingAiCreateResponseDto> response;
        try {
            response = restTemplate.exchange(
                    "https://api.klingai.com/v1/images/kolors-virtual-try-on",
                    HttpMethod.POST,
                    requestEntity,
                    KlingAiCreateResponseDto.class
            );

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody();
            } else {
                throw new RuntimeException("Failed to create try-on task. Response: " + response);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error calling Kolors Virtual Try-On API", e);
        }
    }


    // Mengambil Status Task berdasarkan ID
    public KlingAiSingleTaskResponseDto getTryOnTaskStatus(String taskId) {
        String token = JwtUtilTest.generateToken(accessKey, secretKey);
        System.out.println("Authorization Header: Bearer " + token);

        if (token == null) {
            throw new RuntimeException("Failed to generate token");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);


        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> rawResponse = restTemplate.exchange(
                "https://api.klingai.com/v1/images/kolors-virtual-try-on/" + taskId,
                HttpMethod.GET,
                requestEntity,
                String.class
        );

        System.out.println("Raw Response: " + rawResponse.getBody());

        ResponseEntity<KlingAiSingleTaskResponseDto> response = restTemplate.exchange(
                "https://api.klingai.com/v1/images/kolors-virtual-try-on/" + taskId,
                HttpMethod.GET,
                requestEntity,
                KlingAiSingleTaskResponseDto.class
        );


        return response.getBody();
    }

    // Mengambil Daftar Task Try-On
    public KlingAiListTaskResponseDto getTryOnTaskList(int pageNum, int pageSize) {
        String token = JwtUtilTest.generateToken(accessKey, secretKey);
        System.out.println("Authorization Header: Bearer " + token);

        if (token == null) {
            throw new RuntimeException("Failed to generate token");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<KlingAiListTaskResponseDto> response = restTemplate.exchange(
                 "https://api.klingai.com/v1/images/kolors-virtual-try-on/?pageNum=" + pageNum + "&pageSize=" + pageSize,
                HttpMethod.GET,
                requestEntity,
                KlingAiListTaskResponseDto.class
        );

        return response.getBody();
    }



    // alternatif callback karena belum di host
    public KlingAiSingleTaskResponseDto pollTryOnTaskStatus(String taskId, int maxRetries, int intervalSeconds) {
        int attempt = 0;

        while (attempt < maxRetries) {
            KlingAiSingleTaskResponseDto response = getTryOnTaskStatus(taskId);

            // stop polling
            if ("succeed".equals(response.getData().getTask_status()) || "failed".equals(response.getData().getTask_status_msg())) {
                System.out.println("Final Task Status: " + response.getData().getTask_status());
                return response;
            }

            System.out.println("Task masih diproses, mencoba lagi dalam " + intervalSeconds + " detik...");
            try {
                Thread.sleep(intervalSeconds * 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Polling interrupted", e);
            }

            attempt++;
        }

        throw new RuntimeException("Max retries reached, task status unknown.");
    }

}

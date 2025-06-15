package com.virtuwear.rest.service.implementation;

import com.virtuwear.rest.dto.*;
import com.virtuwear.rest.service.KlingAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class KlingAiServiceImpl implements KlingAiService {
    @Value("${kling.api.access-key}")
    private String accessKey;

    @Value("${kling.api.secret-key}")
    private String secretKey;

    @Value("${kling.api.domain}")
    private String apiDomain;

    private final RestTemplate restTemplate;

    public KlingAiServiceImpl() {
        this.restTemplate = new RestTemplate();
    }

    /**
     * Generate JWT token with HMAC256 algorithm
     */
    @Override
    public String generateToken() {
        try {
            Date now = new Date();
            Date exp = new Date(now.getTime() + 1800 * 1000);  // 30 minutes
            Date nbf = new Date(now.getTime() - 5 * 1000);     // 5 seconds ago

            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            Map<String, Object> header = new HashMap<>();
            header.put("alg", "HS256");

            return JWT.create()
                    .withIssuer(accessKey)
                    .withHeader(header)
                    .withExpiresAt(exp)
                    .withNotBefore(nbf)
                    .sign(algorithm);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate JWT token", e);
        }
    }

    @Override
    public KlingAiCreateResponseDto createTryOnTask(KlingAiRequestDto requestDto) {
        // Deduct coins or handle pricing logic here (generatePrice = 20)

        String token = generateToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        // Validate image URL
        if (!requestDto.getHuman_image().startsWith("http")) {
            throw new IllegalArgumentException("Invalid image format. Only public URLs are allowed.");
        }

        HttpEntity<KlingAiRequestDto> entity = new HttpEntity<>(requestDto, headers);
        String url = String.format("%s/v1/images/kolors-virtual-try-on", apiDomain);

        ResponseEntity<KlingAiCreateResponseDto> response =
                restTemplate.exchange(url, HttpMethod.POST, entity, KlingAiCreateResponseDto.class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody();
        }
        throw new RuntimeException("Failed to create try-on task: " + response);
    }

    @Override
    public KlingAiSingleTaskResponseDto getTryOnTaskStatus(String taskId) {
        String token = generateToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        HttpEntity<Void> entity = new HttpEntity<>(headers);
        String url = String.format("%s/v1/images/kolors-virtual-try-on/%s", apiDomain, taskId);

        ResponseEntity<KlingAiSingleTaskResponseDto> response =
                restTemplate.exchange(url, HttpMethod.GET, entity, KlingAiSingleTaskResponseDto.class);

        return response.getBody();
    }

    @Override
    public KlingAiListTaskResponseDto getTryOnTaskList(int pageNum, int pageSize) {
        String token = generateToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        HttpEntity<Void> entity = new HttpEntity<>(headers);
        String url = String.format("%s/v1/images/kolors-virtual-try-on?pageNum=%d&pageSize=%d",
                apiDomain, pageNum, pageSize);

        ResponseEntity<KlingAiListTaskResponseDto> response =
                restTemplate.exchange(url, HttpMethod.GET, entity, KlingAiListTaskResponseDto.class);

        return response.getBody();
    }

    @Override
    public KlingAiSingleTaskResponseDto pollTryOnTaskStatus(String taskId, int maxRetries, int intervalSeconds) {
        for (int attempt = 0; attempt < maxRetries; attempt++) {
            KlingAiSingleTaskResponseDto resp = getTryOnTaskStatus(taskId);
            String status = resp.getData().getTask_status();
            if ("succeed".equalsIgnoreCase(status) || "failed".equalsIgnoreCase(status)) {
                return resp;
            }
            try {
                Thread.sleep(intervalSeconds * 1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Polling interrupted", e);
            }
        }
        throw new RuntimeException("Max retries reached, task status unknown.");
    }
}

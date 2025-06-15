package com.virtuwear.rest.controller;

import com.virtuwear.rest.dto.*;
import com.virtuwear.rest.service.KlingAiService;
import com.virtuwear.rest.service.implementation.KlingAiServiceImpl;
import com.virtuwear.rest.utility.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@Slf4j
@RestController
@RequestMapping("/klingai")
public class KlingAiController {

    private final JwtUtil jwtUtil;

    private final KlingAiService klingAiService;

    public KlingAiController(KlingAiService klingAiService, JwtUtil jwtUtil) {
        this.klingAiService = klingAiService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/tryon")
    public ResponseEntity<KlingAiCreateResponseDto> createTryOnTask(@RequestBody KlingAiRequestDto requestDto) {
        KlingAiCreateResponseDto response = klingAiService.createTryOnTask(requestDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/tryon/{taskId}")
    public ResponseEntity<KlingAiSingleTaskResponseDto> getTryOnTaskStatus(@PathVariable String taskId) {
        KlingAiSingleTaskResponseDto response = klingAiService.getTryOnTaskStatus(taskId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/tryon/list")
    public ResponseEntity<KlingAiListTaskResponseDto> getTryOnTaskList(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "30") int pageSize) {
        KlingAiListTaskResponseDto response = klingAiService.getTryOnTaskList(pageNum, pageSize);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/tryon/callback")
    public ResponseEntity<String> handleCallback(@RequestBody String callbackData) {
        log.info("Received callback: " + callbackData);
        return ResponseEntity.ok("Callback received");
    }

//    @GetMapping("/token")
//    public ResponseEntity<?> getToken() {
//        String token = jwtUtil.generateToken();
//        return ResponseEntity.ok(Collections.singletonMap("token", token));
//    }

    @GetMapping("/tryon/status/{taskId}")
    public ResponseEntity<KlingAiSingleTaskResponseDto> getTryOnTaskStatusWithPolling(@PathVariable String taskId) {
        KlingAiSingleTaskResponseDto response = klingAiService.pollTryOnTaskStatus(taskId, 10, 30);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/token")
    public ResponseEntity<String> getToken() {
        String token = klingAiService.generateToken();  // perlu ubah generateToken() jadi public
        return ResponseEntity.ok(token);
    }

}



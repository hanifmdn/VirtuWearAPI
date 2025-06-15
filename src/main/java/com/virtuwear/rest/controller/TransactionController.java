package com.virtuwear.rest.controller;

import com.virtuwear.rest.dto.SingleGarmentDto;
import com.virtuwear.rest.dto.TransactionDto;
import com.virtuwear.rest.dto.UserDto;
import com.virtuwear.rest.service.ModelService;
import com.virtuwear.rest.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor

public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/validate/{userUid}")
    public ResponseEntity<Map<String, String>> validateGenerate(@PathVariable String userUid) {
        transactionService.validateGenerateTryon(userUid);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Balance sufficient for try-on generation.");
        return ResponseEntity.ok(response);
    }


    @PostMapping("/reduce/{userUid}")
    public ResponseEntity<TransactionDto> reduceCoin(@PathVariable String userUid) {
        TransactionDto makeTransaction = transactionService.reduceCoin(userUid);

        return new ResponseEntity<>(makeTransaction, HttpStatus.OK);
    }


}

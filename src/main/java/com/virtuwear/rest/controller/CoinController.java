package com.virtuwear.rest.controller;

import com.virtuwear.rest.dto.PurchaseRequest;
import com.virtuwear.rest.service.CoinService;
import com.virtuwear.rest.service.GarmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/coin")
@RequiredArgsConstructor
public class CoinController {
    private final CoinService coinService;

    @PostMapping("/purchase")
    public ResponseEntity<?> purchase(@RequestBody PurchaseRequest request) {
        Map<String, Integer> coinMap = Map.of(
                "coin_pack_10", 10,
                "coin_pack_20", 20,
                "coin_pack_30", 30,
                "coin_pack_40", 40,
                "coin_pack_50", 50
        );

        if (!coinMap.containsKey(request.getProductId())) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("status", "error", "message", "Invalid product"));
        }

        int coinAmount = coinMap.get(request.getProductId());
        coinService.addPurchaseCoin(request.getUserUid(), coinAmount);

        return ResponseEntity.ok(
                Map.of(
                        "purchaseToken", request.getPurchaseToken(),
                        "productId", request.getProductId(),
                        "userUid",    request.getUserUid()
                )
        );
    }

}

package com.virtuwear.rest.controller;


import com.virtuwear.rest.dto.ReferralDto;
import com.virtuwear.rest.dto.SingleGarmentDto;
import com.virtuwear.rest.dto.UserDto;
import com.virtuwear.rest.service.ReferralService;
import com.virtuwear.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/referrals")
public class ReferralController {
    @Autowired
    private ReferralService referralService;

    @Autowired
    private UserService userService;

    @GetMapping("/{referralCode}")
    public ResponseEntity<ReferralDto> getReferralByCode(@PathVariable String referralCode) {
        ReferralDto referral = referralService.getReferralByCode(referralCode);
        return ResponseEntity.ok(referral);
    }

    @GetMapping
    public ResponseEntity<List<ReferralDto>> getAllReferrals() {
        List<ReferralDto> referrals = referralService.getAllReferrals();
        return ResponseEntity.ok(referrals);
    }

    @PutMapping("/{referralCode}/totalUsed")
    public ResponseEntity<ReferralDto> updateUsedReferral(
            @PathVariable String referralCode,
            @RequestParam Long totalUsed) {
        ReferralDto updatedReferral = referralService.updateUsedReferral(referralCode, totalUsed);
        return ResponseEntity.ok(updatedReferral);
    }

    @PutMapping("/{referralCode}/cooldown")
    public ResponseEntity<ReferralDto> updateCooldownReferral(
            @PathVariable String referralCode,
            @RequestParam Timestamp cooldown) {
        ReferralDto updatedReferral = referralService.updateCooldownReferral(referralCode, cooldown);
        return ResponseEntity.ok(updatedReferral);
    }

}
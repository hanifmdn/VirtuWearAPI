package com.virtuwear.rest.controller;


import com.virtuwear.rest.dto.ReferralDto;
import com.virtuwear.rest.dto.UserDto;
import com.virtuwear.rest.service.ReferralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/referrals")
public class ReferralController {
    @Autowired
    ReferralService referralService;

    // Build add employee REST API
    @PostMapping
    public ResponseEntity<ReferralDto> createRefferal(@RequestBody ReferralDto referralDto) {
        ReferralDto savedReferral = referralService.createReferral(referralDto);
        return new ResponseEntity<>(savedReferral, HttpStatus.CREATED);
    }


}

package com.virtuwear.rest.controller;

import com.virtuwear.rest.dto.UserDto;
import com.virtuwear.rest.dto.UserProfileDto;
import com.virtuwear.rest.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    // add employee REST API
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto savedUser = userService.createUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // Get Employee REST API
    @GetMapping("{uid}")
    public ResponseEntity<UserDto> getUserByUID(@PathVariable("uid") String uid) {
        UserDto userDto = userService.getUserByUID(uid);
        return ResponseEntity.ok(userDto);
    }

//    @GetMapping("profile/{uid}")
//    public ResponseEntity<UserProfileDto> getUserProfileByUID(@PathVariable("uid") String uid) {
//        UserProfileDto userProfileDto = userService.getProfile(uid);
//        return ResponseEntity.ok(userProfileDto);
//
//    }

    // Get All User REST API
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }

    // Update User REST API
    @PutMapping("{uid}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("uid") String uid,  @RequestBody UserDto updatedUser) {
        UserDto userDto = userService.updateUser(uid, updatedUser);
        return ResponseEntity.ok(userDto);
    }

    // Delete User REST API
    @DeleteMapping("{uid}")
    public ResponseEntity<String> deleteUser(@PathVariable("uid") String uid) {
        userService.deleteUser(uid);

        return ResponseEntity.ok("User deleted succesfully");
    }

    // User Reedem Referral REST API
    @PostMapping("/{uid}/redeem-referral/{referralCode}")
    public ResponseEntity<UserDto> redeemReferral(@PathVariable String uid, @PathVariable String referralCode) {
        UserDto updatedUser = userService.redeemReferral(uid, referralCode);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // Update total generate user
    @PostMapping("{uid}/total_generate")
    public ResponseEntity<UserDto> updateTotalGenerate(@PathVariable String uid) {
        UserDto userDto = userService.updateTotalGenerate(uid);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/update/dashboard/{uid}")
    public ResponseEntity<UserDto> updateDashboard(@PathVariable("uid") String uid) {
        UserDto userDto = userService.updateDashboard(uid);
        return ResponseEntity.ok(userDto);
    }

}

package com.virtuwear.rest.service.implementation;

import com.virtuwear.rest.dto.ReferralDto;
import com.virtuwear.rest.dto.UserDto;
import com.virtuwear.rest.entity.Referral;
import com.virtuwear.rest.entity.User;
import com.virtuwear.rest.exception.ResourceNotFoundException;
import com.virtuwear.rest.mapper.ReferralMapper;
import com.virtuwear.rest.mapper.UserMapper;
import com.virtuwear.rest.repository.ReferralRepository;
import com.virtuwear.rest.repository.UserRepository;
import com.virtuwear.rest.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private final ReferralRepository referralRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);

        // Generate referral code baru
        Referral referral = new Referral();
        referral.setReferralCode(UUID.randomUUID().toString());
        referral.setTotalUsed(0L);
        referral.setCooldown(null);
        referral.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
        referral.setUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));

        // Simpan Referral ke database
        referralRepository.save(referral);

        // Hubungkan User dengan Referral
        user.setReferral(referral);

        // Simpan User ke database
        User savedUser = userRepository.save(user);

        // Kembalikan DTO
        return UserMapper.mapToUserDto(savedUser);
    }

    private String generateUniqueReferralCode() {
        String referralCode;
        do {
            referralCode = generateReferralCode();
        } while (referralRepository.existsById(referralCode));
        return referralCode;
    }

    private String generateReferralCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder referralCodeBuilder = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(characters.length());
            referralCodeBuilder.append(characters.charAt(index));
        }

        return referralCodeBuilder.toString();
    }


    @Override
    public UserDto getUserByUID(String uid) {
        User user = userRepository.findById(uid)
                .orElseThrow(() -> new ResolutionException("Employee is not exists with given uid : " + uid));
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> UserMapper.mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(String uid, UserDto updatedUser) {

        User user = userRepository.findById(uid).orElseThrow(
                () -> new ResourceNotFoundException("User is not exists with the given uid: " + uid)
        );

        user.setToken(updatedUser.getToken());
        user.setTotalGenerate(updatedUser.getTotalGenerate());
        user.setTotalTryon(updatedUser.getTotalTryon());

        User updatedUserObj = userRepository.save(user);


        return UserMapper.mapToUserDto(updatedUserObj);
    }

    @Override
    public void deleteUser(String uid) {
        User user = userRepository.findById(uid).orElseThrow(
                () -> new ResourceNotFoundException("User is not exists with the given uid: " + uid)
        );
        userRepository.deleteById(uid);
    }



}

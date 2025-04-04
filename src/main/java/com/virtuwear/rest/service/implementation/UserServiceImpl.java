package com.virtuwear.rest.service.implementation;

import com.virtuwear.rest.dto.UserDto;
import com.virtuwear.rest.entity.Referral;
import com.virtuwear.rest.entity.User;
import com.virtuwear.rest.exception.InvalidOperationException;
import com.virtuwear.rest.exception.ResourceNotFoundException;
import com.virtuwear.rest.mapper.UserMapper;
import com.virtuwear.rest.repository.ReferralRepository;
import com.virtuwear.rest.repository.UserRepository;
import com.virtuwear.rest.service.UserService;
import com.virtuwear.rest.utility.ReferralCodeGenerator;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReferralRepository referralRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user.setUid(userDto.getUid());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setToken(userDto.getToken());
        user.setTotalTryon(userDto.getTotalTryon());
        user.setTotalGenerate(userDto.getTotalGenerate());
        user.setReedemedReferral(userDto.getRedeemedReferral());

        String referralCode = generateUniqueReferralCode();


        Referral referral = new Referral();
        referral.setReferralCode(referralCode);
        referral.setTotalUsed(0L);
        referral.setUser(user);

        user.setReferral(referral);

        userRepository.save(user);
        return userMapper.toDto(user);
    }

    private String generateUniqueReferralCode() {
        String code;
        do {
            code = ReferralCodeGenerator.generateReferralCode();
        } while (referralRepository.existsById(code));
        return code;
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
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> userMapper.toDto(user))
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


        return userMapper.toDto(updatedUserObj);
    }

    @Override
    @Transactional
    public void deleteUser(String uid) {
        User user = userRepository.findById(uid)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with UID: " + uid));
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public UserDto redeemReferral(String uid, String referralCode) {
        User user = userRepository.findById(uid)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with UID: " + uid));

        Referral referral = referralRepository.findById(referralCode)
                .orElseThrow(() -> new ResourceNotFoundException("Referral not found with code: " + referralCode));

        if (referral.getUser().getUid().equals(uid)) {
            throw new InvalidOperationException("Cannot redeem your own referral.");
        }

        if (user.getReedemedReferral() != null) {
            throw new InvalidOperationException("User has already redeemed a referral.");
        }

        user.setReedemedReferral(referralCode);
        referral.setTotalUsed(referral.getTotalUsed() + 1);
        referral.setCooldown(Timestamp.valueOf(LocalDateTime.now().plusDays(7)));

        userRepository.save(user);
        referralRepository.save(referral);

        return userMapper.toDto(user);
    }
}

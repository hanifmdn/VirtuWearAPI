package com.virtuwear.rest.service.implementation;

import com.virtuwear.rest.dto.UserDto;
import com.virtuwear.rest.entity.Coin;
import com.virtuwear.rest.entity.Referral;
import com.virtuwear.rest.entity.User;
import com.virtuwear.rest.exception.InvalidOperationException;
import com.virtuwear.rest.exception.ResourceNotFoundException;
import com.virtuwear.rest.mapper.ReferralMapper;
import com.virtuwear.rest.mapper.UserMapper;
import com.virtuwear.rest.mapper.UserProfileMapper;
import com.virtuwear.rest.repository.RedeemLogRepository;
import com.virtuwear.rest.repository.ReferralRepository;
import com.virtuwear.rest.repository.UserRepository;
import com.virtuwear.rest.service.CoinService;
import com.virtuwear.rest.service.RedeemLogService;
import com.virtuwear.rest.service.ReferralService;
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
import java.util.Objects;
import java.util.Optional;
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
    private CoinService coinService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ReferralMapper referralMapper;

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Autowired
    private RedeemLogRepository redeemLogRepository;

    @Autowired
    private RedeemLogService redeemLogService;

    @Autowired
    private ReferralService referralService;

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        Optional<User> existingUser = userRepository.findById(userDto.getUid());

        if (existingUser.isPresent()) {
            throw new IllegalStateException("User already exists with UID: " + userDto.getUid());
        }

        User user = userMapper.toEntity(userDto);
        user.setUid(userDto.getUid());
        user.setEmail(userDto.getEmail());
        user.setToken(userDto.getToken());
        user.setTotalGenerate(userDto.getTotalGenerate());
        user.setRedeemedReferral(userDto.getRedeemedReferral());
        userRepository.save(user);

        String referralCode = generateUniqueReferralCode();

        // kayanya disini akar dari setiap ada user login referral nya ganti ganti
        Referral referral = new Referral();
        referral.setReferralCode(referralCode);
        referral.setTotalUsed(0L);
        referral.setUser(user);
        referral.setMilestone(0);
        user.setReferral(referral);

        //disini manggil createCoin dengan parameter User
//        coinService.createCoin(user);
        final int coinBalance = 20;
        LocalDateTime nextMonth = LocalDateTime.now().plusMonths(1);
        Timestamp oneMonthValid = Timestamp.valueOf(nextMonth);

        Coin coin = new Coin();
        coin.setUser(user);
        coin.setCoinBalance(coinBalance);
        coin.setValidUntil(oneMonthValid);
        user.setCoin(coin);


        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
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
                .orElseThrow(() -> new ResolutionException("User is not exists with given uid : " + uid));

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

        User updatedUserObj = userRepository.save(user);


        return userMapper.toDto(updatedUserObj);
    }


    @Override
    public UserDto updateTotalGenerate(String uid, UserDto updatedUser) {

        User user = userRepository.findById(uid).orElseThrow(
                () -> new ResourceNotFoundException("User is not exists with the given uid: " + uid)
        );

        user.setTotalGenerate(updatedUser.getTotalGenerate());

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

        if (Objects.equals(referral.getUser().getRedeemedReferral(), user.getReferral().getReferralCode())) {
            throw new InvalidOperationException("User cannot redeem back each other code.");
        }

        if (user.getRedeemedReferral() != null) {
            throw new InvalidOperationException("User has already redeemed a referral.");
        }

        if (redeemLogRepository.existsByInviterEmail_EmailAndInvitedEmail_Email(referral.getUser().getEmail(), user.getEmail())) {
            throw new IllegalStateException("This email has already redeemed a referral .");
        }

        user.setRedeemedReferral(referralCode);
        referral.setTotalUsed(referral.getTotalUsed() + 1);

        userRepository.save(user);
        referralRepository.save(referral);

        // simpan ke log
        redeemLogService.redeemLog(referral.getUser(), user);

        // check reward
        Long totalInvitation = referral.getTotalUsed();
        referralService.checkRewardMilestone(referral, totalInvitation);



        return userMapper.toDto(user);
    }
}

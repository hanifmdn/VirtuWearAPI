package com.virtuwear.rest.service.implementation;

import com.virtuwear.rest.dto.ReferralDto;
import com.virtuwear.rest.dto.UserDto;
import com.virtuwear.rest.entity.Referral;
import com.virtuwear.rest.entity.User;
import com.virtuwear.rest.exception.ResourceNotFoundException;
import com.virtuwear.rest.mapper.ReferralMapper;
import com.virtuwear.rest.mapper.UserMapper;
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
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {

        User user = UserMapper.mapToUser(userDto);

        ReferralDto referralDto = new ReferralDto();
        referralDto.setReferralCode(generateReferralCode());
        referralDto.setTotalUsed(0L);
        referralDto.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
        referralDto.setUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));

        Referral referral = ReferralMapper.mapToReferral(referralDto);
        referral.setUser(user);

        user.setReferral(referral);

        User savedUser = userRepository.save(user);

        return UserMapper.mapToUserDto(savedUser);
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

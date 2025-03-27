package com.virtuwear.rest.service.implementation;

import com.virtuwear.rest.dto.UserDto;
import com.virtuwear.rest.entity.Referral;
import com.virtuwear.rest.entity.User;
import com.virtuwear.rest.repository.UserRepository;
import com.virtuwear.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto findOrCreateUser(String uid, String email, String name) {
        Optional<User> existingUser = userRepository.findById(uid);

        if (existingUser.isPresent()) {
            User user = existingUser.get();

            // Ambil referral_code dari entitas Referral
            String referralCode = (user.getReferral() != null) ? user.getReferral().getReferralCode() : null;

            return new UserDto(
                    user.getUid(),
                    user.getEmail(),
                    user.getName(),
                    user.getToken(),
                    user.getTotalTryon(),
                    user.getTotalGenerate(),
                    referralCode, // Menggunakan referralCode yang benar
                    user.getCreatedDate(),
                    user.getUpdatedDate()
            );
        } else {
            // Jika belum ada, buat user baru
            User newUser = new User();
            newUser.setUid(uid);
            newUser.setEmail(email);
            newUser.setName(name);
            newUser.setToken(0);
            newUser.setTotalTryon(0);
            newUser.setTotalGenerate(0);
            newUser.setReferral(null); // Pastikan referral diset null jika tidak ada
            newUser.setCreatedDate(Timestamp.from(Instant.now()));
            newUser.setUpdatedDate(Timestamp.from(Instant.now()));

            userRepository.save(newUser);

            return new UserDto(
                    newUser.getUid(),
                    newUser.getEmail(),
                    newUser.getName(),
                    newUser.getToken(),
                    newUser.getTotalTryon(),
                    newUser.getTotalGenerate(),
                    null, // Tidak ada referral code saat pertama kali dibuat
                    newUser.getCreatedDate(),
                    newUser.getUpdatedDate()
            );
        }
    }


    @Override
    public UserDto getUserByUID(String uid) {
        Optional<User> userOpt = userRepository.findById(uid);
        return userOpt.map(this::convertToDto).orElse(null);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(String uid, UserDto updatedUser) {
        Optional<User> userOpt = userRepository.findById(uid);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setToken(updatedUser.getToken());
            user.setTotalTryon(updatedUser.getTotalTryon());
            user.setTotalGenerate(updatedUser.getTotalGenerate());
            user.setUpdatedDate(Timestamp.from(Instant.now()));

            // Update Referral jika ada
            if (updatedUser.getReferralCode() != null) {
                Referral referral = new Referral();
                referral.setReferralCode(updatedUser.getReferralCode());
                user.setReferral(referral);
            }

            userRepository.save(user);
            return convertToDto(user);
        }
        return null;
    }

    @Override
    public void deleteUser(String uid) {
        userRepository.deleteById(uid);
    }

    private UserDto convertToDto(User user) {
        String referralCode = (user.getReferral() != null) ? user.getReferral().getReferralCode() : null;
        return new UserDto(
                user.getUid(),
                user.getEmail(),
                user.getName(),
                user.getToken(),
                user.getTotalTryon(),
                user.getTotalGenerate(),
                referralCode,
                user.getCreatedDate(),
                user.getUpdatedDate()
        );
    }
}

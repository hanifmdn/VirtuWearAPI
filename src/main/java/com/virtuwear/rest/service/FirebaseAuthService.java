package com.virtuwear.rest.service;

import com.virtuwear.rest.entity.User;
import com.virtuwear.rest.repository.UserRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FirebaseAuthService {

    private final UserRepository userRepository;

    public FirebaseAuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User verifyTokenAndSaveUser(String idToken) throws Exception {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
        String uid = decodedToken.getUid();
        String email = decodedToken.getEmail();
        String name = decodedToken.getName();

        Optional<User> existingUser = userRepository.findByUid(uid);
        if (existingUser.isPresent()) {
            return existingUser.get();
        }

        User newUser = new User();
        newUser.setUid(uid);
        newUser.setEmail(email);
        newUser.setName(name);
        newUser.setToken(0);
        newUser.setTotalTryon(0);
        newUser.setTotalGenerate(0);

        return userRepository.save(newUser);
    }
}


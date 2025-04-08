package com.stoxport.stoxportapp.service;

import com.stoxport.stoxportapp.dto.SignupRequest;
import com.stoxport.stoxportapp.model.User;
import com.stoxport.stoxportapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(SignupRequest signupRequest) {
        // Create unique user id
        String uniqueUserId = "STX-" + UUID.randomUUID().toString();
        User user = new User();
        user.setUserId(uniqueUserId);
        user.setFullName(signupRequest.getFullName());
        user.setEmail(signupRequest.getEmail());
        user.setMobile(signupRequest.getMobile());
        // Convert DOB string (MM-DD-YYYY) to ISO (YYYY-MM-DD)
        user.setDob(LocalDate.parse(convertDOBFormat(signupRequest.getDob())));
        // In production, password must be hashed
        user.setPassword(signupRequest.getPassword());
        return userRepository.save(user);
    }

    public Optional<User> findByEmailOrMobile(String identifier) {
        Optional<User> userOpt = userRepository.findByEmail(identifier);
        if (!userOpt.isPresent()) {
            userOpt = userRepository.findByMobile(identifier);
        }
        return userOpt;
    }

    // Additional method to find a user by email specifically (used in reset password flow)
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Method to update the user's password (new password should be hashed in production)
    public void updatePassword(User user, String newPassword) {
        user.setPassword(newPassword);
        userRepository.save(user);
    }

    // Helper method to convert DOB from MM-DD-YYYY to ISO (YYYY-MM-DD)
    private String convertDOBFormat(String dob) {
        String[] parts = dob.split("-");
        return parts[2] + "-" + parts[0] + "-" + parts[1];
    }
}



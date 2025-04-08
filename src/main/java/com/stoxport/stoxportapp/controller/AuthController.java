package com.stoxport.stoxportapp.controller;

import com.stoxport.stoxportapp.dto.*;
import com.stoxport.stoxportapp.model.User;
import com.stoxport.stoxportapp.service.OTPService;
import com.stoxport.stoxportapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private OTPService otpService;

    // Signup: Create account and send OTP for email and mobile verification
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest signupRequest) {
        User user = userService.registerUser(signupRequest);
        // Send OTP for email verification (simulate)
        otpService.createOTP(user.getUserId(), "EMAIL_SIGNUP");
        // Send OTP for mobile verification (simulate)
        otpService.createOTP(user.getUserId(), "MOBILE_SIGNUP");
        return ResponseEntity.ok("User registered. Please verify your email and mobile with OTP.");
    }

    // OTP verification endpoint (for both signup and login)
    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOTP(@Valid @RequestBody OTPRequest otpRequest) {
        boolean valid = otpService.verifyOTP(otpRequest.getUserId(), otpRequest.getOtp(), otpRequest.getOtpType());
        if (valid) {
            // Optionally, update user verification status if signup OTP
            return ResponseEntity.ok("OTP verified successfully.");
        } else {
            return ResponseEntity.badRequest().body("Invalid or expired OTP.");
        }
    }

    // Login: Validate credentials and then send OTP to mobile
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        Optional<User> userOpt = userService.findByEmailOrMobile(loginRequest.getIdentifier());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(loginRequest.getPassword())) {
                // Create OTP for login (simulate sending to mobile)
                otpService.createOTP(user.getUserId(), "LOGIN");
                return ResponseEntity.ok("Password verified. OTP sent to registered mobile.");
            } else {
                return ResponseEntity.badRequest().body("Invalid password.");
            }
        }
        return ResponseEntity.badRequest().body("User not found.");
    }

//    // Reset Password: Request OTP on email, then verify and update password
//    @PostMapping("/reset-password")
//    public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPasswordRequest request) {
//        // Assume OTP is already sent to email as part of the reset flow.
//        boolean otpValid = otpService.verifyOTP(
//                userService.findByEmailOrMobile(request.getEmail()).get().getUserId(),
//                request.getOtp(),
//                "RESET_PASSWORD"
//        );
//        if (otpValid) {
//            userService.resetPassword(request);
//            return ResponseEntity.ok("Password reset successfully.");
//        }
//        return ResponseEntity.badRequest().body("OTP verification failed.");
//    }

    // Endpoint for requesting reset password OTP
    @PostMapping("/request-reset-password")
    public ResponseEntity<?> requestResetPassword(@Valid @RequestBody ResetPasswordRequest request) {
        Optional<User> userOpt = userService.findByEmail(request.getEmail());
        if (!userOpt.isPresent()) {
            return ResponseEntity.badRequest().body("Email not found.");
        }
        User user = userOpt.get();
        // Create OTP with type "RESET_PASSWORD" and simulate sending by logging it
        otpService.createOTP(user.getUserId(), "RESET_PASSWORD");
        return ResponseEntity.ok("OTP sent to your registered email.");
    }

    // Endpoint for submitting OTP and new password
    @PostMapping("/submit-reset-password")
    public ResponseEntity<?> submitResetPassword(@Valid @RequestBody SubmitResetPasswordRequest request) {
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("New Password and Confirm Password do not match.");
        }
        Optional<User> userOpt = userService.findByEmail(request.getEmail());
        if (!userOpt.isPresent()) {
            return ResponseEntity.badRequest().body("Email not found.");
        }
        User user = userOpt.get();
        // Verify the OTP for RESET_PASSWORD type
        boolean isOtpValid = otpService.verifyOTP(user.getUserId(), request.getOtp(), "RESET_PASSWORD");
        if (!isOtpValid) {
            return ResponseEntity.badRequest().body("Invalid or expired OTP.");
        }
        // Update password if OTP is valid
        userService.updatePassword(user, request.getNewPassword());
        return ResponseEntity.ok("Password updated successfully.");
    }
}


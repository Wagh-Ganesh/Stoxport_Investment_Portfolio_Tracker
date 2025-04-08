package com.stoxport.stoxportapp.service;

import com.stoxport.stoxportapp.model.OTP;
import com.stoxport.stoxportapp.repository.OTPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class OTPService {

    @Autowired
    private OTPRepository otpRepository;

    // Generate a 6-digit OTP
    public String generateOTP() {
        Random random = new Random();
        int otpInt = 100000 + random.nextInt(900000);
        return String.valueOf(otpInt);
    }

    public OTP createOTP(String userId, String otpType) {
        OTP otp = new OTP();
        otp.setUserId(userId);
        otp.setOtp(generateOTP());
        otp.setOtpType(otpType);
        otp.setExpiresAt(LocalDateTime.now().plusMinutes(5));
        // Save OTP record to database
        OTP savedOtp = otpRepository.save(otp);

        // Log the OTP (simulate sending)
        System.out.println("OTP for " + otpType + " for user " + userId + " is: " + savedOtp.getOtp());
        return savedOtp;
    }

    public boolean verifyOTP(String userId, String otpInput, String otpType) {
        Optional<OTP> otpOpt = otpRepository.findByUserIdAndOtpType(userId, otpType);
        if (otpOpt.isPresent()) {
            OTP otp = otpOpt.get();
            if (otp.getExpiresAt().isAfter(LocalDateTime.now()) && otp.getOtp().equals(otpInput)) {
                // OTP is valid; you might want to delete or mark it as used.
                otpRepository.delete(otp);
                return true;
            }
        }
        return false;
    }
}


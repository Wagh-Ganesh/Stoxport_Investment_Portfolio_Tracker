package com.stoxport.stoxportapp.repository;

import com.stoxport.stoxportapp.model.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OTPRepository extends JpaRepository<OTP, Long> {
    Optional<OTP> findByUserIdAndOtpType(String userId, String otpType);
}


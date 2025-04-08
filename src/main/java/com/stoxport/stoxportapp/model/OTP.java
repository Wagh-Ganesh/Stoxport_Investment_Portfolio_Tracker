package com.stoxport.stoxportapp.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "otps")
public class OTP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_id", nullable = false)
    private String userId;

    @Column(nullable = false)
    private String otp;

    @Column(name="otp_type", nullable = false)
    private String otpType;

    @Column(name="created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name="expires_at", nullable = false)
    private LocalDateTime expiresAt;

    public OTP() {
    }

    public OTP(Long id, String userId, String otp, String otpType, LocalDateTime createdAt, LocalDateTime expiresAt) {
        this.id = id;
        this.userId = userId;
        this.otp = otp;
        this.otpType = otpType;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getOtpType() {
        return otpType;
    }

    public void setOtpType(String otpType) {
        this.otpType = otpType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }
}


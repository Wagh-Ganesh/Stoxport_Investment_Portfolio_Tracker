package com.stoxport.stoxportapp.dto;

import jakarta.validation.constraints.NotBlank;

public class OTPRequest {
    @NotBlank
    private String userId;
    @NotBlank
    private String otp;
    @NotBlank
    private String otpType; // e.g., "EMAIL_SIGNUP", "LOGIN", "RESET_PASSWORD"

    public OTPRequest(String userId, String otp, String otpType) {
        this.userId = userId;
        this.otp = otp;
        this.otpType = otpType;
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
}


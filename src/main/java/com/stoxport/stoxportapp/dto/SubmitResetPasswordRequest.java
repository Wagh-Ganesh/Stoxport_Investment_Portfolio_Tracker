package com.stoxport.stoxportapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class SubmitResetPasswordRequest {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String otp;

    @NotBlank
    private String newPassword;

    @NotBlank
    private String confirmPassword;

    public SubmitResetPasswordRequest(String email, String otp, String newPassword, String confirmPassword) {
        this.email = email;
        this.otp = otp;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getOtp() {
        return otp;
    }
    public void setOtp(String otp) {
        this.otp = otp;
    }
    public String getNewPassword() {
        return newPassword;
    }
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    public String getConfirmPassword() {
        return confirmPassword;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}


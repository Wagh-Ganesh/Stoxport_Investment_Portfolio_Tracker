package com.stoxport.stoxportapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ResetPasswordRequest {
    @NotBlank
    @Email
    private String email;

    public ResetPasswordRequest(String email) {
        this.email = email;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}



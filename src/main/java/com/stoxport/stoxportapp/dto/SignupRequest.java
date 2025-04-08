package com.stoxport.stoxportapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class SignupRequest {
    @NotBlank
    private String fullName;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String mobile;
    @NotBlank
    private String dob; // Expecting MM-DD-YYYY
    @NotBlank
    private String password;

    public SignupRequest(String fullName, String email, String mobile, String dob, String password) {
        this.fullName = fullName;
        this.email = email;
        this.mobile = mobile;
        this.dob = dob;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


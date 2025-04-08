package com.stoxport.stoxportapp.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_id", unique = true, nullable = false)
    private String userId; // generated unique ID

    @Column(name="full_name", nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String mobile;

    @Column(nullable = false)
    private LocalDate dob;

    @Column(nullable = false)
    private String password;

    @Column(name="email_verified")
    private Boolean emailVerified = false;

    @Column(name="mobile_verified")
    private Boolean mobileVerified = false;

    @Column(name="created_at")
    private LocalDateTime createdAt = LocalDateTime.now();


    public User() {

    }

    public User(Long id, String userId, String fullName, String email, String mobile, LocalDate dob, String password, Boolean emailVerified, Boolean mobileVerified, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.mobile = mobile;
        this.dob = dob;
        this.password = password;
        this.emailVerified = emailVerified;
        this.mobileVerified = mobileVerified;
        this.createdAt = createdAt;
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public Boolean getMobileVerified() {
        return mobileVerified;
    }

    public void setMobileVerified(Boolean mobileVerified) {
        this.mobileVerified = mobileVerified;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}


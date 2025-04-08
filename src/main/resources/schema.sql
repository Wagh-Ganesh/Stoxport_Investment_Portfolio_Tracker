-- File: src/main/resources/schema.sql
-- --------------------------------------------------------
-- Database: stoxportdb
-- Version: 1.0
-- Created: 2023-12-01
-- Purpose: Initial schema for StoxPort investment tracker
-- --------------------------------------------------------

-- Create User Table
CREATE TABLE IF NOT EXISTS users (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     user_id VARCHAR(50) UNIQUE NOT NULL,
                                     full_name VARCHAR(100) NOT NULL,
                                     email VARCHAR(100) UNIQUE NOT NULL,
                                     mobile VARCHAR(15) UNIQUE NOT NULL,
                                     dob DATE NOT NULL,
                                     password VARCHAR(255) NOT NULL,
                                     email_verified BOOLEAN DEFAULT FALSE,
                                     mobile_verified BOOLEAN DEFAULT FALSE,
                                     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create OTP Table (to store temporary OTPs)
CREATE TABLE IF NOT EXISTS otps (
                                    id INT AUTO_INCREMENT PRIMARY KEY,
                                    user_id VARCHAR(50) NOT NULL,
                                    otp VARCHAR(6) NOT NULL,
                                    otp_type VARCHAR(20) NOT NULL, -- e.g., 'EMAIL_SIGNUP', 'MOBILE_SIGNUP', 'LOGIN', 'RESET_PASSWORD'
                                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                    expires_at TIMESTAMP NOT NULL
);


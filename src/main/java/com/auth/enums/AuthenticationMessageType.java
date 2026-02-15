package com.auth.enums;

public enum AuthenticationMessageType {
    LOGIN_SUCCESS( "Login successful"),
    INVALID_CREDENTIALS( "Invalid Credentials."),
    OTP_VERIFIED( "OTP verified successfully. User registered."),
    OTP_EXPIRED( "OTP has expired. Please request a new one."),
    EMAIL_ALREADY_EXISTS( "Profile with this email already exists."),
    INVALID_OTP("Invalid OTP. Verification failed."),
    OTP_SENT("Otp sent to email and valid only for 5 minutes.");

    private final String message;

    AuthenticationMessageType( String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

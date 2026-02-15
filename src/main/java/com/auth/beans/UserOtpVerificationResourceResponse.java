package com.auth.beans;

public class UserOtpVerificationResourceResponse {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UserOtpVerificationResourceResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}

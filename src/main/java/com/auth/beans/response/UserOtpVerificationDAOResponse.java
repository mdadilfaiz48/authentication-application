package com.auth.beans.response;

public class UserOtpVerificationDAOResponse {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UserOtpVerificationDAOResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}

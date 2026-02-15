package com.auth.beans.response;

public class UserSignInServiceResponse {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UserSignInServiceResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}

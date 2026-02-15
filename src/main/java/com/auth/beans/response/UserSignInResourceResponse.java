package com.auth.beans.response;

public class UserSignInResourceResponse {
    private String message; // login success or login failure

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UserSignInResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}

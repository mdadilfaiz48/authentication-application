package com.auth.exception;

public class AuthUserException {
   private String code;
   private String message;

    public AuthUserException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

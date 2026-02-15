package com.auth.util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;



public class AuthenticationUtils {
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final int digits = 4;
    public static String generateOtp(){
        if (digits <= 0) {
            throw new IllegalArgumentException("Digits must be greater than 0");
        }

        int max = (int) Math.pow(10, digits);

        int otp = secureRandom.nextInt(max);

        return String.format("%0" + digits + "d", otp);
    }
}

package com.auth.beans.request;

public class UserOtpVerificationServiceRequest {
    private String otp;

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    @Override
    public String toString() {
        return "UserOtpVerificationResourceRequest{" +
                "otp='" + maskOtp(otp) + '\'' +
                '}';
    }

    private String maskOtp(String otp) {
        if (otp == null || otp.length() < 2) {
            return "**";   // safety fallback
        }
        return "**" + otp.substring(otp.length() - 2);
    }


}

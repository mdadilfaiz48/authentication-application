package com.auth.service;

import com.auth.beans.request.UserOtpVerificationServiceRequest;
import com.auth.beans.request.UserRegistrationServiceRequest;
import com.auth.beans.request.UserSignInServiceRequest;
import com.auth.beans.response.UserOtpVerificationServiceResponse;
import com.auth.beans.response.UserRegistrationServiceResponse;
import com.auth.beans.response.UserSignInServiceResponse;

public interface IAuthService {
    public UserRegistrationServiceResponse initRegistration(UserRegistrationServiceRequest serviceRequest);

   public UserOtpVerificationServiceResponse confirmOtpVerification(UserOtpVerificationServiceRequest  serviceRequest);

   public UserSignInServiceResponse signInUser(UserSignInServiceRequest serviceRequest);
}

package com.auth.builder.request;

import com.auth.beans.request.*;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationRequestBuilder {
    public UserRegistrationServiceRequest buildServiceRequest(UserRegistrationResourceRequest resourceRequest ){
        UserRegistrationServiceRequest serviceRequest = new UserRegistrationServiceRequest();
        serviceRequest.setUsername(resourceRequest.getUsername());
        serviceRequest.setEmail(resourceRequest.getEmail());
        serviceRequest.setPassword(resourceRequest.getPassword());

        return serviceRequest;
    }

    public UserOtpVerificationServiceRequest buildOtpVerificationServiceRequest(UserOtpVerificationResourceRequest resourceRequest){
        UserOtpVerificationServiceRequest serviceRequest = new UserOtpVerificationServiceRequest();
        serviceRequest.setOtp(resourceRequest.getOtp());

        return serviceRequest;
    }

    public UserSignInServiceRequest buildSignInServiceRequest(UserSignInResourceRequest request){
        UserSignInServiceRequest serviceRequest = new UserSignInServiceRequest();
        serviceRequest.setEmail(request.getEmail());
        serviceRequest.setPassword(request.getPassword());

        return serviceRequest;
    }
}

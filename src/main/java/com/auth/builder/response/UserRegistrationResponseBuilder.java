package com.auth.builder.response;

import com.auth.beans.UserOtpVerificationResourceResponse;
import com.auth.beans.response.*;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationResponseBuilder {
  public UserRegistrationResourceResponse buildResourceResponse(UserRegistrationServiceResponse serviceResponse ){
        UserRegistrationResourceResponse resourceResponse = new UserRegistrationResourceResponse();
        resourceResponse.setMessage(serviceResponse.getMessage());
        return resourceResponse;
  }


   public UserRegistrationServiceResponse buildServiceResponse(String message){
        UserRegistrationServiceResponse serviceResponse = new UserRegistrationServiceResponse();
        serviceResponse.setMessage(message);
        return serviceResponse;
   }


   public UserOtpVerificationServiceResponse buildOtpVerificationServiceResponse(UserOtpVerificationDAOResponse daoResponse ){
        UserOtpVerificationServiceResponse serviceResponse = new UserOtpVerificationServiceResponse();
        serviceResponse.setMessage(daoResponse.getMessage());
        return serviceResponse;
   }

   public UserOtpVerificationResourceResponse buildOtpVerificationResourceResponse(UserOtpVerificationServiceResponse serviceResponse ){
        UserOtpVerificationResourceResponse resourceResponse = new UserOtpVerificationResourceResponse();
        resourceResponse.setMessage(serviceResponse.getMessage());
        return resourceResponse;
   }

   public UserSignInResourceResponse buildSignInResourceResponse(UserSignInServiceResponse serviceResponse ){
        UserSignInResourceResponse resourceResponse = new UserSignInResourceResponse();
        resourceResponse.setMessage(serviceResponse.getMessage());
        return resourceResponse;
   }
}

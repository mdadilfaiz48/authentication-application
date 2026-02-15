package com.auth.resource.impl;

import com.auth.beans.UserOtpVerificationResourceResponse;
import com.auth.beans.request.*;
import com.auth.beans.response.*;
import com.auth.builder.request.UserRegistrationRequestBuilder;
import com.auth.builder.response.UserRegistrationResponseBuilder;
import com.auth.resource.IAuthResource;
import com.auth.service.impl.AuthServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/auth/user",consumes = MediaType.APPLICATION_JSON_VALUE)
public class AuthResourceImpl implements IAuthResource {

    public static final Logger  LOGGER = LoggerFactory.getLogger(AuthResourceImpl.class);

   @Autowired
    private UserRegistrationRequestBuilder requestBuilder;
   @Autowired
    private UserRegistrationResponseBuilder responseBuilder;
   @Autowired
    private AuthServiceImpl service;


    @Override
    @PostMapping(value = "/register/init", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserRegistrationResourceResponse registerUser(@RequestBody UserRegistrationResourceRequest resourceRequest) {
        LOGGER.info("api: /register/init called..... with username: {} and email: {}", resourceRequest.getUsername(), resourceRequest.getEmail());
        LOGGER.info("User {} with email {} registration initiating......... ", resourceRequest.getUsername(), resourceRequest.getEmail());
        LOGGER.info("userRegistrationResourceRequest received: {}", resourceRequest);

              LOGGER.debug("calling requestBuilder to build service request for user registration");
              UserRegistrationServiceRequest serviceRequest = requestBuilder.buildServiceRequest(resourceRequest);
              LOGGER.debug("built service request for user registration: {}", serviceRequest);

              LOGGER.debug("calling service to initiate user registration");
              UserRegistrationServiceResponse serviceResponse = service.initRegistration(serviceRequest);
              LOGGER.debug("received service response for user registration: {}", serviceResponse);

              LOGGER.debug("calling responseBuilder to build resource response for user registration");
              UserRegistrationResourceResponse resourceResponse = responseBuilder.buildResourceResponse(serviceResponse);
              LOGGER.debug("built resource response for user registration: {}", resourceResponse);

              LOGGER.info("User {} registration initiated ", resourceRequest.getUsername());

        return resourceResponse;
    }

    @Override
    @PostMapping (value = "/register/confirm", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserOtpVerificationResourceResponse verifyUserOtp(@RequestBody UserOtpVerificationResourceRequest request) {
        LOGGER.info("OTP verification request received: {}", request);

        LOGGER.debug("calling requestBuilder to build service request for OTP verification");
        UserOtpVerificationServiceRequest serviceRequest =  requestBuilder.buildOtpVerificationServiceRequest(request );
        LOGGER.debug("built service request for OTP verification: {}", serviceRequest);

        LOGGER.debug("calling service to confirm OTP verification");
        UserOtpVerificationServiceResponse serviceResponse = service.confirmOtpVerification(serviceRequest);
        LOGGER.debug("received service response for OTP verification: {}", serviceResponse);

        LOGGER.debug("calling responseBuilder to build resource response for OTP verification");
        UserOtpVerificationResourceResponse resourceResponse = responseBuilder.buildOtpVerificationResourceResponse(serviceResponse);
        LOGGER.debug("built resource response for OTP verification: {}", resourceResponse);

        LOGGER.info("OTP verification response: {}", resourceResponse);
        return resourceResponse;
    }

    @Override
    @PostMapping (value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserSignInResourceResponse signInUser(@RequestBody UserSignInResourceRequest request) {
        LOGGER.info("Login request received for user: {}",request);

        LOGGER.debug("calling requestBuilder to build service request for user sign-in");
        UserSignInServiceRequest serviceRequest = requestBuilder.buildSignInServiceRequest(request);
        LOGGER.debug("built service request for user sign-in: {}", serviceRequest);

        LOGGER.debug("calling service to sign in user");
        UserSignInServiceResponse serviceResponse =  service.signInUser(serviceRequest);
        LOGGER.debug("received service response for user sign-in: {}", serviceResponse); LOGGER.debug("calling responseBuilder to build resource response for user sign-in");

        LOGGER.debug("calling responseBuilder to build resource response for user sign-in");
        UserSignInResourceResponse resourceResponse =  responseBuilder.buildSignInResourceResponse(serviceResponse);
        LOGGER.debug("built resource response for user sign-in: {}", resourceResponse);

        LOGGER.info("Login response generated: {}", resourceResponse);
        return resourceResponse;
    }






}

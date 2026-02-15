package com.auth.service.impl;

import com.auth.beans.request.UserOtpVerificationServiceRequest;
import com.auth.beans.request.UserRegistrationServiceRequest;
import com.auth.beans.request.UserSignInServiceRequest;
import com.auth.beans.response.UserOtpVerificationServiceResponse;
import com.auth.beans.response.UserRegistrationServiceResponse;
import com.auth.beans.response.UserSignInServiceResponse;
import com.auth.client.SendInBlueClient;
import com.auth.dao.IProfileDAO;
import com.auth.dao.IVerificationDAO;
import com.auth.entity.UserProfile;
import com.auth.entity.Verification;
import com.auth.enums.AuthenticationMessageType;
import com.auth.service.IAuthService;
import com.auth.util.AuthenticationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.auth.enums.AuthenticationMessageType.INVALID_CREDENTIALS;
import static com.auth.resource.impl.AuthResourceImpl.LOGGER;

@Service
public class AuthServiceImpl implements IAuthService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private IVerificationDAO verificationDAO;

    @Autowired
    private IProfileDAO profileDAO;

    @Autowired
    private SendInBlueClient sendInBlueClient;


    @Override
    public UserRegistrationServiceResponse initRegistration(UserRegistrationServiceRequest serviceRequest) {
        LOGGER.info("AuthServiceImpl.initRegistration------------------------->{}", serviceRequest);
        UserRegistrationServiceResponse response = new UserRegistrationServiceResponse();


        Verification verificationEntity = new Verification();
        verificationEntity.setName(serviceRequest.getUsername());
        verificationEntity.setEmail(serviceRequest.getEmail());
        verificationEntity.setPassword(serviceRequest.getPassword());

        String otp = AuthenticationUtils.generateOtp();
        LOGGER.info("Generated OTP: {}", otp);

        verificationEntity.setOtp(otp);
        verificationEntity.setOtpExpiryTime(LocalDateTime.now().plusMinutes(5));


        LOGGER.debug("calling sendInBlueClient");
        sendInBlueClient.sendOtpEmail(serviceRequest.getEmail(), otp);
        LOGGER.debug("called sendInBlueClient");

        // send in blue api call to send otp to email
        // verificationEntity.setOtp();
        // save verificationEntity to DB
        LOGGER.debug("saving data");
        verificationDAO.save(verificationEntity);
        LOGGER.debug("saved data {} to DB", verificationEntity);

        response.setMessage(AuthenticationMessageType.OTP_SENT.getMessage());

        LOGGER.info("AuthServiceImpl.initRegistration---------------->>>>>>>>>{}", response);
        return response;

    }

    @Override
    public UserOtpVerificationServiceResponse confirmOtpVerification(UserOtpVerificationServiceRequest serviceRequest) {
        LOGGER.info("AuthServiceImpl.confirmOtpVerification------------------------->{}", serviceRequest);

        UserOtpVerificationServiceResponse response = new UserOtpVerificationServiceResponse();

        LOGGER.debug("calling verificationDAO to find otp");
        Verification verificationEntity = verificationDAO.findByOtp(serviceRequest.getOtp());
        LOGGER.debug("called verificationDAO to find otp");

        if (verificationEntity == null) {
            response.setMessage(AuthenticationMessageType.INVALID_OTP.getMessage());
            return response;
        }
        LOGGER.debug("calling profileDAO to find email");
        UserProfile existingProfile = profileDAO.findByEmail(verificationEntity.getEmail());// null or not null
        LOGGER.debug("called profileDAO to find email");

        if (existingProfile != null) {
            response.setMessage(AuthenticationMessageType.EMAIL_ALREADY_EXISTS.getMessage());
            return response;
        }


        UserProfile profileEntity = new UserProfile();
        if (verificationEntity.getOtpExpiryTime().isBefore(LocalDateTime.now())) {
            response.setMessage(AuthenticationMessageType.OTP_EXPIRED.getMessage() );
            return response;
        }
        profileEntity.setEmail(verificationEntity.getEmail());
        profileEntity.setName(verificationEntity.getName());
        profileEntity.setPassword(verificationEntity.getPassword());
        profileEntity.setUserVerified(true);

        LOGGER.debug("saving profileEntity to DB");
        profileDAO.save(profileEntity);
        LOGGER.debug("saved profileEntity to DB");

        LOGGER.debug("deleting verificationEntity from DB by email");
        verificationDAO.deleteByEmail(verificationEntity.getEmail());
        LOGGER.debug("deleted verificationEntity from DB by email");

        response.setMessage(AuthenticationMessageType.OTP_VERIFIED.getMessage());


        LOGGER.info("AuthServiceImpl.confirmOtpVerification---------------->>>>>>>>>{}", response);

        return response;
    }

    @Override
    public UserSignInServiceResponse signInUser(UserSignInServiceRequest serviceRequest) {
        LOGGER.info("AuthServiceImpl.signInUser------------------------->{}", serviceRequest);

        UserSignInServiceResponse serviceResponse = new UserSignInServiceResponse();
        LOGGER.debug("calling profileDAO to find by email");
        UserProfile profileEntity = profileDAO.findByEmail(serviceRequest.getEmail());
        LOGGER.debug("called profileDAO to find by email");

        if (profileEntity != null) {
            if (serviceRequest.getPassword().equals(profileEntity.getPassword()) && serviceRequest.getEmail().equals(profileEntity.getEmail())) {
                serviceResponse.setMessage(AuthenticationMessageType.LOGIN_SUCCESS.getMessage());
                return serviceResponse;
            } else {
                LOGGER.warn("Invalid login attempt for email: {}", serviceRequest.getEmail());
                serviceResponse.setMessage( AuthenticationMessageType.INVALID_CREDENTIALS.getMessage());
                return serviceResponse;
            }

        }
        LOGGER.debug("AuthServiceImpl.signInUser---------------->>>>>>>>>{}", serviceResponse);
        return serviceResponse;
    }
}
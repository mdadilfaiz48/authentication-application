package com.auth.resource;

import com.auth.beans.UserOtpVerificationResourceResponse;
import com.auth.beans.request.UserOtpVerificationResourceRequest;
import com.auth.beans.request.UserRegistrationResourceRequest;
import com.auth.beans.request.UserSignInResourceRequest;
import com.auth.beans.response.UserRegistrationResourceResponse;
import com.auth.beans.response.UserSignInResourceResponse;

public interface IAuthResource {

    public UserRegistrationResourceResponse registerUser(UserRegistrationResourceRequest request);

    public UserOtpVerificationResourceResponse verifyUserOtp(UserOtpVerificationResourceRequest request);

    public UserSignInResourceResponse signInUser(UserSignInResourceRequest request);
}

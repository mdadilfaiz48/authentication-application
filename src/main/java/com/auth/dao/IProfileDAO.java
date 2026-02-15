package com.auth.dao;

import com.auth.entity.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfileDAO extends MongoRepository<UserProfile, String> {
    UserProfile findByEmail(String email);

//    public UserProfile findByOtp(String email, String otp);
}

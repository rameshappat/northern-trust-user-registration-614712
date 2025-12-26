package com.northerntrust.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final OtpService otpService;
    private final UserRepository userRepository;

    @Autowired
    public UserService(OtpService otpService, UserRepository userRepository) {
        this.otpService = otpService;
        this.userRepository = userRepository;
    }

    public void registerUser(UserRegistrationRequest request) throws Exception {
        // Logic to save user and send OTP
        User user = new User(request.getName(), request.getEmail(), request.getPassword());
        userRepository.save(user);
        otpService.sendOtp(user);
    }

    public void verifyOtp(String email, String otp) throws Exception {
        // Logic to verify OTP
        boolean isValid = otpService.verifyOtp(email, otp);
        if (!isValid) {
            throw new Exception("Invalid OTP");
        }
    }
}

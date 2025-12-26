package com.northerntrust.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OtpService {

    private final EmailService emailService;
    private final SmsService smsService;

    @Autowired
    public OtpService(EmailService emailService, SmsService smsService) {
        this.emailService = emailService;
        this.smsService = smsService;
    }

    public void sendOtp(User user) {
        // Generate and send OTP
        String otp = generateOtp();
        emailService.sendOtp(user.getEmail(), otp);
        smsService.sendOtp(user.getPhoneNumber(), otp);
    }

    public boolean verifyOtp(String email, String otp) {
        // Logic to verify OTP
        return true; // Placeholder
    }

    private String generateOtp() {
        // Generate a random OTP
        return "123456"; // Placeholder
    }
}

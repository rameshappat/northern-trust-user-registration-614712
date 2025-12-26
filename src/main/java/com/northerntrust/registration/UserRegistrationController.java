package com.northerntrust.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/register")
public class UserRegistrationController {

    private final UserService userService;

    @Autowired
    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegistrationRequest request) {
        try {
            userService.registerUser(request);
            return new ResponseEntity<>("Registration successful. Please check your email or SMS for OTP.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Registration failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        try {
            userService.verifyOtp(email, otp);
            return new ResponseEntity<>("OTP verified successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("OTP verification failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

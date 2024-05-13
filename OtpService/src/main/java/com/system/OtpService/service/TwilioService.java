package com.system.OtpService.service;

public interface TwilioService {

    String sendOtp(String mobielNumber);

    String verifyOtp(String otp);
}

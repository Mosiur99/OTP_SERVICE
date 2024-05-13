package com.system.OtpService.contoller;

import com.system.OtpService.service.TwilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TwilioController {

    private final TwilioService twilioService;

    @Autowired
    public TwilioController(TwilioService twilioService) {
        this.twilioService = twilioService;
    }

    @PostMapping("/send-otp")
    public String sendOtp(@RequestParam String mobileNumber) {
        return twilioService.sendOtp(mobileNumber);
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam String otp) {
        return twilioService.verifyOtp(otp);
    }
}

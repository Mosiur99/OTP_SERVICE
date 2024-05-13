package com.system.OtpService.service.impl;

import com.system.OtpService.config.TwilioConfig;
import com.system.OtpService.service.TwilioService;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class TwilioServiceImpl implements TwilioService {

    private final TwilioConfig twilioConfig;
    Map<String, String> otpMap = new HashMap<>();

    @Autowired
    public TwilioServiceImpl(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }

    @Override
    public String sendOtp(String mobielNumber) {
        String otp = generateOtp();
        PhoneNumber receiverPhoneNumber = new PhoneNumber(mobielNumber);
        PhoneNumber senderPhoneNumber = new PhoneNumber(twilioConfig.getMobileNumber());
        String msgBody = "Your One Time Password (OTP) is : " + otp;
        Message message = Message.creator(receiverPhoneNumber, senderPhoneNumber, msgBody).create();
        otpMap.put(msgBody, otp);
        return "OTP send successfully!!";
    }

    private String generateOtp() {
        int otp = (int) (Math.random() * 1000000);
        return String.format("%06d", otp);
    }

    @Override
    public String verifyOtp(String otp) {

        Set<String> keys = otpMap.keySet();
        for(String key : keys) {
            String value = otpMap.get(key);
            if(value.equals(otp)) {
                otpMap.remove(key, value);
                return "OTP verified successfully!!";
            }
        }

        return "Invalid OTP!!!";
    }
}

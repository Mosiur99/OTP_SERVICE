package com.system.OtpService;

import com.system.OtpService.config.TwilioConfig;
import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OtpServiceApplication {

	private final TwilioConfig twilioConfig;

	@Autowired
	public OtpServiceApplication(TwilioConfig twilioConfig) {
		this.twilioConfig = twilioConfig;
	}

	@PostConstruct
	public void setup() {
		Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
	}

	public static void main(String[] args) {
		SpringApplication.run(OtpServiceApplication.class, args);
	}

}

package com.karthick.Controller;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.karthick.OtpPackage.OtpStorage;
import com.karthick.Response.otpDetails;
import com.karthick.Service.impl.EmailService;

@RestController
public class EmailController {

	@Autowired
	private EmailService emailService;
	
	@GetMapping("/sent-otp")
	public ResponseEntity<?>EmailSentOtp(@RequestParam String email)
	{
		String otp=emailService.SendOtp(email);
		    
		return new ResponseEntity<>("your otp is SucessFully sent",HttpStatus.OK);
	}
	
	@PostMapping("validate-otp")
	
	public String EmailvalidationOtp(@RequestParam String email,@RequestParam String otp)
	{
		
		otpDetails otpDetail=OtpStorage.getOtpDetails(email);
		if(otpDetail==null)
		{
			 throw new IllegalArgumentException("you are not send otp");	
		}
	
		if(otpDetail==null)
		{
			return "your otp not found or Expired";
		}
		System.out.println(Instant.now().toEpochMilli());
		if(Instant.now().toEpochMilli()>otpDetail.getExpirationTime())
		{
			OtpStorage.removeOtpDetails(email);
			return "your otp is expired";
		}
				
		if(!otpDetail.getOtp().equals(otp))
		{
			return "otp is invalid";
		}
		
		OtpStorage.removeOtpDetails(email);
		return "otp validated Successfully!";
	}
}

package com.karthick.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.karthick.OtpPackage.OtpGenerate;
import com.karthick.OtpPackage.OtpStorage;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	public String SendOtp(String email) {
		String otp=OtpGenerate.GenerateOtp();
		 OtpStorage.storeOtp(email, otp);
		SimpleMailMessage mailMessage=new SimpleMailMessage();
		mailMessage.setTo(email);
		mailMessage.setSubject("your otp code");
		mailMessage.setText("your otp code is :"+otp);
		javaMailSender.send(mailMessage);
		return otp;
		
	}
}

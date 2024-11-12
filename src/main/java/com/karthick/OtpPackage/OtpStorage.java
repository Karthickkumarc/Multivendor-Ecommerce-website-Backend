package com.karthick.OtpPackage;

import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;

import com.karthick.Response.otpDetails;

public class OtpStorage {

	private static final ConcurrentHashMap<String ,otpDetails>otpstore=new ConcurrentHashMap<>();
	
	private static final long OTP_EXPIRATION_TIME=5*60*1000;
	
	public static void storeOtp(String email ,String otp) {
		
		otpDetails otpDetails=new otpDetails(otp, Instant.now().toEpochMilli()+OTP_EXPIRATION_TIME);
		otpstore.put(email, otpDetails);
	}
	
	public static otpDetails getOtpDetails(String email)
	{
		System.out.println(otpstore);
		return otpstore.get(email);
	}
	
	public static void removeOtpDetails(String email)
	{
		otpstore.remove(email);
	}
	
}

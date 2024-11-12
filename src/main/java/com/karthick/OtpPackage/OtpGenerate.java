package com.karthick.OtpPackage;

import java.security.SecureRandom;

public class OtpGenerate {

	private static final SecureRandom secureRandom=new SecureRandom();
	private static final int otpLength=6;
	public static String  GenerateOtp() {
   StringBuilder otp=new StringBuilder();
   
   
    for(int i=0;i<otpLength;i++)
    {
    	otp.append(secureRandom.nextInt(10));
    }
	return otp.toString();
	
}
}
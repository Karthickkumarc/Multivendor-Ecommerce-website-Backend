package com.karthick.Response;

public class otpDetails {

	private String otp;
	private Long expirationTime;
	
	
	public otpDetails(String otp, Long expirationTime) {
		super();
		this.otp = otp;
		this.expirationTime = expirationTime;
	}


	public String getOtp() {
		return otp;
	}





	public Long getExpirationTime() {
		return expirationTime;
	}


	
}

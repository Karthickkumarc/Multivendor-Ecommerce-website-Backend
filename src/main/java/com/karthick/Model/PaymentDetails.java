package com.karthick.Model;

import com.karthick.Domain.PaymentStatus;

import lombok.Data;

@Data
public class PaymentDetails {
	
	private String paymentId;
	
	private String razorPaymentLinkId;
	
	private String razorPaymentLinkReferenceID;
	
	private String razorPaymentLinkStatus;
	
	private String razorPaymentIzZWSP;
	
	private PaymentStatus status;

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getRazorPaymentLinkId() {
		return razorPaymentLinkId;
	}

	public void setRazorPaymentLinkId(String razorPaymentLinkId) {
		this.razorPaymentLinkId = razorPaymentLinkId;
	}

	public String getRazorPaymentLinkReferenceID() {
		return razorPaymentLinkReferenceID;
	}

	public void setRazorPaymentLinkReferenceID(String razorPaymentLinkReferenceID) {
		this.razorPaymentLinkReferenceID = razorPaymentLinkReferenceID;
	}

	public String getRazorPaymentLinkStatus() {
		return razorPaymentLinkStatus;
	}

	public void setRazorPaymentLinkStatus(String razorPaymentLinkStatus) {
		this.razorPaymentLinkStatus = razorPaymentLinkStatus;
	}

	public String getRazorPaymentIzZWSP() {
		return razorPaymentIzZWSP;
	}

	public void setRazorPaymentIzZWSP(String razorPaymentIzZWSP) {
		this.razorPaymentIzZWSP = razorPaymentIzZWSP;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}
	
	

}

package com.karthick.Service;

import java.util.Set;

import com.karthick.Model.Order;
import com.karthick.Model.PaymentOrder;
import com.karthick.Model.User;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentService {
	
	PaymentOrder CreateOrder(User user,Set<Order> orders);
	
	PaymentOrder getPaymentOrderById(Long paymentLinkId) throws Exception;
	
	PaymentOrder getPaymentOrderByPaymentId(String paymentLinkId) throws Exception;	
	
	Boolean PRoceedToPaymentOrder(PaymentOrder paymentOrder,String paymentId,String paymentLinkId) throws RazorpayException;
	
	
    PaymentLink CreateRazorPaymentLink(User user,Long amount,Long orderId);
    
    String CreateStripePaymentLink(User user ,Long amount, Long OrderId ) throws StripeException;

	
}

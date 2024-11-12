package com.karthick.Service.impl;

import java.util.Set;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.karthick.Domain.PaymentOrderStatus;
import com.karthick.Domain.PaymentStatus;
import com.karthick.Model.Order;
import com.karthick.Model.PaymentOrder;
import com.karthick.Model.User;
import com.karthick.Repository.OrderRepository;
import com.karthick.Repository.PaymentRepository;
import com.karthick.Service.PaymentService;
import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams.PaymentMethodType;
import com.stripe.param.financialconnections.SessionCreateParams;

import ch.qos.logback.core.net.server.Client;

@Service
public class PaymentServiceImplements implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	private String  apikey="apikey";
	private String apisecretKey="";
	
	private String stripSecretKey="";
	
	@Override
	
	public PaymentOrder CreateOrder(User user, Set<Order> orders) {
		
		Long amount=orders.stream().mapToLong(Order ::getTotalSellingPrice).sum();
		// TODO Auto-generated method stub
		PaymentOrder paymentOrder=new PaymentOrder();
		paymentOrder.setUser(user);
		paymentOrder.setAmount(amount);
		paymentOrder.setOrders(orders);
		return paymentRepository.save(paymentOrder);
	}

	@Override
	public PaymentOrder getPaymentOrderById(Long orderId) throws Exception {
		// TODO Auto-generated method stub
		return paymentRepository.findById(orderId).orElseThrow(()-> new Exception("Payment id not found"+orderId));
	}

	@Override
	public PaymentOrder getPaymentOrderByPaymentId(String orderId) throws Exception {
		// TODO Auto-generated method stub
		
		PaymentOrder paymentOrder=paymentRepository.findByPaymentLinkId(orderId);
		if(paymentOrder==null)
		{
			throw new Exception("Your order not found with provided paymentLink");
		}
		return paymentOrder;
	}

	@Override
	public Boolean PRoceedToPaymentOrder(PaymentOrder paymentOrder, String paymentId, String paymentLinkId) throws RazorpayException {
		// TODO Auto-generated method stub
		if(paymentOrder.getStatus().equals(PaymentOrderStatus.PENDING))
		{
			RazorpayClient razor=new RazorpayClient(apikey,apisecretKey);
			Payment payment=razor.payments.fetch(paymentId);
			String status=payment.get("status");
			if(status.equals("captured"))
			{
				Set<Order>orders=paymentOrder.getOrders();
				
				for(Order order:orders)
				{
					order.setPaymentstatus(PaymentStatus.COMPLETED);
					orderRepository.save(order);
				}
				paymentOrder.setStatus(PaymentOrderStatus.SUCCESS);
				paymentRepository.save(paymentOrder);
				return true;
			}
			paymentOrder.setStatus(PaymentOrderStatus.FAILURE);
			paymentRepository.save(paymentOrder);
			return false;
		}
		
		return false;
	}

	@Override
	public PaymentLink CreateRazorPaymentLink(User user, Long amount, Long orderId) {
		// TODO Auto-generated method stub
		
		amount=amount*100;
		try
		{
			RazorpayClient razor=new RazorpayClient(apikey,apisecretKey);
			JSONObject paymentLinkRequest=new JSONObject();
			paymentLinkRequest.put("amount", amount);
			paymentLinkRequest.put("currency","INR");
			
			JSONObject customer= new JSONObject();
			customer.put("name", user.getUserName());
			customer.put("email", user.getEmail());
		     paymentLinkRequest.put("customer", customer);
		     
		     JSONObject notify=new JSONObject();
		     notify.put("email",true);
		     paymentLinkRequest.put("notify", notify);
		     
		     paymentLinkRequest.put("callback_url", "http://localhost:3000/payment-sucees/"+orderId);
		     PaymentLink link=razor.paymentLink.create(paymentLinkRequest);
		     
		 
		     return link;
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public String CreateStripePaymentLink(User user, Long amount, Long OrderId) throws StripeException {
		// TODO Auto-generated method stub
		Stripe.apiKey=stripSecretKey;
		com.stripe.param.checkout.SessionCreateParams params=com.stripe.param.checkout.SessionCreateParams.builder()
				.addPaymentMethodType(PaymentMethodType.CARD)
				.setMode(com.stripe.param.checkout.SessionCreateParams.Mode.PAYMENT)
				.addLineItem(com.stripe.param.checkout.SessionCreateParams.LineItem.builder()
						.setQuantity(1L)
						.setPriceData(com.stripe.param.checkout.SessionCreateParams.LineItem.PriceData.builder()
								.setCurrency("INR")
								.setUnitAmount(amount*100)
								.setProductData(
										com.stripe.param.checkout.SessionCreateParams.LineItem.PriceData.ProductData
										.builder().setName("Karthickkumar c")
										.build()
										)
								.build())
						.build())
				        
				
				.build();
		Session session=Session.create(params);
		
		return session.getUrl();
	}

	

}

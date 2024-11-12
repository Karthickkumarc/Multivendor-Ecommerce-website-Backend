package com.karthick.Controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.karthick.Domain.PaymentMethod;
import com.karthick.Model.Address;
import com.karthick.Model.Cart;
import com.karthick.Model.Order;
import com.karthick.Model.OrderItem;
import com.karthick.Model.PaymentOrder;
import com.karthick.Model.User;
import com.karthick.Repository.PaymentRepository;
import com.karthick.Response.PaymentLinkResponse;
import com.karthick.Service.CartService;
import com.karthick.Service.OrderService;
import com.karthick.Service.PaymentService;
import com.karthick.Service.SellerReportService;
import com.karthick.Service.SellerService;
import com.karthick.Service.impl.USerServiceImpleMents;
import com.karthick.Service.impl.UserService;
import com.razorpay.PaymentLink;
import com.stripe.exception.StripeException;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private CartService cartService;
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private SellerReportService sellerReportService;
	@Autowired
	private USerServiceImpleMents uSerServiceImpleMents;
	
	@Autowired
	private PaymentService paymentService;

	@Autowired
	private PaymentRepository paymentRepository;
	
	@PostMapping("/createorder")
	public ResponseEntity<PaymentLinkResponse> CreateOrder(@RequestHeader("Authorization") String jwt ,@RequestBody Address shipAddress, @RequestParam PaymentMethod paymentMethod) throws StripeException
	{
		User user=uSerServiceImpleMents.findUserByJwtToken(jwt);
		
		Cart cart=cartService.findUserCart(user);
		
		Set<Order> orders=orderService.createOrder(user, shipAddress, cart);
		
		PaymentOrder paymentOrder=paymentService.CreateOrder(user, orders);
		
		PaymentLinkResponse paymentLinkResponse=new PaymentLinkResponse();
		
		if(paymentMethod.equals(PaymentMethod.RAZORPAY))
		{
			PaymentLink paymentLink=paymentService.CreateRazorPaymentLink(user,paymentOrder.getAmount() ,paymentOrder.getId());
		    String paymentlinkUrl=paymentLink.get("short_url");
		     String paymentLinkId=paymentLink.get("id");
		     paymentLinkResponse.setPayment_link_url(paymentlinkUrl);
		     paymentOrder.setPaymentLinkId(paymentLinkId);
		     paymentRepository.save(paymentOrder);
		     
		}
		else
		{
			String paymentUrl=paymentService.CreateStripePaymentLink(user, paymentOrder.getAmount(), paymentOrder.getId());
			paymentLinkResponse.setPayment_link_url(paymentUrl);
			
			
		}
		return new ResponseEntity<>(paymentLinkResponse,HttpStatus.OK);
	}
	
	@GetMapping("/{orderbyid}")
	public ResponseEntity<Order>GetORderbyId(@PathVariable Long id,@RequestHeader("Authorization") String jwt) throws Exception
	{
		User user=uSerServiceImpleMents.findUserByJwtToken(jwt);
		Order order=orderService.findOrderById(id);
		return new ResponseEntity<>(order,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/item/{orderitembyid}")
	public ResponseEntity<OrderItem>GetORditemerbyId(@PathVariable Long orderitembyid,@RequestHeader("Authorization") String jwt) throws Exception
	{
		User user=uSerServiceImpleMents.findUserByJwtToken(jwt);
		OrderItem orderitem=orderService.findbyId(orderitembyid);
		return new ResponseEntity<>(orderitem,HttpStatus.ACCEPTED);
		
	}
	
	
	@PutMapping("/{orderid}/cancel")
	public ResponseEntity<Order> cancelOrder(@RequestHeader("Authorization") String jwt,@PathVariable Long orderid) throws Exception
	{
		User user=uSerServiceImpleMents.findUserByJwtToken(jwt);
		Order order=orderService.CancelOrder(orderid, user);
		return ResponseEntity.ok(order);
	}
	
}

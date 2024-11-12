package com.karthick.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.karthick.Domain.OrderStatus;
import com.karthick.Model.Order;
import com.karthick.Model.Seller;
import com.karthick.Service.OrderService;
import com.karthick.Service.SellerService;

@RequestMapping("api/seller/order")
public class SellerOrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private SellerService sellerService;
	
	
@GetMapping("/sellersorder")
public ResponseEntity<List<Order>>GetSellerorder(@RequestHeader("Authorization")String jwt)
{
	Seller seller=sellerService.GetSellerProfile(jwt);
	
	List<Order> order=orderService.sellerOrder(seller.getId());
	return new ResponseEntity<>(order,HttpStatus.ACCEPTED);
}

@PatchMapping("/{orderid}/status/{orderStatus}")
public ResponseEntity<Order> UpdateOrderHandler(@RequestHeader("Authorization")String jwt,@PathVariable Long id,@PathVariable OrderStatus orderStatus) throws Exception
{
	Order orders=orderService.orderUpdateStatus(id, orderStatus);
	return new ResponseEntity<>(orders,HttpStatus.ACCEPTED);
}

}

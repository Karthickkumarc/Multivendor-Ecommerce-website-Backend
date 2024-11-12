package com.karthick.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.karthick.Model.Cart;
import com.karthick.Model.Coupon;
import com.karthick.Model.User;
import com.karthick.Response.ApiResponse;
import com.karthick.Service.CartService;
import com.karthick.Service.CouponService;
import com.karthick.Service.impl.USerServiceImpleMents;
import com.karthick.Service.impl.UserService;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {

	@Autowired
	private CouponService couponService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private USerServiceImpleMents uSerServiceImpleMents;
	
	@PostMapping("/apply")
	public ResponseEntity<Cart> ApplyCouponHandler(@RequestHeader("Authorization") String jwt,@RequestParam String apply,@RequestParam String code,@RequestParam double orderValue) throws Exception
	{
		User user=uSerServiceImpleMents.findUserByJwtToken(jwt);
		Cart cart;
		if(apply.equals("true"))
		{
			cart=couponService.ApplyCoupon(code, orderValue, user);
		}
		else {
			cart=couponService.RemoveCoupon(code, user);
		}
		return new  ResponseEntity<>(cart,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/admin/create")
	public ResponseEntity<Coupon>CreatCoupon(@RequestBody Coupon coupon)
	{
		Coupon createdcoupon=couponService.CreateCoupon(coupon);
		return new  ResponseEntity<>(createdcoupon,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/admin/delete/{id}")
	public ResponseEntity<ApiResponse>DeleteCouponHandler(@PathVariable Long couponId) throws Exception
	{
		couponService.deleteCoupon(couponId);
		ApiResponse apiResponse=new ApiResponse();
		return new  ResponseEntity<>(apiResponse,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/admin/all")
	public ResponseEntity<List<Coupon>> GetAllCoupon()
	{
		return new  ResponseEntity<>(couponService.findAllCoupon(),HttpStatus.ACCEPTED);
	}
	
	
}

package com.karthick.Service.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.karthick.Model.Cart;
import com.karthick.Model.Coupon;
import com.karthick.Model.User;
import com.karthick.Repository.CartRepository;
import com.karthick.Repository.CouponRepository;
import com.karthick.Repository.UserRepository;
import com.karthick.Service.CouponService;

@Service
public class CouponServiceImplements implements CouponService {

	@Autowired
	private CouponRepository couponRepository;
	
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private UserRepository userRepository;
	@Override
	public Cart ApplyCoupon(String code, double orderValue, User user) throws Exception {
		// TODO Auto-generated method stub
		
		Coupon coupon=couponRepository.findByCode(code);
		   Cart cart=cartRepository.findByUserId(user.getId());
		   if(coupon==null)
		   {
			   throw new Exception("Coupon Not Valid");
		   }
		   if(user.getCoupon().contains(coupon))
		   {
			   throw new Exception("Coupon Already used Or not Valid");
		   }
		   if(LocalDate.now().isAfter(coupon.getValidityEndDate()))
		   {
			   throw new Exception("Your coupon code is expired");
		   }
		   if(orderValue<coupon.getMinimumOrderValue())
		   {
			   throw new Exception("your order amount lesser than coupon amount and add some item and then try again  to apply coupon");
		   }
		   if(coupon.isActive() && LocalDate.now().isAfter(coupon.getValidityEndDate()) &&  LocalDate.now().isBefore(coupon.getValidityEndDate()))
		   {
			   user.getCoupon().add(coupon);
			   userRepository.save(user);
			   
			   double discountprice=(cart.getTotalSellingPrice()*coupon.getDiscountPercentage())/100;
			   cart.setTotalSellingPrice(cart.getTotalSellingPrice()-discountprice);
			   cart.setCouponcode(code);
			       cartRepository.save(cart);
		   }
		throw new Exception("coupon is not valid");
	}

	@Override
	public Cart RemoveCoupon(String code, User user) throws Exception {
		// TODO Auto-generated method stub
		
		Coupon coupon=couponRepository.findByCode(code);
		if(coupon==null)
		{
		throw new Exception("Coupon not found .. ....");
		}
		   Cart cart=cartRepository.findByUserId(user.getId());
		   double discountprice=(cart.getTotalSellingPrice()*coupon.getDiscountPercentage())/100;
		   cart.setTotalSellingPrice(cart.getTotalSellingPrice()+discountprice);
		   cart.setCouponcode(null);
		  
		   
		return  cartRepository.save(cart);
	}

	@Override
	public Coupon findByCouponId(Long id) throws Exception {
		// TODO Auto-generated method stub
		return couponRepository.findById(id).orElseThrow(()-> new Exception("Coupon not found :"+id));
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public Coupon CreateCoupon(Coupon coupon) {
		// TODO Auto-generated method stub
		return 	couponRepository.save(coupon);
	}

	@Override
	public List<Coupon> findAllCoupon() {
		// TODO Auto-generated method stub
		return couponRepository.findAll();
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteCoupon(Long id) throws Exception {
		// TODO Auto-generated method stub
		Coupon coupon= findByCouponId(id);
		if(coupon!=null)
		{
		couponRepository.deleteById(id);
		}
	}

}

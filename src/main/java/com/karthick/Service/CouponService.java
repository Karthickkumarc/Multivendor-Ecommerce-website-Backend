package com.karthick.Service;

import java.util.List;

import com.karthick.Model.Cart;
import com.karthick.Model.Coupon;
import com.karthick.Model.User;

public interface CouponService {

	Cart ApplyCoupon(String code,double orderValue,User user) throws Exception;
	Cart RemoveCoupon (String code,User user) throws Exception;
	Coupon findByCouponId(Long id) throws Exception;
	Coupon CreateCoupon(Coupon coupon);
	List<Coupon>findAllCoupon();
	void deleteCoupon(Long id) throws Exception;
}

package com.karthick.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karthick.Model.Coupon;

public interface CouponRepository  extends JpaRepository<Coupon, Long>{

	Coupon findByCode(String code);
}


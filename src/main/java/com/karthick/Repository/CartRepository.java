package com.karthick.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karthick.Model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
   
	Cart findByUserId(Long id);
}

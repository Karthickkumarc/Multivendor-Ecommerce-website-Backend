package com.karthick.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karthick.Model.Cart;
import com.karthick.Model.CartItem;
import com.karthick.Model.Product;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	CartItem findByCartAndProductAndSize(Cart cart, Product product,String size);
}

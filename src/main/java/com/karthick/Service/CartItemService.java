package com.karthick.Service;

import com.karthick.Model.CartItem;

public interface CartItemService {

	CartItem UpdateCartItem(Long UserId, Long id,CartItem cartItem) throws Exception;
	 void  removeCartItem(Long userId,Long cartItemId) throws Exception;
	 CartItem findCarItemById(Long id) throws Exception;
	 
}

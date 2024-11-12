package com.karthick.Service;

import com.karthick.Model.Cart;
import com.karthick.Model.CartItem;
import com.karthick.Model.Product;
import com.karthick.Model.User;

public interface CartService {

	public CartItem addCartItem(User user, Product product, String size, int quantity);
	public Cart findUserCart(User user);

}

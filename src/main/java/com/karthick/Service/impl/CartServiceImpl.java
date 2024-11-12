package com.karthick.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karthick.Model.Cart;
import com.karthick.Model.CartItem;
import com.karthick.Model.Product;
import com.karthick.Model.User;
import com.karthick.Repository.CartItemRepository;
import com.karthick.Repository.CartRepository;
import com.karthick.Service.CartService;
@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	@Override
	public CartItem addCartItem(User user, Product product, String size, int quantity) {
		// TODO Auto-generated method stub
		Cart cart=this.findUserCart(user);
		
		CartItem ispresent=cartItemRepository.findByCartAndProductAndSize(cart, product, size);
		    if(ispresent ==null)
		    {
		    	CartItem cartItem =new CartItem();
		    	cartItem.setProduct(product);
		    	cartItem.setQuantity(quantity);
		    	cartItem.setSize(size);
		    	int totalPrice=quantity*product.getSellingPrice();
		    	cartItem.setSellingPrice(totalPrice);
		    	int totalMrpPrice=quantity*product.getMrpPrice();
		    	cartItem.setMrpPrice(totalMrpPrice);
		    	cart.getCartItems().add(cartItem);
		    	cartItem.setCart(cart);
		    	return cartItemRepository.save(cartItem);
		    	
		    }
		return ispresent;
	}

	@Override
	public Cart findUserCart(User user) {
		// TODO Auto-generated method stub
		Cart cart=cartRepository.findByUserId(user.getId());
		
		int totalPrice=0;
		int totalDiscountPrice=0;
		int totalItem=0;
		

		for(CartItem cartItem : cart.getCartItems())
		{
			Integer mrpPrice = cartItem.getMrpPrice();
			int price = (mrpPrice != null) ? mrpPrice.intValue() : 0;
			totalPrice+=price;
			totalDiscountPrice+=cartItem.getSellingPrice();
			totalItem+=cartItem.getQuantity();
			
		}
	
		 cart.setTotalMrpPrice(totalPrice);
		 cart.setTotalSellingPrice(totalDiscountPrice);
		cart.setDiscount(CalculateDiscountPrice(totalPrice,totalDiscountPrice));
		cart.setTotalItem(totalItem);
		
		return cart;
	}
	private int CalculateDiscountPrice(int mrpPrice, int sellingPrice) {
		// TODO Auto-generated method stub

		if (mrpPrice <= 0) {
			return 0;
		}
		double discount = mrpPrice - sellingPrice;
		double discountPercentage = (discount / mrpPrice) * 100;
		return (int) discountPercentage;
	}

}

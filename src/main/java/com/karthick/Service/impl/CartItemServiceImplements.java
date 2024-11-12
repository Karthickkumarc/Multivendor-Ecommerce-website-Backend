package com.karthick.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karthick.Model.CartItem;
import com.karthick.Model.User;
import com.karthick.Repository.CartItemRepository;
import com.karthick.Service.CartItemService;

@Service
public class CartItemServiceImplements  implements CartItemService{

	@Autowired
	private CartItemRepository cartItemRepository;
	@Override
	public CartItem UpdateCartItem(Long UserId, Long id, CartItem cartItem) throws Exception {
		// TODO Auto-generated method stub
		
		CartItem item=findCarItemById(id);
		System.out.println("quantity"+cartItem.getQuantity());
		User cartItemUser=item.getCart().getUser();
		
		if(cartItemUser.getId().equals(UserId))
		{
			System.out.println("sysout");
			item.setQuantity(cartItem.getQuantity());
			item.setMrpPrice(item.getQuantity()*item.getProduct().getMrpPrice());
			item.setSellingPrice(item.getQuantity()*item.getProduct().getSellingPrice());
				return cartItemRepository.save(item);
		}
		 throw new Exception("You can't Update this CartITem");
	}

	@Override
	public void removeCartItem(Long userId, Long cartItemId) throws Exception {
		// TODO Auto-generated method stub
//		System.out.println(cartItemId);
	
		CartItem item=findCarItemById(cartItemId);
		System.out.println(userId);
		System.out.println(item.getCart().getUser().getId());
		User cartItemUser=item.getCart().getUser();
//		System.out.println(cartItemUser.getId());
		if(cartItemUser.getId().equals(userId))
		{
			System.out.println("hi");
			cartItemRepository.delete(item);
		}
		
	}

	@Override
	public CartItem findCarItemById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return cartItemRepository.findById(id).orElseThrow(()-> new Exception("CartItem  id not found"+id));
	}

}

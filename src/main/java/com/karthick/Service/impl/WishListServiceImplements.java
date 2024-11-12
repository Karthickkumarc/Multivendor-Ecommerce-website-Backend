package com.karthick.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karthick.Model.Product;
import com.karthick.Model.User;
import com.karthick.Model.WishList;
import com.karthick.Repository.WishListRepository;
import com.karthick.Service.WishListService;
@Service
public class WishListServiceImplements  implements WishListService{

	@Autowired
	private WishListRepository wishListRepository;
	@Override
	public WishList CreateWishList(User user) {
		// TODO Auto-generated method stub
		WishList wishList=new WishList();
		wishList.setUser(user);
		
		return wishListRepository.save(wishList);
	}

	@Override
	public WishList GetWishListByuserId(User user) {
		// TODO Auto-generated method stub
	WishList wishList=wishListRepository.findByUserId(user.getId());
		 if(wishList ==null)
		 {
			 wishList=CreateWishList(user);
		 }
		 return wishList;
	}

	@Override
	public WishList AddProductToWishList(User user, Product product) {
		// TODO Auto-generated method stub
		WishList wishList=GetWishListByuserId(user);
		if(wishList.getProducts().contains(product))
		{
			wishList.getProducts().remove(product);
		}
		else {
			wishList.getProducts().add(product);
		}
				
		return wishListRepository.save(wishList);
	}

}

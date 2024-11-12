package com.karthick.Service;

import com.karthick.Model.Product;
import com.karthick.Model.User;
import com.karthick.Model.WishList;

public interface WishListService {
	
	WishList CreateWishList(User user);
	WishList GetWishListByuserId(User user);
	WishList AddProductToWishList(User user,Product product);

}

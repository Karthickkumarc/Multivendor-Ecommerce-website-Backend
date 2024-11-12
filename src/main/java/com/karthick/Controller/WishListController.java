package com.karthick.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karthick.Exception.ProductException;
import com.karthick.Model.Product;
import com.karthick.Model.User;
import com.karthick.Model.WishList;
import com.karthick.Repository.WishListRepository;
import com.karthick.Service.ProductService;
import com.karthick.Service.WishListService;
import com.karthick.Service.impl.USerServiceImpleMents;

@RestController
@RequestMapping("/api/wishList")
public class WishListController {

	
	@Autowired
	private WishListService wishListService;
	
	@Autowired
	private  USerServiceImpleMents uSerServiceImpleMents;
	
	@Autowired
	
	private ProductService  productService;
	@GetMapping
	public ResponseEntity<WishList>GetWisHListByUserId(@RequestHeader("Authorization")String jwt)
	{
		User  user=uSerServiceImpleMents.findUserByJwtToken(jwt);
		WishList wishList=wishListService.GetWishListByuserId(user);
		return new ResponseEntity<>(wishList ,HttpStatus.OK);
	}
	
	
	@PostMapping("/addproduct/{productId}")
	public ResponseEntity<WishList>AddproductToWishList(@RequestHeader("Authorization")String jwt,@PathVariable Long productId) throws ProductException
	{
		Product product=productService.findProductId(productId);
		User  user=uSerServiceImpleMents.findUserByJwtToken(jwt);
		WishList addproducttowishList=wishListService.AddProductToWishList(user, product);
		return new ResponseEntity<>(addproducttowishList,HttpStatus.OK);
		
	}
	
	
}

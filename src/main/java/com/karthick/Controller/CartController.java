package com.karthick.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karthick.Exception.ProductException;
import com.karthick.Model.Cart;
import com.karthick.Model.CartItem;
import com.karthick.Model.Product;
import com.karthick.Model.User;
import com.karthick.Repository.CartItemRepository;
import com.karthick.Repository.CartRepository;
import com.karthick.Response.AddItemRequest;
import com.karthick.Response.ApiResponse;
import com.karthick.Service.CartItemService;
import com.karthick.Service.CartService;
import com.karthick.Service.ProductService;
import com.karthick.Service.impl.USerServiceImpleMents;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private ProductService productService;
	@Autowired
	private CartItemService cartItemService;

	@Autowired

	private CartService cartService;

	@Autowired
	private USerServiceImpleMents uSerServiceImpleMents;

	@GetMapping("/cartuser")
	public ResponseEntity<Cart> findUserCartHandler(@RequestHeader("Authorization") String jwt) {
		User user = uSerServiceImpleMents.findUserByJwtToken(jwt);
		System.out.println(user);
		Cart cart = cartService.findUserCart(user);

		return new ResponseEntity<>(cart, HttpStatus.OK);
	}

	@PostMapping("/addCart")
	public ResponseEntity<CartItem> addCartItem(@RequestBody AddItemRequest req,
			@RequestHeader("Authorization") String jwt) throws ProductException {
		User user = uSerServiceImpleMents.findUserByJwtToken(jwt);
		Product product = productService.findProductId(req.getProductId());
		CartItem cartItem = cartService.addCartItem(user, product, req.getSize(), req.getQuantity());
		ApiResponse response = new ApiResponse();
		response.setMessage("item Added succesfully in cart");
		return new ResponseEntity<>(cartItem, HttpStatus.OK);
	}

	@DeleteMapping("/{deleteId}")
	public ResponseEntity<ApiResponse> DeleteCartItems(@RequestHeader("Authorization") String jwt,
			@PathVariable("deleteId") Long id) throws Exception {

		User user = uSerServiceImpleMents.findUserByJwtToken(jwt);
		System.out.println(user.getId());
		cartItemService.removeCartItem(user.getId(), id);
		ApiResponse response = new ApiResponse();
		response.setMessage("item deleted succesfully in cart");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/getcartitem/{id}")
	public ResponseEntity<CartItem> GetcartItem(@PathVariable("id") Long id) throws Exception {
		return new ResponseEntity<>(cartItemService.findCarItemById(id), HttpStatus.OK);
	}

	@PutMapping("/update/{cartitemId}")
	public ResponseEntity<CartItem> UpdateCartItemHandler(@RequestHeader("Authorization") String jwt,
			@PathVariable("cartitemId") Long id, CartItem cartItem) throws Exception {
//		System.out.println(id);
		System.out.println(cartItem.getQuantity() + "quantitys");
	
		User user = uSerServiceImpleMents.findUserByJwtToken(jwt);
		
		CartItem updateCartItem = null;
		if (cartItem.getQuantity()>0) {
			System.out.println("hiiiip");
			updateCartItem = cartItemService.UpdateCartItem(user.getId(), id, cartItem);
		}
		return new ResponseEntity<>(updateCartItem, HttpStatus.OK);
	}
}

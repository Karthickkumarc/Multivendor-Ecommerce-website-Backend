package com.karthick.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.karthick.Exception.ProductException;
import com.karthick.Model.Product;
import com.karthick.Model.Seller;
import com.karthick.Response.CreateProductRequest;
import com.karthick.Service.ProductService;
import com.karthick.Service.SellerService;

@RestController
public class SellerProductContoller {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private SellerService sellerService;
	
	@GetMapping("/product")
	public ResponseEntity<List<Product>> getProductSellerId(@RequestHeader("authorization")String jwt)
	{
		Seller seller=sellerService.GetSellerProfile(jwt);
		List<Product>products= productService.GetProductBySellerId(seller.getId());
		return new ResponseEntity<>(products,HttpStatus.OK);
	}
	
	@PostMapping("/createproduct")
	public ResponseEntity<Product>CreateProduct(@RequestBody CreateProductRequest request,@RequestHeader("Authorization")String jwt)
	{
		Seller seller=sellerService.GetSellerProfile(jwt);
		Product product=productService.CreateProduct(request, seller);
		return new ResponseEntity<>(product,HttpStatus.OK);
	}
	
	@DeleteMapping("/{deleteProduct}")
	public ResponseEntity<Void> DeleteProduct(@PathVariable("deleteProduct") long id) throws ProductException
	{
		productService.deleteRequest(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

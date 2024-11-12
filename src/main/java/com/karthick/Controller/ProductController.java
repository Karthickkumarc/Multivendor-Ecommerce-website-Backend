package com.karthick.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.karthick.Exception.ProductException;
import com.karthick.Model.Product;
import com.karthick.Service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductException
	{
	   Product product=productService.findProductId(id);
	   return new  ResponseEntity<>(product,HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Product>> searcProduct(@RequestParam(required = false)String query)
	{
		System.out.println(query);
		List<Product> products=productService.searchProducts(query);
		return new  ResponseEntity<>(products,HttpStatus.OK);
			}
	
	@GetMapping("/getallproduct")
	public ResponseEntity<Page<Product>>getAllproducts(
			@RequestParam(required = false) String category,
			@RequestParam(required = false) String brand,
			@RequestParam(required = false)String color,
			@RequestParam(required = false) String size,
			@RequestParam(required = false) Integer minPrice,
			@RequestParam(required = false) Integer maxPrice,
			@RequestParam(required = false) Integer minDiscount,
			@RequestParam(required = false) String sort,
			@RequestParam(required = false) String stock,
			@RequestParam(required = false) Integer pageNumber	)
	{
		return new ResponseEntity<>(productService.GetAllProduct(category, brand, color, size, minPrice, maxPrice, minDiscount, sort, stock, pageNumber),HttpStatus.OK);
	}
}

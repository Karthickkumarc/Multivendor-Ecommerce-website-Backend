package com.karthick.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.karthick.Exception.ProductException;
import com.karthick.Model.Product;
import com.karthick.Model.Seller;
import com.karthick.Response.CreateProductRequest;

public interface ProductService {

	public Product CreateProduct(CreateProductRequest req, Seller seller);

	public void deleteRequest(Long productId);

	public Product updateProduct(long productId, Product Product);

	public Product findProductId(Long productId) throws ProductException;



	public Page<Product> GetAllProduct(String Category, String brand, String colors, String sizes, Integer minPrice,
			Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber

	);
	public List<Product>GetProductBySellerId(Long SellerId);

	List<Product> searchProducts(String query);

}

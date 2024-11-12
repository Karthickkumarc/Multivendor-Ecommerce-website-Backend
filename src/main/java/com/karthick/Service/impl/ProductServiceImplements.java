package com.karthick.Service.impl;

import java.awt.Point;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.ISBN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.karthick.Exception.ProductException;
import com.karthick.Model.Categorys;
import com.karthick.Model.Product;
import com.karthick.Model.Seller;
import com.karthick.Repository.CategoryRepository;
import com.karthick.Repository.ProdutRepository;
import com.karthick.Response.CreateProductRequest;
import com.karthick.Service.ProductService;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;

@Service
public class ProductServiceImplements implements ProductService {

	@Autowired
	private ProdutRepository produtRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Product CreateProduct(CreateProductRequest req, Seller seller) {
		// TODO Auto-generated method stub
         
		Categorys categorys1 = categoryRepository.findByCategoryId(req.getCategory());
		if (categorys1 != null) {
			Categorys categorys = new Categorys();
			categorys.setCategoryId(req.getCategory());
			categorys.setLevel(1);
			categorys1 = categoryRepository.save(categorys);
		}

		Categorys categorys2 = categoryRepository.findByCategoryId(req.getCategory2());
		if (categorys1 != null) {
			Categorys categorys = new Categorys();
			categorys.setCategoryId(req.getCategory2());
			categorys.setLevel(2);
			categorys.setParenCategorys(categorys1);
			categorys2 = categoryRepository.save(categorys);
		}

		Categorys categorys3 = categoryRepository.findByCategoryId(req.getCategory3());
		if (categorys1 != null) {
			Categorys categorys = new Categorys();
			categorys.setCategoryId(req.getCategory3());
			categorys.setLevel(3);
			categorys.setParenCategorys(categorys2);
			categorys2 = categoryRepository.save(categorys);
		}

		int DisCountPrice = CalculateDiscountPrice(req.getMrpPrice(), req.getSellingPrice());
		Product product = new Product();

		product.setSeller(seller);
		product.setCategory(categorys3);
		product.setColor(req.getColor());
		product.setDescription(req.getDescription());
		product.setCreatedAt(LocalDateTime.now());
		product.setTitle(req.getTitle());
		product.setSellingPrice(req.getSellingPrice());
		product.setImages(req.getImages());
		product.setMrpPrice(req.getMrpPrice());
		product.setSizes(req.getSizes());
		product.setDiscountPrice(DisCountPrice);

		return produtRepository.save(product);
	}

	private int CalculateDiscountPrice(int mrpPrice, int sellingPrice) {
		// TODO Auto-generated method stub

		if (mrpPrice <= 0) {
			throw new IllegalArgumentException("Actual price must be Greater than 0");
		}
		double discount = mrpPrice - sellingPrice;
		double discountPercentage = (discount / mrpPrice) * 100;
		return (int) discountPercentage;
	}

	@Override
	public void deleteRequest(Long productId) {
		// TODO Auto-generated method stub
		Product product = produtRepository.findProductById(productId);

		produtRepository.delete(product);
	}

	@Override
	public Product updateProduct(long productId, Product Product) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public Product findProductId(Long productId) throws ProductException {
		// TODO Auto-generated method stub
		return produtRepository.findById(productId).orElseThrow(()-> new ProductException("Product Id not found:"+productId));

	}

	@Override
	public List<Product> searchProducts(String query) {
		// TODO Auto-generated method stub
		return produtRepository.searchProduct(query);
	}

	@Override
	public Page<Product> GetAllProduct(String Category, String brand, String colors, String sizes, Integer minPrice,
			Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber) {
		// TODO Auto-generated method stub
		
		Specification<Product> spec = (root,query,criteriaBuilder) ->{
			List<Predicate> predicates=new ArrayList<>();
			
			if(Category!=null)
			{
				Join<Product, Categorys> categoryJoin=root.join("category");
				predicates.add(criteriaBuilder.equal(categoryJoin.get("categoryId"), Category));
			}
			
			if(colors!=null)
			{
				predicates.add(criteriaBuilder.equal(root.get("color"), colors));
			}
			
			if(sizes!=null)
			{	
				predicates.add(criteriaBuilder.equal(root.get("size"), sizes));
			}
			if(minPrice !=null)
			{
				predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("sellingPrice"), minPrice));
			}
			
			if(maxPrice !=null)
			{
				predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("sellingPrice"), maxPrice));
			}
			
			if(minDiscount !=null)
			{
				predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("discountPercent"), minDiscount));
			}
			
			if(stock!=null)
			{
				predicates.add(criteriaBuilder.equal(root.get("stock"), stock));
			}
			
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			
		};
		Pageable pageable;
		          if(sort!=null && !sort.isEmpty())
		          {
		        pageable=switch (sort)  {
		        	  case "price_low"->PageRequest.of(pageNumber!=null ?pageNumber:0, 10,Sort.by("sellinPrice").ascending());
		        		
		        	  case "price_high"->PageRequest.of(pageNumber!=null ?pageNumber:0, 10,Sort.by("sellinPrice").descending());
		        	
		        		  default->PageRequest.of(pageNumber!=null ?pageNumber:0, 10,Sort.unsorted());
		        			
		        			  
		        	  };
		          }
		          else {
		        	  pageable=PageRequest.of(pageNumber!=null ?pageNumber:0, 10,Sort.unsorted());
		          }
		return produtRepository.findAll(spec,pageable);
	}

	@Override
	public List<Product> GetProductBySellerId(Long SellerId) {
		// TODO Auto-generated method stub
		
		return produtRepository.findBySellerId(SellerId);
	}

}

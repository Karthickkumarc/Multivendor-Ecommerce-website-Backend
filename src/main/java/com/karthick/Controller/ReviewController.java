package com.karthick.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karthick.Exception.ProductException;
import com.karthick.Model.Product;
import com.karthick.Model.Review;
import com.karthick.Model.User;
import com.karthick.Response.ApiResponse;
import com.karthick.Response.CreateReviewRequest;
import com.karthick.Service.ProductService;
import com.karthick.Service.ReviewService;
import com.karthick.Service.impl.USerServiceImpleMents;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private USerServiceImpleMents uSerServiceImpleMents;
	
	@Autowired
	private ProductService  productService;
	
	@GetMapping("/product/{productId}/reviews")
	public ResponseEntity<List<Review>>GetReviewsByPRoductID(@PathVariable Long productID)
	{
		List<Review> reviews=reviewService.GetReviewByProductId(productID);
		return new ResponseEntity<>(reviews,HttpStatus.OK);
	}
	
	@PostMapping("/products/{productID}/reviews")
	public ResponseEntity<Review>WriteReview(@RequestHeader("Authorization")String jwt,@RequestBody CreateReviewRequest req,@PathVariable Long productId) throws ProductException
	{
	   Product product=productService.findProductId(productId);
	   User user=uSerServiceImpleMents.findUserByJwtToken(jwt);
	   Review review=reviewService.CreatReview(req, user, product);
		return new ResponseEntity<>(review,HttpStatus.OK);
	}
	
	@PatchMapping("/review/{reviewId}")
	public ResponseEntity<Review>UpdateReview(@RequestHeader("Authorization")String jwt,@RequestBody CreateReviewRequest req,@PathVariable Long reviewId) throws Exception {
		
		 User user=uSerServiceImpleMents.findUserByJwtToken(jwt);
		 Review review=reviewService.updateReview(reviewId, req.getReviewText(), req.getReviewRating(), user.getId());
		 
			return new ResponseEntity<>(review,HttpStatus.OK);
		
	}
	@DeleteMapping("/reviews/{reviewId}")
	public ResponseEntity<ApiResponse>DeleteReview(@PathVariable Long reviewId,@RequestHeader("Authorization")String jwt) throws Exception
	{
		User user=uSerServiceImpleMents.findUserByJwtToken(jwt);
		reviewService.DeleteReview(reviewId, user.getId());
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setMessage("Review Succesfully Deleted");
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
		
	}
	
	
}

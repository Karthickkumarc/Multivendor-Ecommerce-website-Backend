package com.karthick.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karthick.Model.Product;
import com.karthick.Model.Review;
import com.karthick.Model.User;
import com.karthick.Repository.ReviewRepository;
import com.karthick.Response.CreateReviewRequest;
import com.karthick.Service.ReviewService;
@Service
public class ReviewServiceImplements  implements ReviewService{

	@Autowired
	private ReviewRepository reviewRepository;
	@Override
	public Review CreatReview(CreateReviewRequest req, User user, Product product) {
		// TODO Auto-generated method stub
		Review review=new Review();
		review.setUser(user);
		review.setProduct(product);
		review.setReviewText(req.getReviewText());
		review.setRating(req.getReviewRating());
		review.setProductImages(req.getProductImages());
		product.getReviews().add(review);
		return reviewRepository.save(review);
	}

	@Override
	public List<Review> GetReviewByProductId(Long PRoductId) {
		// TODO Auto-generated method stub
		return reviewRepository.findByProductId(PRoductId);
	}

	@Override
	public Review updateReview(Long reviewId, String reviewText, Double Rating, Long userId) throws Exception {
		// TODO Auto-generated method stub
		Review review=this.GetReviewById(reviewId);
		if(review.getUser().getId().equals(userId))
		{
			review.setReviewText(reviewText);
			review.setRating(Rating);
			return reviewRepository.save(review);
		}
		throw new Exception("You can't update this review");
	}

	@Override
	public void DeleteReview(Long ReviewId, Long userId) throws Exception {
		// TODO Auto-generated method stub
		Review review=GetReviewById(ReviewId);
		if(!review.getUser().getId().equals(userId))
		{
			throw new Exception("you can't delete this review");
		}
		 reviewRepository.delete(review);
	}

	@Override
	public Review GetReviewById(Long reviewId) throws Exception {
		// TODO Auto-generated method stub
		return reviewRepository.findById(reviewId).orElseThrow(()->new Exception("Review id not found"+reviewId));
	}

}

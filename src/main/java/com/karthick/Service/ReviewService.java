package com.karthick.Service;

import java.util.List;

import com.karthick.Model.Product;
import com.karthick.Model.Review;
import com.karthick.Model.User;
import com.karthick.Response.CreateReviewRequest;

public interface ReviewService {

	Review CreatReview(CreateReviewRequest req,User user,Product product);
	List<Review>GetReviewByProductId(Long PRoductId);
	Review updateReview (Long reviewId,String reviewText,Double Rating,Long userId) throws Exception;
	void DeleteReview(Long ReviewId ,Long userId) throws Exception;
	Review GetReviewById(Long reviewId) throws Exception;
}

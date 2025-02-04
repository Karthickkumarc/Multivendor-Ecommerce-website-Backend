package com.karthick.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karthick.Model.Review;

public interface ReviewRepository  extends JpaRepository<Review, Long>{

	List<Review>findByProductId(Long productId);
}

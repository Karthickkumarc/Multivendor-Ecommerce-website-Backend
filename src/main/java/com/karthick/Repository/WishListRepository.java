package com.karthick.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karthick.Model.WishList;

public interface WishListRepository extends JpaRepository<WishList, Long> {

		WishList findByUserId(Long userId);
}

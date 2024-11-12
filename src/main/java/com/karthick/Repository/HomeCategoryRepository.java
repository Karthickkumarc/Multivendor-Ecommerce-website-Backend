package com.karthick.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karthick.Model.HomeCategory;

public interface HomeCategoryRepository  extends JpaRepository<HomeCategory, Long>{
	

}

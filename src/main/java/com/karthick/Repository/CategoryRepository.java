package com.karthick.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.karthick.Model.Categorys;

@Repository
public interface CategoryRepository  extends JpaRepository<Categorys, Long>{

	 Categorys findByCategoryId(String categoryId);
}

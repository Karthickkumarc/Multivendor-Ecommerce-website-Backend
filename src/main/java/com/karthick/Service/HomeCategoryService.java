package com.karthick.Service;

import java.util.List;

import com.karthick.Model.HomeCategory;

public interface HomeCategoryService {

	HomeCategory CreateHomeCategory(HomeCategory homeCategory);
	List<HomeCategory>CreateCategories(List<HomeCategory> homeCategories);
	HomeCategory updateHomeCategory(HomeCategory homeCategory,Long id) throws Exception;
	List<HomeCategory> getAllHomeCategories();
}

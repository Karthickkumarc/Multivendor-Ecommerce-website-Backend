package com.karthick.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karthick.Model.Categorys;
import com.karthick.Model.HomeCategory;
import com.karthick.Repository.HomeCategoryRepository;
import com.karthick.Service.HomeCategoryService;

@Service
public class HomeCategoryServiceImplemts implements HomeCategoryService {

	@Autowired
	private HomeCategoryRepository homeCategoryRepository;
	@Override
	public HomeCategory CreateHomeCategory(HomeCategory homeCategory) {
		// TODO Auto-generated method stub
		return homeCategoryRepository.save(homeCategory);
	}

	@Override
	public List<HomeCategory> CreateCategories(List<HomeCategory> homeCategories) {
		// TODO Auto-generated method stub
		if(homeCategoryRepository.findAll().isEmpty())
		{
			return homeCategoryRepository.saveAll(homeCategories);
		}
		return homeCategoryRepository.findAll();
	}

	@Override
	public HomeCategory updateHomeCategory(HomeCategory homeCategory, Long id) throws Exception {
		// TODO Auto-generated method stub
		HomeCategory existingHomeCategory=homeCategoryRepository.findById(id).orElseThrow(()->new Exception("HomeCategory id not found :"+id));
		if(homeCategory.getImage()!=null)
		{
			existingHomeCategory.setImage(homeCategory.getImage());
		}
		if(homeCategory.getCategoryId()!=null)
		{
			existingHomeCategory.setCategoryId(homeCategory.getCategoryId());
		}
		return homeCategoryRepository.save(existingHomeCategory);
	}

	@Override
	public List<HomeCategory> getAllHomeCategories() {
		// TODO Auto-generated method stub
		return homeCategoryRepository.findAll();
	}

	
}

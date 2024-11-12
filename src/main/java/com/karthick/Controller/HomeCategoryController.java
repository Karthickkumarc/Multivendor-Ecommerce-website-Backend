package com.karthick.Controller;

import java.util.List;

import org.hibernate.query.NativeQuery.ReturnableResultNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karthick.Model.Home;
import com.karthick.Model.HomeCategory;
import com.karthick.Service.HomeCategoryService;
import com.karthick.Service.HomeService;

@RestController
@RequestMapping
public class HomeCategoryController {

	@Autowired
	private HomeCategoryService homeCategoryService;
	
	@Autowired
	private HomeService homeService;
	@PostMapping("/home/category")
	public ResponseEntity<Home>CreateHomeCategoryHandler(@RequestBody List<HomeCategory> homeCategories)
	{
		List<HomeCategory>categories=homeCategoryService.CreateCategories(homeCategories);
		Home home=homeService.CreateHomePageData(categories);
				return new ResponseEntity<>(home,HttpStatus.OK);	
				
	}
	@GetMapping("/admin/home-category")
	public ResponseEntity<List<HomeCategory>>GetHomeCategory()
	{
		List<HomeCategory> categories=homeCategoryService.getAllHomeCategories();
		return new ResponseEntity<>(categories,HttpStatus.OK);
	}
	@PatchMapping("/admin/home-category/{id}")
	public ResponseEntity<HomeCategory>updateHomeCategoryHandler(@PathVariable Long id,@RequestBody HomeCategory homeCategory) throws Exception
	{
		HomeCategory updatecategory=homeCategoryService.updateHomeCategory(homeCategory, id);
		return new ResponseEntity<>(homeCategory,HttpStatus.OK);
	}
}

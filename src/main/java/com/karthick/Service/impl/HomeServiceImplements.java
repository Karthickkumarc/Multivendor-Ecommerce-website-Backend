package com.karthick.Service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karthick.Domain.HomeCategorySection;
import com.karthick.Model.Deal;
import com.karthick.Model.Home;
import com.karthick.Model.HomeCategory;
import com.karthick.Repository.DealRepository;
import com.karthick.Service.HomeService;
import com.stripe.model.tax.Registration.CountryOptions.Ca;

@Service
public class HomeServiceImplements  implements HomeService{

	@Autowired
	private DealRepository dealRepository;
	
	@Override
	public Home CreateHomePageData(List<HomeCategory> allcategories) {
		// TODO Auto-generated method stub
		List<HomeCategory> gridCategories=allcategories.stream()
				.filter(category ->category.getSection()== HomeCategorySection.GRID)
				.collect(Collectors.toList());
		
		List<HomeCategory> shopByCategories=allcategories.stream()
				.filter(category ->category.getSection()== HomeCategorySection.SHOP_BY_CATEGORIES)
				.collect(Collectors.toList());
		List<HomeCategory> electricCategories=allcategories.stream()
				.filter(category ->category.getSection()== HomeCategorySection.ELECTRIC_CATEGORIES)
				.collect(Collectors.toList());
		List<HomeCategory> dealCategories=allcategories.stream()
				.filter(category ->category.getSection()== HomeCategorySection.DEALS)
				.toList();
		
		List<Deal>createddeals=new ArrayList<>();
		if(dealRepository.findAll().isEmpty())
		{
			List<Deal> deal=allcategories.stream()
					.filter(category ->category.getSection()==HomeCategorySection.DEALS)
					.map(category -> new Deal(null ,10,category))
					.collect(Collectors.toList());
			createddeals=dealRepository.saveAll(deal);
		}
		else {
			createddeals=dealRepository.findAll();
		}
		
		Home home=new Home();
		  home.setGrid(gridCategories);
		  home.setElectricCategories(electricCategories);
		  home.setShopByCategories(shopByCategories);
		  home.setDealCategories(dealCategories);
		  home.setDeals(createddeals);
		return home;
	}

}

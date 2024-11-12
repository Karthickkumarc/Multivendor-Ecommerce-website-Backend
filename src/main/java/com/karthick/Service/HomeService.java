package com.karthick.Service;

import java.util.List;

import com.karthick.Model.Home;
import com.karthick.Model.HomeCategory;

public interface HomeService {

	public Home CreateHomePageData(List<HomeCategory> allcategories);
}

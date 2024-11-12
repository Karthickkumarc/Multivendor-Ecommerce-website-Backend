package com.karthick.Service;

import java.util.List;

import com.karthick.Model.Deal;

public interface DealService {

	List<Deal>getDeals();
	Deal CreateDeal(Deal deal);
	Deal UpdateDeal(Deal deal,Long id) throws Exception;
	void DeleteDeal(Long id) throws Exception;
}

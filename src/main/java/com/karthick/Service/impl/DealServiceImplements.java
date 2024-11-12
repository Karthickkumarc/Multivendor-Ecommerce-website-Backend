package com.karthick.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karthick.Model.Deal;
import com.karthick.Model.HomeCategory;
import com.karthick.Repository.DealRepository;
import com.karthick.Repository.HomeCategoryRepository;
import com.karthick.Service.DealService;

@Service
public class DealServiceImplements  implements DealService	{

	@Autowired
	private DealRepository dealRepository;
	
	@Autowired
	private HomeCategoryRepository homeCategoryRepository;
	@Override
	public List<Deal> getDeals() {
		// TODO Auto-generated method stub
		return dealRepository.findAll();
	}

	@Override
	public Deal CreateDeal(Deal deal) {
		// TODO Auto-generated method stub
		HomeCategory category=homeCategoryRepository.findById(deal.getCategory().getId()).orElseThrow(null);
		Deal newdeal=dealRepository.save(deal);
		newdeal.setCategory(category);
		newdeal.setDiscount(deal.getDiscount());
		return dealRepository.save(newdeal);
	}

	@Override
	public Deal UpdateDeal(Deal deal,Long id) throws Exception {
		// TODO Auto-generated method stub
		Deal existingDeal=dealRepository.findById(id).orElseThrow(()->new Exception("Deal id not found"));
		if(existingDeal!=null)
		{
			if(deal.getDiscount()!=null)
			{
				existingDeal.setDiscount(deal.getDiscount());
			}
			if(deal.getCategory()!=null)
			{
				existingDeal.setCategory(deal.getCategory());
			}
			return dealRepository.save(existingDeal);
		}
	 throw new Exception("Deal not found");
	}

	@Override
	public void DeleteDeal(Long id) throws Exception {
		// TODO Auto-generated method stub
		Deal deal=dealRepository.findById(id).orElseThrow(()-> new Exception("Deal not found"));
		dealRepository.delete(deal);
	}

}

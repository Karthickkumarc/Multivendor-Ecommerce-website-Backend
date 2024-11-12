package com.karthick.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karthick.Model.Seller;
import com.karthick.Model.SellerReport;
import com.karthick.Repository.SellerReportRepository;
import com.karthick.Service.SellerReportService;

@Service
public class SellerReportServiceImplements  implements SellerReportService{

	@Autowired
	private SellerReportRepository sellerReportRepository;
	@Override
	public SellerReport GetSellerReport(Seller seller) {
		// TODO Auto-generated method stub
		
		SellerReport sr=sellerReportRepository.findBySellerId(seller.getId());
		if(sr==null)
		{
			SellerReport sellerReport=new SellerReport();
			sellerReport.setSeller(seller);
			return sellerReportRepository.save(sellerReport);
		}
		return sr;
	}

	@Override
	public SellerReport UpdateSellerReport(SellerReport sellerReport) {
		// TODO Auto-generated method stub
		return sellerReportRepository.save(sellerReport);
	}
	

}

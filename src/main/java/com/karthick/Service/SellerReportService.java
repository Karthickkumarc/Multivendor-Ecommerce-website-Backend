package com.karthick.Service;

import com.karthick.Model.Seller;
import com.karthick.Model.SellerReport;

public interface SellerReportService {

	SellerReport GetSellerReport(Seller seller);
	
	SellerReport UpdateSellerReport(SellerReport sellerReport);
	
}

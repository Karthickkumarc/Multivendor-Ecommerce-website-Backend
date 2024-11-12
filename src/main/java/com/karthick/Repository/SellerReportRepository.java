package com.karthick.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karthick.Model.SellerReport;

public interface SellerReportRepository extends JpaRepository<SellerReport, Long>{

	SellerReport findBySellerId(Long sellerId);
}

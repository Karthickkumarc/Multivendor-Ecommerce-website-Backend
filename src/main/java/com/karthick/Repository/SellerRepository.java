package com.karthick.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.karthick.Domain.AccountStatus;
import com.karthick.Model.Seller;


public interface SellerRepository extends JpaRepository<Seller, Long> {

	Seller findBySellerName(String sellername);
	List<Seller>findByAccountstatus(AccountStatus status);
}

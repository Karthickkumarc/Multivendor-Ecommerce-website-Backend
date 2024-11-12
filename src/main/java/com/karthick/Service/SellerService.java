package com.karthick.Service;

import org.springframework.stereotype.Service;

import com.karthick.Domain.AccountStatus;
import com.karthick.Exception.SellerException;
import com.karthick.Model.Seller;
import com.karthick.Response.AuthResponse;

import java.util.List;


@Service
public interface SellerService {
	
	AuthResponse LoginSeller(Seller seller);
	Seller GetSellerByUsername(String username) throws Exception;
  Seller  GetSellerProfile(String jwt);
	Seller CreateSeller(Seller seller) throws Exception;
	Seller GetSellerById(Long id) throws SellerException;
	Seller UpdateSeller(Long id,Seller seller) throws Exception;
	 List<Seller> GetAllSellers(AccountStatus status);
	void DeleteSeller(Long id) throws Exception;
	Seller UpdateStatusAccountStatus(Long sellerId,AccountStatus status) throws Exception;
	Seller VerifySellerUsername(String username) throws Exception;
	

}

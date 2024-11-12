package com.karthick.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.karthick.Domain.AccountStatus;
import com.karthick.Exception.SellerException;
import com.karthick.Model.Seller;
import com.karthick.Model.SellerReport;
import com.karthick.Response.AuthResponse;
import com.karthick.Service.Authservice;
import com.karthick.Service.SellerReportService;
import com.karthick.Service.SellerService;

@RestController
//@RequestMapping("/seller")
public class SellerController {



	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private SellerReportService sellerReportService;
	
	@PostMapping("/sellerlogin")
	public ResponseEntity<AuthResponse>SellerLogin(@RequestBody Seller seller)
	{
		
		seller.setSellerName("seller_"+seller.getSellerName());
		AuthResponse response=sellerService.LoginSeller(seller);
		return  new ResponseEntity<>(response,HttpStatus.OK);
		
	}
	
	@PostMapping("/sellercreate")
	public ResponseEntity<Seller>SellerCreater(@RequestBody Seller seller) throws Exception
	{
		Seller response=sellerService.CreateSeller(seller);
		return new ResponseEntity<>(response,HttpStatus.OK);
		
	}
	@GetMapping("/{sellerId}")
	public ResponseEntity<Seller> GetSellerById(@PathVariable("sellerId") Long id) throws SellerException	
	{
		Seller seller=sellerService.GetSellerById(id);
		return  new ResponseEntity<>(seller,HttpStatus.OK);
	}
	
	@GetMapping("/profile")
	public ResponseEntity<Seller>getSellerByJwt(@RequestHeader("Authorization") String jwt) throws Exception
	{
		Seller seller=sellerService.GetSellerProfile(jwt);
		
		 return  new ResponseEntity<>(seller,HttpStatus.OK);
	}
	
	@PatchMapping("/updateseller")
	public ResponseEntity<Seller>UpdateSellerByJwt(@RequestHeader("Authorization") String jwt,@RequestBody Seller seller) throws Exception
	{
		Seller profile=sellerService.GetSellerProfile(jwt);	
		System.out.println(profile.getId());
		Seller Updateseller=sellerService.UpdateSeller(profile.getId(), seller);
		 return  new ResponseEntity<>(Updateseller,HttpStatus.OK);
		
	}
	
	@GetMapping("/report")
	public ResponseEntity<SellerReport>GetSellerReport(@RequestHeader("Authorization") String jwt)
	{
		Seller profile=sellerService.GetSellerProfile(jwt);
		SellerReport report=sellerReportService.GetSellerReport(profile);
		return  new ResponseEntity<>(report,HttpStatus.OK);
	}
	
	@GetMapping("/getseller")
	public ResponseEntity<List<Seller>>getAllSeller(@RequestParam(required = false)AccountStatus status)
	{
		List<Seller> sellers=sellerService.GetAllSellers(status);
		 return  new ResponseEntity<>(sellers,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteseller/{deleteid}")
	public ResponseEntity<?>DeleteSeller(@PathVariable("deleteid") Long id) throws Exception
	{
		sellerService.DeleteSeller(id);
		return ResponseEntity.noContent().build();
		
	}
}

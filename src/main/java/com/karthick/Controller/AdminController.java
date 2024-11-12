package com.karthick.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karthick.Domain.AccountStatus;
import com.karthick.Model.Seller;
import com.karthick.Service.SellerService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private SellerService sellerService;
	
	@PatchMapping("/seller/{id}/status/{status}")
	public ResponseEntity<Seller>UpdateSellerStatus(@PathVariable Long id,@PathVariable AccountStatus accountStatus) throws Exception
	{
		Seller seller=sellerService.UpdateStatusAccountStatus(id, accountStatus);
		return new ResponseEntity<>(seller,HttpStatus.OK);
	}
}

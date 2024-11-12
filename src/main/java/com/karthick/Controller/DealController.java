package com.karthick.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karthick.Model.Deal;
import com.karthick.Response.ApiResponse;
import com.karthick.Service.DealService;
import com.stripe.model.tax.Registration.CountryOptions.De;

@RestController
@RequestMapping("/api/deal")
public class DealController {

	@Autowired
	private DealService dealService;
	
	@PostMapping("/create")
	public ResponseEntity<Deal>CreateDealHandler(@RequestBody Deal deal)
	{
		Deal createDetails=dealService.CreateDeal(deal);
		return new ResponseEntity<>(createDetails,HttpStatus.OK);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Deal>UpdateDeal(@RequestBody Deal deal,@PathVariable Long id) throws Exception
	{
		Deal updateDeal=dealService.UpdateDeal(deal, id);
		return new ResponseEntity<>(updateDeal,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse>DeleteDeal(@PathVariable Long id) throws Exception
	{
	   dealService.DeleteDeal(id);	
	   ApiResponse apiResponse=new ApiResponse();
	   apiResponse.setMessage("Succesfully deal deleted");
	   apiResponse.setStatus(true);
	   return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}
	
}

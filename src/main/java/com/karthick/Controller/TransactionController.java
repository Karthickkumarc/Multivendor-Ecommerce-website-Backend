package com.karthick.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karthick.Model.Seller;
import com.karthick.Model.Transaction;
import com.karthick.Service.SellerService;
import com.karthick.Service.TransactionService;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private SellerService  sellerService;
	
	@GetMapping("/seller")
	public ResponseEntity<List<Transaction>>GetTransactionSeller(@RequestHeader("Authorization")String jwt)
	{
		Seller seller=sellerService.GetSellerProfile(jwt);
		List<Transaction> transactions=transactionService.GetTransactionBySellerId(seller);
		return new ResponseEntity<>(transactions,HttpStatus.OK);
		
		
	}
	@GetMapping("/alltransaction")
  public ResponseEntity<List<Transaction>> GetAllTransaction()
  {
		List<Transaction>transactions=transactionService.GetAllTransactions();
		return new ResponseEntity<>(transactions,HttpStatus.OK);
  }

}

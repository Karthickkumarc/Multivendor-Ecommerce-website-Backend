package com.karthick.Service;

import java.util.List;

import com.karthick.Exception.SellerException;
import com.karthick.Model.Order;
import com.karthick.Model.Seller;
import com.karthick.Model.Transaction;

public interface TransactionService {
	
	Transaction CreateTranscation(Order order) throws SellerException;
	
	List<Transaction>GetAllTransactions();
	List<Transaction>GetTransactionBySellerId(Seller seller);

}

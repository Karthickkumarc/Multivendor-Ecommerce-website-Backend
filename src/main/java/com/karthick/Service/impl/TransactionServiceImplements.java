package com.karthick.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karthick.Exception.SellerException;
import com.karthick.Model.Order;
import com.karthick.Model.Seller;
import com.karthick.Model.SellerReport;
import com.karthick.Model.Transaction;
import com.karthick.Repository.SellerReportRepository;
import com.karthick.Repository.SellerRepository;
import com.karthick.Repository.TransactionRepository;
import com.karthick.Service.SellerService;
import com.karthick.Service.TransactionService;

@Service
public class TransactionServiceImplements  implements TransactionService{

	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private SellerRepository sellerRepository;
	
	@Autowired
	private SellerService sellerService;
	
	@Override
	public Transaction CreateTranscation(Order order) throws SellerException {
		// TODO Auto-generated method stub
		Seller seller=sellerRepository.findById(order.getSellerId()).get();
		   Transaction transaction=new Transaction();
		   transaction.setSeller(seller);
		   transaction.setCustomer(order.getUser());
		   transaction.setOrder(order);
		return transactionRepository.save(transaction);
	}

	@Override
	public List<Transaction> GetAllTransactions() {
		// TODO Auto-generated method stub
		return transactionRepository.findAll();
	}

	@Override
	public List<Transaction> GetTransactionBySellerId(Seller seller) {
		// TODO Auto-generated method stub
		return transactionRepository.findBySellerId(seller.getId());
	}



}

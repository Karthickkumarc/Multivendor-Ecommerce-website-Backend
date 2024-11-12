package com.karthick.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karthick.Model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	List<Transaction>findBySellerId(Long id);
}

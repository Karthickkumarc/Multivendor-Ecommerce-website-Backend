package com.karthick.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karthick.Model.PaymentOrder;

public interface PaymentRepository extends JpaRepository<PaymentOrder, Long> {

	PaymentOrder findByPaymentLinkId(String orderId);

	
}


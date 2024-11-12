package com.karthick.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karthick.Model.OrderItem;

public interface OrderItemRepository  extends JpaRepository<OrderItem,Long>{

}

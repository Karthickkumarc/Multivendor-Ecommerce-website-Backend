package com.karthick.Service;

import java.util.List;
import java.util.Set;

import com.karthick.Domain.OrderStatus;
import com.karthick.Model.Address;
import com.karthick.Model.Cart;
import com.karthick.Model.Order;
import com.karthick.Model.OrderItem;
import com.karthick.Model.User;

public interface OrderService {

	Set<Order>createOrder	(User user ,Address shipAddress,Cart cart);
	Order findOrderById(Long id) throws Exception;
	List<Order>usersOrderHistory(Long userId);
	List<Order>sellerOrder(Long sellerId);
	Order orderUpdateStatus(Long orderId,OrderStatus orderStatus) throws Exception;
	Order CancelOrder(Long orderId ,User user) throws Exception;
	OrderItem findbyId(Long id) throws Exception;
}

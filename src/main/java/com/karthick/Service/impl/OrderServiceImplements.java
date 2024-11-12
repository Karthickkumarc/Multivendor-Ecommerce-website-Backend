package com.karthick.Service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karthick.Domain.OrderStatus;
import com.karthick.Domain.PaymentOrderStatus;
import com.karthick.Domain.PaymentStatus;
import com.karthick.Model.Address;
import com.karthick.Model.Cart;
import com.karthick.Model.CartItem;
import com.karthick.Model.Order;
import com.karthick.Model.OrderItem;
import com.karthick.Model.User;
import com.karthick.Repository.AddressRepository;
import com.karthick.Repository.OrderItemRepository;
import com.karthick.Repository.OrderRepository;
import com.karthick.Service.OrderService;

@Service
public class OrderServiceImplements implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private OrderItemRepository itemRepository;

	@Override
	public Set<Order> createOrder(User user, Address shipAddress, Cart cart) {
		// TODO Auto-generated method stub
		if(!user.getAddress().contains(shipAddress))
		{
			 	user.getAddress().add(shipAddress);
		}
		addressRepository.save(shipAddress);
		
		Set<Order>orders=new HashSet<>();
		
			Map<Long, List<CartItem>>itemsBySeller=cart.getCartItems().stream()
					.collect(Collectors.groupingBy(item->item.getProduct().getId()));
			
			for(Entry<Long, List<CartItem>> entry:itemsBySeller.entrySet())
			{
				System.out.println(entry.getKey());
				System.out.println(entry.getValue());
			}
			for(Entry<Long, List<CartItem>> entry:itemsBySeller.entrySet()) {
					Long sellerId=entry.getKey();
					List<CartItem> items=entry.getValue();
					
					int totalOrderPrice=items.stream().mapToInt(CartItem ::getSellingPrice).sum();
					int totalOrderItem=items.stream().mapToInt(CartItem::getQuantity).sum();
					
					Order createdOrder=new Order();
					
					createdOrder.setUser(user);
					createdOrder.setSellerId(sellerId);
					createdOrder.setTotalMrpPrice(totalOrderPrice);
					createdOrder.setTotalSellingPrice(totalOrderPrice);
					createdOrder.setShippingAddress(shipAddress);
					createdOrder.setTotalItem(totalOrderItem);
					createdOrder.setOrderStatus(OrderStatus.PENDING);
					createdOrder.getPaymentDetails().setStatus(PaymentStatus.PENDING);
					
					Order saveOrder=orderRepository.save(createdOrder);
					orders.add(saveOrder);
					
				List<OrderItem>orderItems=new ArrayList<>();
				 
				for(CartItem item:items)
				{
				
				      OrderItem newOrderItem=new OrderItem();
				      newOrderItem.setOrder(saveOrder);
				      newOrderItem.setMrpPrice(item.getMrpPrice());
				      newOrderItem.setProduct(item.getProduct());
				      newOrderItem.setQuantity(item.getQuantity());
				      newOrderItem.setSellingPrice(item.getSellingPrice());
				      newOrderItem.setSize(item.getSize());
				      newOrderItem.setUserId(item.getUserId());
				     saveOrder.getOrderItems().add(newOrderItem);
				     
				     OrderItem savOrderItem=itemRepository.save(newOrderItem);
				     orderItems.add(savOrderItem);
				}
			}
			
		
		return orders;
	}

	@Override
	public Order findOrderById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return orderRepository.findById(id).orElseThrow(()-> new Exception("Order ID not found"));
	}



	@Override
	public List<Order> usersOrderHistory(Long userId) {
		// TODO Auto-generated method stub
		return orderRepository.findByUserId(userId);
	}

	@Override
	public List<Order> sellerOrder(Long sellerId) {
		// TODO Auto-generated method stub
		return orderRepository.findBySellerId(sellerId);
	}

	@Override
	public Order orderUpdateStatus(Long orderId, OrderStatus orderStatus) throws Exception {
		// TODO Auto-generated method stub
		Order findOrder=findOrderById(orderId);
		 findOrder.setOrderStatus(orderStatus);
		return orderRepository.save(findOrder);
	}

	@Override
	public Order CancelOrder(Long orderId,User user) throws Exception {
		// TODO Auto-generated method stub
		
	
		Order order=findOrderById(orderId);
		if(!user.getId().equals(order.getUser().getId()))
		{
			 throw new Exception(" you don't have access this order");
		}
				order.setOrderStatus(OrderStatus.CANCELLED);
		return orderRepository.save(order);
	}

	@Override
	public OrderItem findbyId(Long id) throws Exception {
		// TODO Auto-generated method stub
		return itemRepository.findById(id).orElseThrow(()->new Exception("Order item not exist"));
	}

}

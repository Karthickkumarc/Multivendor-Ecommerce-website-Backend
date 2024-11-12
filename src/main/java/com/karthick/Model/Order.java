package com.karthick.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.karthick.Domain.OrderStatus;
import com.karthick.Domain.PaymentStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name="orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String orderId;
	
	@ManyToOne
	private User user;
	
	private Long sellerId;
	
	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<OrderItem>orderItems=new ArrayList<>();
	
	@ManyToOne
	private Address shippingAddress;
	
	@Embedded
	private PaymentDetails paymentDetails=new PaymentDetails();
	
	private double totalMrpPrice;
	
	private Integer totalSellingPrice;
	
	private Integer discount;
	
	private OrderStatus orderStatus;
	
	private int totalItem;
	
	private PaymentStatus paymentstatus=PaymentStatus.PENDING ;
	
	private LocalDateTime ordertime=LocalDateTime.now();
	
	private LocalDateTime deliverDate=ordertime.plusDays(7);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		sellerId = sellerId;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public PaymentDetails getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(PaymentDetails paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

	public double getTotalMrpPrice() {
		return totalMrpPrice;
	}

	public void setTotalMrpPrice(double totalMrpPrice) {
		this.totalMrpPrice = totalMrpPrice;
	}

	public Integer getTotalSellingPrice() {
		return totalSellingPrice;
	}

	public void setTotalSellingPrice(Integer totalSellingPrice) {
		this.totalSellingPrice = totalSellingPrice;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(int totalItem) {
		this.totalItem = totalItem;
	}

	public PaymentStatus getPaymentstatus() {
		return paymentstatus;
	}

	public void setPaymentstatus(PaymentStatus paymentstatus) {
		this.paymentstatus = paymentstatus;
	}

	public LocalDateTime getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(LocalDateTime ordertime) {
		this.ordertime = ordertime;
	}

	public LocalDateTime getDeliverDate() {
		return deliverDate;
	}

	public void setDeliverDate(LocalDateTime deliverDate) {
		this.deliverDate = deliverDate;
	}
	
}

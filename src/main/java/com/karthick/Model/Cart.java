package com.karthick.Model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	private User user;
	
	@OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
	private Set<CartItem>cartItems=new HashSet<>();
	
	private  double  totalSellingPrice;
	
	private int totalItem;
	
	private int totalMrpPrice;
	
	private int discount;
	
	private String Couponcode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public double getTotalSellingPrice() {
		return totalSellingPrice;
	}

	public void setTotalSellingPrice(double totalSellingPrice) {
		this.totalSellingPrice = totalSellingPrice;
	}

	public int getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(int totalItem) {
		this.totalItem = totalItem;
	}

	public int getTotalMrpPrice() {
		return totalMrpPrice;
	}

	public void setTotalMrpPrice(int totalMrpPrice) {
		this.totalMrpPrice = totalMrpPrice;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getCouponcode() {
		return Couponcode;
	}

	public void setCouponcode(String couponcode) {
		Couponcode = couponcode;
	}
	
}

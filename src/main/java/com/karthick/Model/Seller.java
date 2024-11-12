package com.karthick.Model;

import com.karthick.Domain.AccountStatus;
import com.karthick.Domain.USER_ROLE;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class Seller {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique = true)
	private String  sellerName;
	
	private String mobile;
	
	
	private String email;
	
	private String password;
	
	@Embedded
	private BusinessDetails businessDetails=new BusinessDetails();
	
	@Embedded
	private BankDetails bankDetails=new BankDetails();
	
	@JoinColumn(name = "address_id")
	@OneToOne(cascade = CascadeType.ALL)
	private PickUpAddress pickUpAddress;

	
	public PickUpAddress getPickUpAddress() {
		return pickUpAddress;
	}

	public void setPickUpAddress(PickUpAddress pickUpAddress) {
		this.pickUpAddress = pickUpAddress;
	}

	private USER_ROLE role=USER_ROLE.ROLE_SELLER;
	
	private boolean issellernameVerified=false;
	
	public boolean isIssellernameVerified() {
		return issellernameVerified;
	}

	public void setIssellernameVerified(boolean issellernameVerified) {
		this.issellernameVerified = issellernameVerified;
	}

	private AccountStatus accountstatus=AccountStatus.PENDING_VERIFICATION;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BusinessDetails getBusinessDetails() {
		return businessDetails;
	}

	public void setBusinessDetails(BusinessDetails businessDetails) {
		this.businessDetails = businessDetails;
	}

	public BankDetails getBankDetails() {
		return bankDetails;
	}

	public void setBankDetails(BankDetails bankDetails) {
		this.bankDetails = bankDetails;
	}

	

	public USER_ROLE getRole() {
		return role;
	}

	public void setRole(USER_ROLE role) {
		this.role = role;
	}

	

	public AccountStatus getAccountstatus() {
		return accountstatus;
	}

	public void setAccountstatus(AccountStatus accountstatus) {
		this.accountstatus = accountstatus;
	}
	
}

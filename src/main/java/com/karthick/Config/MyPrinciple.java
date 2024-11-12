package com.karthick.Config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.karthick.Domain.USER_ROLE;
import com.karthick.Model.Seller;
import com.karthick.Model.User;

public class MyPrinciple implements UserDetails {

	private String username;
	private String password;
	private USER_ROLE roles;
	
	public MyPrinciple(User user) {
		// TODO Auto-generated constructor stub
		this.username=user.getUserName();
		this.password=user.getPassword();
		this.roles=user.getRole();
	}

	

	public MyPrinciple(Seller seller) {
		// TODO Auto-generated constructor stub
		this.username=seller.getSellerName();
		this.password=seller.getPassword();
		this.roles=seller.getRole();
		
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		   if(roles ==null)
		   {
			   roles=USER_ROLE.ROLE_CUSTOMER;
		   }
		   List<GrantedAuthority> authorityList=new ArrayList<>();
		   authorityList.add(new SimpleGrantedAuthority("ROLE_"+roles));
		return authorityList;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return  password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

}

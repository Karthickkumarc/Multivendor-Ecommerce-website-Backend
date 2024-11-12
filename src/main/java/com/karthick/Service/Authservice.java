package com.karthick.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.karthick.Model.Seller;
import com.karthick.Model.User;
import com.karthick.Response.AuthResponse;



@Service
public interface Authservice {

	public AuthResponse verify(User user);
	public List<User> GetAllUser() ;
	public String AddUser(User user) ;
}

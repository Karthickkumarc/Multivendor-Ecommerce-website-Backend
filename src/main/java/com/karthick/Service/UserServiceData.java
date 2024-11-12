package com.karthick.Service;


import org.springframework.stereotype.Service;

import com.karthick.Model.User;

@Service
public interface UserServiceData {
	public User findUserByJwtToken(String jwt);
	
	public User findByuserNameed(String username);
}

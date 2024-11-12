package com.karthick.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.karthick.Model.User;
import com.karthick.Repository.UserRepository;
import com.karthick.Service.JwtService;

import com.karthick.Service.UserServiceData;

import lombok.RequiredArgsConstructor;


@Service
public class USerServiceImpleMents implements UserServiceData {

	@Autowired
	private JwtService jwtService;
	@Autowired
	private UserRepository userRepository;

	@Override
	public User findUserByJwtToken(String jwt) {
		System.out.println(jwt);
		String token=null;
		if(jwt!=null)
		{
			jwt=jwt.substring(7);
           token = jwtService.extractUserName(jwt);
		}
	 
		   System.out.println(token);
		User user = userRepository.findByUserName(token);
		if (user != null) {
			return user;

		}

		throw new BadCredentialsException(token);
	}

	@Override
	public User findByuserNameed(String username) {
		// TODO Auto-generated method stub

		User user = userRepository.findByUserName(username);
		if (user != null)
			return user;

		throw new BadCredentialsException("username not found");

	}

}

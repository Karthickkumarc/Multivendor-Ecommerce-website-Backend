package com.karthick.Service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.karthick.Domain.USER_ROLE;
import com.karthick.Model.Address;
import com.karthick.Model.Cart;
import com.karthick.Model.User;
import com.karthick.Repository.CartRepository;
import com.karthick.Repository.UserRepository;
import com.karthick.Response.AuthResponse;
import com.karthick.Service.Authservice;
import com.karthick.Service.JwtService;



@Service
public class UserService  implements Authservice{

	@Autowired
	AuthenticationManager authmanager;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private JwtService JwtService;

	@Autowired
	
	private PasswordEncoder encoder;

	public String AddUser(User user) {
		// TODO Auto-generated method stub
		user.setPassword(encoder.encode(user.getPassword()));
		user.setRole(USER_ROLE.ROLE_CUSTOMER);
		   for(Address address:user.getAddress())
		   {
			address.setUser(user);
		   }
		
		   
		if(!existByEmail(user.getUserName()))
		{
			userRepository.save(user);
			 Cart cart=new Cart();
			   cart.setUser(user);
			   cartRepository.save(cart);
		String token = JwtService.GenerateToken(user.getUserName());
			return token;
		}
		return "Error: username already exist";
			
	}

	public boolean existByEmail(String username)
	{
		if(userRepository.existsByUserName(username))
		{
			return true;
		}
		return false;
	}
	
	public List<User> GetAllUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	public AuthResponse verify(User user) {
		// TODO Auto-generated method stub
		org.springframework.security.core.Authentication authentication = authmanager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
		if (authentication.isAuthenticated()) {

			String token = JwtService.GenerateToken(user.getUserName());
			
                Collection<?extends GrantedAuthority> authorities=authentication.getAuthorities();
                String roleName=authorities.isEmpty()?null:authorities.iterator().next().getAuthority();
                System.out.println(roleName);
                
                
                roleName = roleName.replace("ROLE_ROLE_", "ROLE_");
                
                
                AuthResponse response=new AuthResponse();
                response.setJwt(token);
                response.setMessage("login succesfull");
                response.setRole(USER_ROLE.valueOf(roleName));
			   return response;

		}
		return null;
		
	

	}


	

}

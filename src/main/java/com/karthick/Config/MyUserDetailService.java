package com.karthick.Config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.karthick.Domain.USER_ROLE;
import com.karthick.Model.Seller;
import com.karthick.Model.User;
import com.karthick.Repository.SellerRepository;
import com.karthick.Repository.UserRepository;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SellerRepository sellerRepository;

	private static final String SELLER_PREFIX = "seller_";

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println(username);
		if (username.startsWith(SELLER_PREFIX)) {

			String actualusername = username.substring(SELLER_PREFIX.length());

			Seller seller = sellerRepository.findBySellerName(actualusername);
			System.out.println(actualusername);
			if (seller == null) {
				throw new UsernameNotFoundException("userName not found " + actualusername);
			}
			return BuildUserDetails(seller.getSellerName(), seller.getPassword(), seller.getRole());
		} else {
			User user = userRepository.findByUserName(username);

			if (user == null) {
				System.out.println("USername not found Exceptyion");
				throw new UsernameNotFoundException("userName not found " + user);

			}
			return BuildUserDetails(user.getUserName(), user.getPassword(), user.getRole());
		}

	}

	private UserDetails BuildUserDetails(String sellerName, String password, USER_ROLE role) {
		// TODO uto-generated method stub
		if (role == null)
			role = USER_ROLE.ROLE_CUSTOMER;
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
		return new org.springframework.security.core.userdetails.User(sellerName, password, authorities);
	}

}

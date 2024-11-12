package com.karthick.Service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.karthick.Domain.AccountStatus;
import com.karthick.Domain.USER_ROLE;
import com.karthick.Exception.SellerException;
import com.karthick.Model.Address;
import com.karthick.Model.PickUpAddress;
import com.karthick.Model.Seller;
import com.karthick.Repository.AddressRepository;
import com.karthick.Repository.SellerRepository;
import com.karthick.Response.AuthResponse;
import com.karthick.Service.JwtService;
import com.karthick.Service.SellerService;
@Service
public class SellerServiceImplements implements SellerService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authmanManager;
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private SellerRepository sellerRepository;
	
	private static final String SELLER_PREFIX = "seller_";
	
	@Autowired
	private AddressRepository addressRepository;
	@Override
	public Seller GetSellerProfile(String jwt) {
		// TODO Auto-generated method stub
		System.out.println(jwt);
		String token=null;
		if(jwt!=null)
		{
			String token1=jwt.substring(7);
			System.out.println(token1);
			token=jwtService.extractUserName(token1);
		}
		System.out.println("Seller"+token);
		  String username=token.substring(SELLER_PREFIX.length());
		Seller seller=sellerRepository.findBySellerName(username);
		System.out.println("Seller"+seller);
		return seller;
	}

	@Override
	public Seller CreateSeller(Seller seller) throws Exception {
		   Seller sellergetname=sellerRepository.findBySellerName(seller.getSellerName());
		   if(sellergetname!=null)
		   {
			   throw new Exception("this sellername name is already Exist pls different use username and try again");
		   }
		  ;
		  PickUpAddress pickUpAddress=new PickUpAddress();
		      pickUpAddress.setAddress(seller.getPickUpAddress().getAddress());
		      pickUpAddress.setCity(seller.getPickUpAddress().getCity());
		      pickUpAddress.setMobile(seller.getPickUpAddress().getMobile());
		      pickUpAddress.setLocality(seller.getPickUpAddress().getLocality());
		      pickUpAddress.setName(seller.getPickUpAddress().getName());
		      pickUpAddress.setPincode(seller.getPickUpAddress().getPincode());
		     pickUpAddress.setState(seller.getPickUpAddress().getState());
		    
		 System.out.println(seller.getPassword());
		   Seller createSeller =new Seller();
		   createSeller.setSellerName(seller.getSellerName());
		   createSeller.setEmail(seller.getEmail());
		    createSeller.setPassword(passwordEncoder.encode(seller.getPassword()));
		   createSeller.setBankDetails(seller.getBankDetails());
		   createSeller.setAccountstatus(seller.getAccountstatus());
		   createSeller.setBusinessDetails(seller.getBusinessDetails());
		   createSeller.setMobile(seller.getMobile());
		   createSeller.setRole(USER_ROLE.ROLE_SELLER);
		   createSeller.setIssellernameVerified(false);
		   createSeller.setPickUpAddress(pickUpAddress);
		  
		   
		   System.out.println("password"+createSeller.getPassword());
		return sellerRepository.save(createSeller);		
	}

	@Override
	public Seller GetSellerById(Long id) throws SellerException {
		// TODO Auto-generated method stub
		return sellerRepository.findById(id).orElseThrow(()-> new SellerException("Seller id not found :"+id));
		
	}

	@Override
	public Seller UpdateSeller(Long id, Seller seller) throws Exception {
		// TODO Auto-generated method stub
		 Seller existinSeller=this.GetSellerById(id);
		 
		 if(seller.getSellerName()!=null)
		 {
			 existinSeller.setSellerName(seller.getSellerName());
		 }
		 if(seller.getEmail()!=null)
		 {
			 existinSeller.setEmail(seller.getEmail());
		 }
		 
		 if(seller.getMobile()!=null)
		 {
			 existinSeller.setMobile(seller.getMobile());

		 }
		 if(seller.getBusinessDetails()!=null && seller.getBusinessDetails().getBusinessName()!=null
				                               && seller.getBusinessDetails().getBusinessAddress()!=null
				                               && seller.getBusinessDetails().getBusinessMobile()!=null
				                               && seller.getBusinessDetails().getBusinessEmail()!=null
				                               &&seller.getBusinessDetails().getBanner()!=null
				                               && seller.getBusinessDetails().getLogo()!=null)
		 {
			 existinSeller.getBusinessDetails().setBusinessName(seller.getBusinessDetails().getBusinessName());
			 existinSeller.getBusinessDetails().setBusinessAddress(seller.getBusinessDetails().getBusinessAddress());
			 existinSeller.getBusinessDetails().setBusinessMobile(seller.getBusinessDetails().getBusinessMobile());
			 existinSeller.getBusinessDetails().setBusinessEmail(seller.getBusinessDetails().getBusinessEmail());
			 existinSeller.getBusinessDetails().setBanner(seller.getBusinessDetails().getBanner());
			 existinSeller.getBusinessDetails().setLogo(seller.getBusinessDetails().getLogo());
		 
		 }
		 if(existinSeller.getBankDetails()!=null && existinSeller.getBankDetails().getAccountHolderName()!=null
				                                  && existinSeller.getBankDetails().getAccountNumber()!=null
				                                  &&existinSeller.getBankDetails().getIfscCode()!=null)
		 {
			 existinSeller.getBankDetails().setAccountHolderName(seller.getBankDetails().getAccountHolderName());
			 existinSeller.getBankDetails().setAccountNumber(seller.getBankDetails().getAccountNumber());
			 existinSeller.getBankDetails().setIfscCode(seller.getBankDetails().getIfscCode());
		 }
		 
		 
		 
		return sellerRepository.save(existinSeller);
	}

	@Override
	public List<Seller> GetAllSellers(AccountStatus status) {
		// TODO Auto-generated method stub
		return sellerRepository.findByAccountstatus(status);
	}

	@Override
	public void DeleteSeller(Long id) throws Exception {
		// TODO Auto-generated method stub
		
		Seller seller= this.GetSellerById(id);
		sellerRepository.delete(seller);
		
	}

	@Override
	public Seller UpdateStatusAccountStatus(Long sellerId, AccountStatus status) throws Exception {
		// TODO Auto-generated method stub
	   Seller seller=this.GetSellerById(sellerId);
	   seller.setAccountstatus(status);
		return sellerRepository.save(seller);
	}

	@Override
	public Seller GetSellerByUsername(String username) throws Exception {
		// TODO Auto-generated method stub
		   Seller seller=sellerRepository.findBySellerName(username);
		   if(seller ==null)
		   {
			   throw new Exception("Seller not found");
		   }
		return seller;
	}

	@Override
	public Seller VerifySellerUsername(String username) throws Exception {
		// TODO Auto-generated method stub
		Seller seller=this.GetSellerByUsername(username);
		seller.setIssellernameVerified(true);
		return sellerRepository.save(seller);
	}

	@Override
	public AuthResponse LoginSeller(Seller seller) {
		// TODO Auto-generated method stub
//		 String sellertokenname=seller.getSellerName().substring(SELLER_PREFIX.length());
		org.springframework.security.core.Authentication authentication = authmanManager
				.authenticate(new UsernamePasswordAuthenticationToken(seller.getSellerName(), seller.getPassword()));
		if (authentication.isAuthenticated()) {

			String token = jwtService.GenerateToken(seller.getSellerName());
			
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

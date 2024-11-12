package com.karthick.Service;

import java.awt.RenderingHints.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.karthick.Config.jwtFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;



@Service

public class JwtService {

	
	@Lazy
	@Autowired
	private jwtFilter jwtFilter;

	private String secretkey="YR1S/JAF68RPLsXPo92XQl798Ca2QMxAqnBCMmlDk8s=";
	
//	
//	public JwtService() {
//		try {
//			KeyGenerator keyGen=KeyGenerator.getInstance("HmacSHA256");
//			SecretKey sk=keyGen.generateKey();
////			System.out.println("hi karthick :"+sk);
//			secretkey=java.util.Base64.getEncoder().encodeToString(sk.getEncoded());
//			System.out.println(secretkey);
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			 throw new RuntimeException(e);
//		}
//	
//	}

	public  String GenerateToken(String username) {
		
		Map<String, Object>claims=new HashMap<>();
	
		
		return Jwts.builder()
				.claims()
				.add(claims)
				.subject(username)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+86400000))
				.and()
				.signWith(getkey())
				.compact();
		
		
	}

	private SecretKey getkey() {
	
		// TODO Auto-generated method stub
		byte [] keybytes=Decoders.BASE64.decode(secretkey);
		return 	Keys.hmacShaKeyFor(keybytes);
	}

	public  String extractUserName(String token) {
		// TODO Auto-generated method stub
		return extractClaim(token, Claims::getSubject);
	}

	private <T> T  extractClaim(String token, Function<Claims, T> claimResolver) {
		// TODO Auto-generated method stub
		final Claims claims=extractAllClaims(token);
		System.out.println(claims);
		
		return  claimResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		// TODO Auto-generated method stub
		return Jwts.parser().verifyWith(getkey()).build().parseSignedClaims(token).getPayload();
	}

	public boolean ValidateToken(String token, UserDetails userDetails) {
		final String username=extractUserName(token);
		return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		// TODO Auto-generated method stub
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		// TODO Auto-generated method stub
		return extractClaim(token,Claims::getExpiration);
	}

	public String tokenValidate(String token) {
		// TODO Auto-generated method stub
		if(isTokenExpired(token))
		{
			return "tokenexpired";
		}
		else
		{
			return "validtoken";
		}
	}

	public boolean validation(String token) {
		if(isTokenExpired(token))
		{
			return false;
		}
		
		return true;	}

	
}

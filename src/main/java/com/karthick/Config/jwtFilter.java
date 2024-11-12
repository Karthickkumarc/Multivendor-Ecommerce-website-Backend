package com.karthick.Config;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.karthick.Service.JwtService;

import io.jsonwebtoken.ExpiredJwtException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class jwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private org.springframework.context.ApplicationContext context;
	@Autowired
	private MyUserDetailService myUserDetailService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String authHeader=request.getHeader("Authorization");
		String token=null;
		String username=null;
		
		if(authHeader !=null && authHeader.startsWith("Bearer "))
		{
			token=authHeader.substring(7);
			try {
			username=jwtService.extractUserName(token);
	      System.out.println("USERNAME"+username);
			
			}
			catch(ExpiredJwtException e)
			{
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.getWriter().write("token is expired,please login");
				return;
			}
		}
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
		{
			System.out.println("hellomadam");
		UserDetails userDetails=context.getBean(myUserDetailService.getClass()).loadUserByUsername(username);

               if(jwtService.ValidateToken(token, userDetails))
               {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
               }
		}
		filterChain.doFilter(request, response);
	}

}

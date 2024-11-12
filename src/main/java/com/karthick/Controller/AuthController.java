package com.karthick.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.karthick.Model.User;
import com.karthick.Repository.UserRepository;
import com.karthick.Response.AuthResponse;
import com.karthick.Service.Authservice;
import com.karthick.Service.UserServiceData;


@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Authservice authservice;
	
	@Autowired
	private UserServiceData userService;

	@PostMapping("/register")
	public ResponseEntity<?> AddUser(@RequestBody User user)
	{
		
	  
	   String jwt =authservice.AddUser(user);
		System.out.println(jwt);
		if(jwt!=null && !jwt.startsWith("Error"))
		{
			   AuthResponse response=new AuthResponse();
			     response.setJwt(jwt);
			     response.setMessage("SuccessFully Registerd");
			     response.setRole(user.getRole());
			return ResponseEntity.ok(response);
		}
		else {
		return	ResponseEntity.badRequest().body(jwt);
		}
		
	}

	@GetMapping("/alluser")
	public List<User>getAllUSer()
	{
		return authservice.GetAllUser();
	}
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> Loginuser(@RequestBody User user)
	{
		
		AuthResponse jwt =authservice.verify(user);
		
		return ResponseEntity.ok(jwt);
		
	}
	@GetMapping("/jwt")
	public ResponseEntity<User> FindbyUserUsingJwt(@RequestHeader("Authorization") String jwt)
	{
		User user=userService.findUserByJwtToken(jwt);
		
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/findbyname")
	public ResponseEntity<User> findbyUserByusername(@RequestParam String username)
	{
		User user=userService.findByuserNameed(username);
		return ResponseEntity.ok(user);
	}
}

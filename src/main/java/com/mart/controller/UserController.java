package com.mart.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mart.dto.LoginDTO;
import com.mart.entities.Users;
import com.mart.response.LoginResponse;
import com.mart.service.JWTService;
import com.mart.service.TokenBlacklist;
import com.mart.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private TokenBlacklist tokenBlacklist;

	@PostMapping("/auth/signup")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Users> postMethodName(@RequestBody Users user) {
		Users responseUser = userService.signUp(user);

		return ResponseEntity.ok(responseUser);
	}

	@PostMapping("/auth/login")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<LoginResponse> postMethodName(@RequestBody LoginDTO loginDto) {
		Users user = userService.loginUser(loginDto);

		String jwtToken = jwtService.generateToken(new HashMap<>(), user);

		LoginResponse loginResponse = new LoginResponse();

		loginResponse.setToken(jwtToken);
		loginResponse.setTokenExpiryTime(jwtService.getExpirationTime());
		return ResponseEntity.ok(loginResponse);
	}

	@GetMapping("/getUsers")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Users>> getAllUsers() {
		List<Users> users = userService.getAllUsers();

		return ResponseEntity.ok(users);
	}
	
	@PostMapping("/logout")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> logout(HttpServletRequest request) {
	    String authHeader = request.getHeader("Authorization");

	    if (authHeader != null && authHeader.startsWith("Bearer ")) {
	        String token = authHeader.substring(7);
	        tokenBlacklist.blacklist(token);
	    }
	    Cookie cookie = new Cookie("token", null);
	    cookie.setHttpOnly(true);
	    cookie.setPath("/");
	    cookie.setMaxAge(0); // expire immediately
	    

	    return ResponseEntity.ok("Logged out");
	}

}

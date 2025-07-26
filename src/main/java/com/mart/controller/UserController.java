package com.mart.controller;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

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
	
	@PostMapping("/auth/logout")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> logout(@RequestHeader("Authorization") String authHeaders) {
		
		if(authHeaders ==null || !authHeaders.startsWith("Bearer")){
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing token.");
		}
		
		String token = authHeaders.substring(7);
		java.util.Date expiry = jwtService.extractExpiration(token); // method you must have
	    LocalDateTime expiryDate = expiry.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

	    tokenBlacklist.blacklist(token, expiryDate);
	   // tokenBlacklist.blacklist(jwtToken);
	    return ResponseEntity.ok("Logged out successfully.");
	}
	
	@Scheduled(cron = "0 * * * * *") // Every hour
	public void purgeExpiredTokens() {
		tokenBlacklist.cleanExpiredTokens();
		System.out.println("tokens deletion executed every minute at: " + new java.util.Date());
	}

}

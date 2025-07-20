package com.mart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mart.dto.LoginDTO;
import com.mart.entities.Users;
import com.mart.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public Users signUp(Users userData) {
		userData.setPassword(passwordEncoder.encode(userData.getPassword()));
		
		return userRepo.save(userData);
	}
	
	public Users loginUser(LoginDTO loginDto) {
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
		
		return userRepo.findByEmail(loginDto.getEmail()).orElseThrow();
	}
	
	public List<Users> getAllUsers(){
		
		return userRepo.findAll();
	}

}

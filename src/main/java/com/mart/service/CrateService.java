package com.mart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mart.entities.Crate;
import com.mart.repository.CrateRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CrateService {
	
	@Autowired
	private CrateRepo crateRepo;
	
	public Crate saveCrate(Crate crate) {
		
		
		return crateRepo.save(crate);
		
		
	}
	
	public List<Crate> getAllCrates(){
		return crateRepo.findAll();
	}

	public Optional<Crate> getCrateById(Long id) {
		
		return Optional.ofNullable(crateRepo.findById(id).orElse(null));
	}
}

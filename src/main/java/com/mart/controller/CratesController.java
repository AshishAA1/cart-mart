package com.mart.controller;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mart.entities.Crate;
import com.mart.repository.CrateRepo;
import com.mart.service.CrateService;

@RestController
@RequestMapping("/api/crates")
@CrossOrigin(origins = "http://localhost:4200")
public class CratesController {

	@Autowired
	private CrateRepo crateRepository;
	
	@Autowired
	private CrateService crateService;

	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> saveCrateWithImage(@RequestPart("crate") Crate crate,
			@RequestPart("image") MultipartFile imageFile) {

		try {
			if (!imageFile.isEmpty()) {
							
				crate.setCrateImgbyteArray(imageFile.getBytes());
				//crate.setCrateId(UUID.randomUUID().toString());
				//crate.setVersion(0);
			}
			Crate savedCrate = crateService.saveCrate(crate);
			return ResponseEntity.ok(savedCrate);

		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to upload crate: " + e.getMessage());
		}
	}
	
	@GetMapping(value = "/getCrates")
	public ResponseEntity<?> getAllCrates(){
		
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(crateService.getAllCrates());
		
		
	}
	
	
	
	
	
	
	
	
}
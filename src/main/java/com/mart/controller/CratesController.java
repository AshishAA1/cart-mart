package com.mart.controller;

import java.io.IOException;
import java.util.Optional;
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
	
	@PostMapping(value ="/updateCrateById", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> updateCrateById(@RequestPart("crate") Crate crate,
			@RequestPart("image") MultipartFile imageFile) throws IOException {
		Crate crateDb = null;

		Optional<Crate> crateResponse = null;
		if (crate.getCrateId() != null) {

			crateResponse = crateService.getCrateById(crate.getCrateId());

			if (crateResponse.isPresent()) {
				crateDb = crateResponse.get();

				crateDb.setAddress(crate.getAddress() != null ? crate.getAddress() : crateDb.getAddress());
				crateDb.setCrateColor(crate.getCrateColor() != null ? crate.getCrateColor() : crateDb.getCrateColor());
				crateDb.setCrateDescription(crate.getCrateDescription() != null ? crate.getCrateDescription()
						: crateDb.getCrateDescription());
				crateDb.setCrateImgbyteArray(
						!imageFile.isEmpty() ? imageFile.getBytes() : crateDb.getCrateImgbyteArray());
				crateDb.setCrateImgPath(
						crate.getCrateImgPath() != null ? crate.getCrateImgPath() : crateDb.getCrateImgPath());
				crateDb.setCrateMeasurment(
						crate.getCrateMeasurment() != null ? crate.getCrateMeasurment() : crateDb.getCrateMeasurment());
				crateDb.setCrateName(crate.getCrateName() != null ? crate.getCrateName() : crateDb.getCrateName());
				crateDb.setCratePrice(crate.getCratePrice() != null ? crate.getCratePrice() : crateDb.getCratePrice());
				crateDb.setPhoneNo(crate.getPhoneNo() != null ? crate.getPhoneNo() : crateDb.getPhoneNo());
				crateDb.setSellerInfo(crate.getSellerInfo() != null ? crate.getSellerInfo() : crateDb.getSellerInfo());
				crateDb.setState(crate.getState() != null ? crate.getState() : crateDb.getState());
				crateDb.setVersion(crate.getVersion() != null ? crate.getVersion() : crateDb.getVersion());
			}
		}

		return ResponseEntity.status(HttpStatus.OK).body(crateService.saveCrate(crateDb));

	}
	
	
}
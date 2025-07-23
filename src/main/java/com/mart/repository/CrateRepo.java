package com.mart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mart.entities.Crate;

public interface CrateRepo extends JpaRepository<Crate, String> {

}

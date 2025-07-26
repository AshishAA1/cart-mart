package com.mart.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mart.entities.BlacklistedToken;

public interface BlacklistedTokenRepository extends JpaRepository<BlacklistedToken, Long> {

	Optional<BlacklistedToken> findByToken(String token);

	void deleteByExpiryDateBefore(LocalDateTime time); // For cleaning old token

}

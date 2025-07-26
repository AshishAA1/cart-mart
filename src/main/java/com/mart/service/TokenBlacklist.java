package com.mart.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.mart.entities.BlacklistedToken;
import com.mart.repository.BlacklistedTokenRepository;

import jakarta.transaction.Transactional;

@Component
public class TokenBlacklist {

	// private final Set<String> blacklistedTokens = ConcurrentHashMap.newKeySet();

	// public void blacklist(String token) {
	// blacklistedTokens.add(token);
	// }

	// public boolean isBlacklisted(String token) {
	// return blacklistedTokens.contains(token);
	// }

	// Using spring redis template to cache the token for the blacklisting purpose

	//private static final String BLACKLIST_PREFIX = "blacklisted:";

	//@Autowired
	//private RedisTemplate<String, String> redisTemplate;

	//public void blacklist(String token, long expiryInMillis) {
	//	redisTemplate.opsForValue().set(BLACKLIST_PREFIX + token, "true", Duration.ofSeconds(expiryInMillis));
	//}

	//public boolean isBlacklisted(String token) {
	//	return redisTemplate.hasKey(BLACKLIST_PREFIX + token);
	//}
	
	@Autowired
	private BlacklistedTokenRepository blackListedTokenRepository;
	
	public void blacklist(String token, LocalDateTime expiry) {
		blackListedTokenRepository.save(new BlacklistedToken(token, expiry));
    }

    public boolean isBlacklisted(String token) {
        return blackListedTokenRepository.findByToken(token).isPresent();
    }

    // Optional: clean old tokens periodically
    @Transactional
    public void cleanExpiredTokens() {
    	blackListedTokenRepository.deleteByExpiryDateBefore(LocalDateTime.now());
    }
	
}

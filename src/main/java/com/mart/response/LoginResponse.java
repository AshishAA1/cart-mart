package com.mart.response;

public class LoginResponse {

	public LoginResponse() {
	}

	public LoginResponse(String token, long tokenExpiryTime) {
		this.token = token;
		this.tokenExpiryTime = tokenExpiryTime;
	}

	private String token;

	private long tokenExpiryTime;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getTokenExpiryTime() {
		return tokenExpiryTime;
	}

	public void setTokenExpiryTime(long tokenExpiryTime) {
		this.tokenExpiryTime = tokenExpiryTime;
	}

}

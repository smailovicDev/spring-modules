package com.example.demo.secconfig;

public enum SecurityConstants {

	
	
	SECRET("Hello@@Smail@@Hello"),
	TOKENPREFIX("Bearer "),
	AUTHORIZATION("Authorization");
	
	
	private String value;
	
	SecurityConstants( String s) {
		this.value = s;
	}

	public String getValue() {
		return value;
	}
	
	
}

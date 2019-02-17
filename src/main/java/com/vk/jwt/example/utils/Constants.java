package com.vk.jwt.example.utils;

public enum Constants {
	
	BEGIN_PRIVATE_KEY("——–BEGIN PRIVATE KEY—––"),
	END_PRIVATE_KEY("–—–END PRIVATE KEY—––"),
	
	BEGIN_PUBLIC_KEY("——–BEGIN PUBLIC KEY—––"),
	END_PUBLIC_KEY("–—–END PUBLIC KEY—––"),
	
	PRIVATE_KEY_EXTN(".pem");
	
	private String value;
	
	private Constants(String value) {
		this.value=value;
	}

	public String getValue() {
		return value;
	}
}

package com.sample.jwt.sample.boot;

import java.util.List;

import net.minidev.json.JSONObject;

public class JWKResponse {

	
	private List<JSONObject> keys;

	public List<JSONObject> getKeys() {
		return keys;
	}

	public void setKeys(List<JSONObject> keys) {
		this.keys = keys;
	}

	
	
}

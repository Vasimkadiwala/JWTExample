package com.vk.jwt.example.model;

import java.util.Date;

public class KeyDetails {

	private String keyStoreName;
	private String keyStorePath;
	private char[] storePass;
	private String alias;
	private Date expiresOn;
	
	public String getKeyStoreName() {
		return keyStoreName;
	}
	public void setKeyStoreName(String keyStoreName) {
		this.keyStoreName = keyStoreName;
	}
	public String getKeyStorePath() {
		return keyStorePath;
	}
	public void setKeyStorePath(String keyStorePath) {
		this.keyStorePath = keyStorePath;
	}
	public char[] getStorePass() {
		return storePass;
	}
	public void setStorePass(char[] storePass) {
		this.storePass = storePass;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public Date getExpiresOn() {
		return expiresOn;
	}
	public void setExpiresOn(Date expiresOn) {
		this.expiresOn = expiresOn;
	}
	
	
	
	
}

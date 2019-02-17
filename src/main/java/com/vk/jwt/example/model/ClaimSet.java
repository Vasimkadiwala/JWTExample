package com.vk.jwt.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class ClaimSet {

	private String iss;
	private long exp;
	
	@JsonIgnore
	private String sig;
	
	public String getIss() {
		return iss;
	}
	public void setIss(String iss) {
		this.iss = iss;
	}
	public long getExp() {
		return exp;
	}
	public void setExp(long exp) {
		this.exp = exp;
	}
	public String getSig() {
		return sig;
	}
	public void setSig(String sig) {
		this.sig = sig;
	}
	
	
}

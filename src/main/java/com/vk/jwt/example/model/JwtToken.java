package com.vk.jwt.example.model;

public class JwtToken {
	
	private JwtHeader header;
	private String encodedHeader;
	private ClaimSet payload;
	private String encodedPayload;
	private String encodedSignature;
	public JwtHeader getHeader() {
		return header;
	}
	public void setHeader(JwtHeader header) {
		this.header = header;
	}
	public ClaimSet getPayload() {
		return payload;
	}
	public void setPayload(ClaimSet payload) {
		this.payload = payload;
	}
	
	public String getEncodedHeader() {
		return encodedHeader;
	}
	public void setEncodedHeader(String encodedHeader) {
		this.encodedHeader = encodedHeader;
	}
	public String getEncodedPayload() {
		return encodedPayload;
	}
	public void setEncodedPayload(String encodedPayload) {
		this.encodedPayload = encodedPayload;
	}
	public String getEncodedSignature() {
		return encodedSignature;
	}
	public void setEncodedSignature(String encodedSignature) {
		this.encodedSignature = encodedSignature;
	}
	
	
	

}

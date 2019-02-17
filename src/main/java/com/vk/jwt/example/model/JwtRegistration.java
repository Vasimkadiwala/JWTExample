package com.vk.jwt.example.model;

import java.util.Date;
import java.util.List;

public class JwtRegistration {

	
	private String clientId;
	private String tenantName;
	private String tenantId;
	private String registeredEmailAddress;
	private int expDiffinSeconds;
	
	
	private KeyDetails keyDetails;
	
	private SymAuthDetails symAuthDteails;
	
	private Date registeredOn;
	private Date modifiedOn;
	private String generatedByEmailId;
	private List<String> emailIds;
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getTenantName() {
		return tenantName;
	}
	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getRegisteredEmailAddress() {
		return registeredEmailAddress;
	}
	public void setRegisteredEmailAddress(String registeredEmailAddress) {
		this.registeredEmailAddress = registeredEmailAddress;
	}
	
	public int getExpDiffinSeconds() {
		return expDiffinSeconds;
	}
	public void setExpDiffinSeconds(int expDiffinSeconds) {
		this.expDiffinSeconds = expDiffinSeconds;
	}
	
	public KeyDetails getKeyDetails() {
		return keyDetails;
	}
	public void setKeyDetails(KeyDetails keyDetails) {
		this.keyDetails = keyDetails;
	}
	public SymAuthDetails getSymAuthDteails() {
		return symAuthDteails;
	}
	public void setSymAuthDteails(SymAuthDetails symAuthDteails) {
		this.symAuthDteails = symAuthDteails;
	}
	public Date getRegisteredOn() {
		return registeredOn;
	}
	public void setRegisteredOn(Date registeredOn) {
		this.registeredOn = registeredOn;
	}
	public Date getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public List<String> getEmailIds() {
		return emailIds;
	}
	public void setEmailIds(List<String> emailIds) {
		this.emailIds = emailIds;
	}
	public String getGeneratedByEmailId() {
		return generatedByEmailId;
	}
	public void setGeneratedByEmailId(String generatedByEmailId) {
		this.generatedByEmailId = generatedByEmailId;
	}
	
	
	

}

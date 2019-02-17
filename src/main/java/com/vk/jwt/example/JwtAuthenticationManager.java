package com.vk.jwt.example;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.time.Instant;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.vk.jwt.example.model.JwtRegistration;
import com.vk.jwt.example.model.JwtToken;
import com.vk.jwt.example.ppki.helper.RegistrationRetreiver;
import com.vk.jwt.example.utils.JwtTokenExtractor;
import com.vk.jwt.example.utils.SignatureVerifier;

/**
 * 
 * @author Vasim Kadiwala
 *
 */
public class JwtAuthenticationManager {

	/**
	 * 1. Extract Token
	 * 2. Verify signature
	 * 3. Verify claim set
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @throws UnrecoverableEntryException 
	 * @throws CertificateException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyStoreException 
	 */
	
	public boolean validateJwtToken(String jwtToken) throws Exception{
		
		
		/**
		 * Extract Token from header
		 */
		JwtToken token=new JwtTokenExtractor().tokenExtractor(jwtToken);
		
		/**
		 * Get Key RegistrationDetails
		 */
		
		JwtRegistration jwtRegistration=new RegistrationRetreiver().fetchRegistration(token, Instant.now().getEpochSecond());
		
		
		/**
		 * Validate Signature
		 * 
		 */
		boolean isSignValid=new SignatureVerifier().verifySignature(jwtRegistration, token);
		
		/**
		 * Verify Claim set
		 */
		//pending
		
		return isSignValid;
	}
	
	
	//To do 
	/**
	 * 1.  Generate JWT token
	 * 2.  Run sh file for java to generate keystore
	 * 3.  
	 */
	
	
	
}

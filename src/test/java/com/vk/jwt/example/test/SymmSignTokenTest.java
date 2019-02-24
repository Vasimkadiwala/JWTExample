package com.vk.jwt.example.test;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vk.jwt.example.JwtAuthenticationManager;
import com.vk.jwt.example.model.ClaimSet;
import com.vk.jwt.example.model.JwtHeader;
import com.vk.jwt.example.utils.AlgOptions;

public class SymmSignTokenTest {
	
	
	
	
	@Test
	public void tokenExtractorTest() throws Exception {
		

		
		ObjectMapper mapper =new ObjectMapper();
		
		Encoder encoder=Base64.getEncoder();
		
		StringBuilder jwtToken=new StringBuilder();
		
		/**
		 * 1. Header
		 */
		JwtHeader header=new JwtHeader();
		header.setAlg(AlgOptions.HS256.name());
		
		String headerJson=mapper.writeValueAsString(header);
		String headerEncoded=encoder.encodeToString(headerJson.getBytes());
		
		
		/**
		 * 2. Payload
		 */
		ClaimSet claimSet=new ClaimSet();
		claimSet.setExp(Instant.now().getEpochSecond());
		claimSet.setIss("Symm Sample Clinet Id");
		
		String payloadJson=mapper.writeValueAsString(claimSet);
		String payloadJsonEncoded=encoder.encodeToString(payloadJson.getBytes());
		
		jwtToken.append(headerEncoded);
		jwtToken.append(".");
		jwtToken.append(payloadJsonEncoded);
		
		/**
		 * 3. Signature
		 */
		
		
		
		SecretKeySpec secretKeySpec = new SecretKeySpec(
				new String("testClientSecret").getBytes(StandardCharsets.UTF_8),
				"HmacSHA256");
		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(secretKeySpec);
		
		String signature=encoder.encodeToString(mac.doFinal(jwtToken.toString().getBytes(StandardCharsets.UTF_8)));
		jwtToken.append(".");
		jwtToken.append(signature);
		
		
		Assert.assertNotNull(jwtToken);
		
		Assert.assertTrue(new JwtAuthenticationManager().validateJwtToken(jwtToken.toString()));
		
		
	
		
	}

}

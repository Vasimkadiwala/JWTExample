package com.vk.jwt.example.test;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.time.Instant;
import java.util.Base64;
import java.util.Base64.Encoder;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vk.jwt.example.model.ClaimSet;
import com.vk.jwt.example.model.JwtHeader;
import com.vk.jwt.example.model.JwtToken;
import com.vk.jwt.example.ppki.helper.PrivateKeyGenerator;
import com.vk.jwt.example.ppki.helper.PublicKeyGenerator;
import com.vk.jwt.example.utils.AlgOptions;
import com.vk.jwt.example.utils.JwtTokenExtractor;
import com.vk.jwt.example.utils.SignatureVerifier;


/**
 * 
 * 
 * keytool -genkeypair -alias testkey -keyalg RSA -keysize 2048 -dname "CN=Vasim kadiwala, OU=VK, O=VK, L=HMMM, ST=Unknown, C=DK" -validity 365  -keystore Examplekeystore.p12 -storepass abcdef -storetype pkcs12
 */
public class AsymmSignTokenTest {
	
	
	@Test
	public void generateToken() throws Exception {
		
		ObjectMapper mapper =new ObjectMapper();
		
		Encoder encoder=Base64.getEncoder();
		
		StringBuilder jwtToken=new StringBuilder();
		
		/**
		 * 1. Header
		 */
		JwtHeader header=new JwtHeader();
		header.setAlg(AlgOptions.RS256.name());
		
		String headerJson=mapper.writeValueAsString(header);
		String headerEncoded=encoder.encodeToString(headerJson.getBytes());
		
		
		/**
		 * 2. Payload
		 */
		ClaimSet claimSet=new ClaimSet();
		claimSet.setExp(Instant.now().getEpochSecond());
		claimSet.setIss("Sample Clinet Id");
		
		String payloadJson=mapper.writeValueAsString(claimSet);
		String payloadJsonEncoded=encoder.encodeToString(payloadJson.getBytes());
		
		jwtToken.append(headerEncoded);
		jwtToken.append(".");
		jwtToken.append(payloadJsonEncoded);
		
		/**
		 * 3. Signature
		 */
		Signature signature = Signature.getInstance("SHA256withRSA");
		
		PrivateKey privateKey=new PrivateKeyGenerator().getPrivateKeyFromKeyStore("/Examplekeystore.p12", null, "abcdef".toCharArray(), "testkey");
		
		signature.initSign(privateKey);
		byte[] data = jwtToken.toString().getBytes("UTF-8");
		signature.update(data);

		byte[] digitalSignature = signature.sign();
		
		jwtToken.append(".");
		jwtToken.append(encoder.encodeToString(digitalSignature));
		
		
		Assert.assertNotNull(jwtToken);
		
		/**
		 * 4. Get public key
		 */
        JwtToken tokenObject=new JwtTokenExtractor().tokenExtractor(jwtToken.toString());
		
		Assert.assertNotNull(tokenObject);
		
		PublicKey publicKey=new PublicKeyGenerator().getPublicKeyFromKeyStore("/Examplekeystore.p12", null, "abcdef".toCharArray(), "testkey");
		
		Assert.assertNotNull(publicKey);
		
		/**
		 * 5. verify Signature
		 */
		Signature signatureV = Signature.getInstance("SHA256withRSA");
		
		signatureV.initVerify(publicKey);

		byte[] vData=(tokenObject.getEncodedHeader()+"."+tokenObject.getEncodedPayload()).getBytes("UTF-8");
		signatureV.update(vData);
		
		
		Assert.assertTrue(new SignatureVerifier().verifySignature(tokenObject, publicKey));
		
		
	}

}

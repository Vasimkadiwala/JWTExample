package com.sample.jwt.sample;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.SecureRandom;

import org.apache.commons.codec.binary.Base64;





public class PJCE {

	
	public static void main(String[] args) throws Exception {
		
		
		SecureRandom sr = new SecureRandom();
		byte[] code = new byte[32];
		sr.nextBytes(code);
		
		String verifier=java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(code);
		
		System.out.println(verifier);
		
		
		
		
		byte[] bytes = verifier.getBytes("US-ASCII");
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(bytes, 0, bytes.length);
		byte[] digest = md.digest();
		//Use Apache "Commons Codec" dependency. Import the Base64 class
		//import org.apache.commons.codec.binary.Base64;
		String challenge = Base64.encodeBase64URLSafeString(digest);
		System.out.println(challenge);
		String challenge2 = java.util.Base64.getUrlEncoder().encodeToString(digest);
		System.out.println(challenge2);
	}
}

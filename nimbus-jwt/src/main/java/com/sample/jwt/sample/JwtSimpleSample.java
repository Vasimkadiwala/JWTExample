package com.sample.jwt.sample;

import java.util.Date;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.*;
import com.nimbusds.jose.jwk.*;
import com.nimbusds.jose.jwk.gen.*;
import com.nimbusds.jwt.*;

public class JwtSimpleSample {

	
	public static void main(String[] args) throws Exception {
		
		// RSA signatures require a public and private RSA key pair, the public key 
		// must be made known to the JWS recipient in order to verify the signatures
		RSAKey rsaJWK = new RSAKeyGenerator(2048)
		    .keyID("123")
		    .generate();
		RSAKey rsaPublicJWK = rsaJWK.toPublicJWK();

		// Create RSA-signer with the private key
		JWSSigner signer = new RSASSASigner(rsaJWK);

		// Prepare JWT with claims set
		JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
		    .subject("alice")
		    .issuer("https://c2id.com")
		    .expirationTime(new Date(new Date().getTime() + 60 * 1000))
		    .claim("This is custom claim key", "This is custom e clain Value")
		    .claim("This is custom claim key1", "This is custom e clain Value")
		    .claim("This is custom claim key2", "This is custom e clain Value")
		    
		    .build();

		SignedJWT signedJWT = new SignedJWT(
		    new JWSHeader.Builder(JWSAlgorithm.RS256).keyID(rsaJWK.getKeyID()).build(),
		    claimsSet);

		// Compute the RSA signature
		signedJWT.sign(signer);

		System.out.println(signedJWT.serialize());
		
		// To serialize to compact form, produces something like
		// eyJhbGciOiJSUzI1NiJ9.SW4gUlNBIHdlIHRydXN0IQ.IRMQENi4nJyp4er2L
		// mZq3ivwoAjqa1uUkSBKFIX7ATndFF5ivnt-m8uApHO4kfIFOrW7w2Ezmlg3Qd
		// maXlS9DhN0nUk_hGI3amEjkKd0BWYCB8vfUbUv0XGjQip78AI4z1PrFRNidm7
		// -jPDm5Iq0SZnjKjCNS5Q15fokXZc8u0A
		String s = signedJWT.serialize();

		// On the consumer side, parse the JWS and verify its RSA signature
		signedJWT = SignedJWT.parse(s);

		JWSVerifier verifier = new RSASSAVerifier(rsaPublicJWK);
		
		System.out.println(signedJWT.verify(verifier));
		/*assertTrue(signedJWT.verify(verifier));
		
		

		// Retrieve / verify the JWT claims according to the app requirements
		assertEquals("alice", signedJWT.getJWTClaimsSet().getSubject());
		assertEquals("https://c2id.com", signedJWT.getJWTClaimsSet().getIssuer());
		assertTrue(new Date().before(signedJWT.getJWTClaimsSet().getExpirationTime()));*/
		
	}
}

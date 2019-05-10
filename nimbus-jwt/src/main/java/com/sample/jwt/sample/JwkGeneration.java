package com.sample.jwt.sample;

import java.security.*;
import java.security.interfaces.*;
import java.util.*;

import com.nimbusds.jose.jwk.*;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.*;

public class JwkGeneration {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		
		
		// Generate the RSA key pair
		KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
		gen.initialize(2048);
		KeyPair keyPair = gen.generateKeyPair();

		// Convert to JWK format
		JWK jwk = new RSAKey.Builder((RSAPublicKey)keyPair.getPublic())
		    .privateKey((RSAPrivateKey)keyPair.getPrivate())
		    .keyUse(KeyUse.SIGNATURE)
		    .keyID(UUID.randomUUID().toString())
		    .build();
		
		System.out.println(jwk.toPublicJWK());

	}

}

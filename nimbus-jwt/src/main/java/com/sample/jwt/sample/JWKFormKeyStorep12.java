package com.sample.jwt.sample;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.io.*;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

//keytool -genkeypair -alias testkey -keyalg RSA -keysize 2048 -dname "CN=Vasim kadiwala, OU=VK, O=VK, L=HMMM, ST=Unknown, C=DK" -validity 365  -keystore D:\Keystore\ExampleJWTkeystore.p12 -storepass 123456 -storetype pkcs12
public class JWKFormKeyStorep12 {

	public static void main(String[] args) throws Exception {
		// Specify the key store type, e.g. JKS
//		KeyStore keyStore = KeyStore.getInstance("JKS");
//
//		// If you need a password to unlock the key store
//		char[] password = "secret".toCharArray();
//
//		// Load the key store from file
//		keyStore = keyStore.load(new FileInputStream("myKeyStore.jks", password);
//
//		// Extract keys and output into JWK set; the secord parameter allows lookup 
//		// of passwords for individual private and secret keys in the store
//		JWKSet jwkSet = JWKSet.load(keyStore, null);
//		
		
		
		/*KeyStore keyStore = KeyStore.getInstance("PKCS12");
		
		InputStream inputStream = JWKFormKeyStore.class.getResourceAsStream("/Examplekeystore.p12");
		keyStore.load(inputStream, "abcdef".toCharArray()); 
		
		java.security.cert.Certificate cert = keyStore.getCertificate("testkey");
		System.out.println(cert.getPublicKey().getFormat());
		
		JWKSet jwkSet = JWKSet.load(keyStore, null);
		
		System.out.println(jwkSet);*/
		
		
        KeyStore keyStore = KeyStore.getInstance("JKS");
		
		InputStream inputStream = JWKFormKeyStorep12.class.getResourceAsStream("/ExampleJWTkeystore.jks");
		keyStore.load(inputStream, "123456".toCharArray()); 
		
		java.security.cert.Certificate cert = keyStore.getCertificate("testkey");
		
		//Public Key
		PublicKey publickey=cert.getPublicKey();
		
		
		//Private Key 
		KeyStore.PasswordProtection keyPassword = new KeyStore.PasswordProtection("123456".toCharArray());
        KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry("testkey", keyPassword);
        PrivateKey privateKey= privateKeyEntry.getPrivateKey();
		
		System.out.println(privateKey);
        
		JWKSet jwkSet = JWKSet.load(keyStore, null);
		
		System.out.println(jwkSet.getKeys());
		
		
		//RSAKey jwk = new RSAKey.Builder((RSAPublicKey) publickey).build();
		
		RSAKey jwk = new RSAKey.Builder((RSAPublicKey) publickey)
			    .privateKey((RSAPrivateKey)privateKey)
			    .keyID("12389")
			    .build();
		
		System.out.println(jwk.toPublicJWK());
		
		signedJWTGeneration(jwk);
	}
	
	
	public static void signedJWTGeneration(RSAKey rsaJWK) throws Exception {
		// Create RSA-signer with the private key
				JWSSigner signer = new RSASSASigner(rsaJWK);

				RSAKey rsaPublicJWK = rsaJWK.toPublicJWK();
				
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
				
				
				String s = signedJWT.serialize();

				// On the consumer side, parse the JWS and verify its RSA signature
				signedJWT = SignedJWT.parse(s);

				JWSVerifier verifier = new RSASSAVerifier(rsaPublicJWK);
				
				System.out.println(signedJWT.verify(verifier));
	}
}

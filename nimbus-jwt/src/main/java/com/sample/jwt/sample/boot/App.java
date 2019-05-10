package com.sample.jwt.sample.boot;

import java.io.File;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.jwk.RSAKey;
import com.sample.jwt.sample.JWKFormKeyStoreJks;

import net.minidev.json.JSONObject;

@SpringBootApplication
@RestController
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);

	}

	
	@GetMapping("/tokens/keys")
	public JWKResponse getJwK() {
		     KeyStore keyStore;
			try {
				keyStore = KeyStore.getInstance("PKCS12");
				InputStream inputStream = JWKFormKeyStoreJks.class.getResourceAsStream("/Examplekeystore.p12");
				keyStore.load(inputStream, "abcdef".toCharArray()); 
				
				java.security.cert.Certificate cert = keyStore.getCertificate("testkey");
				
				//Public Key
				PublicKey publickey=cert.getPublicKey();
				
				RSAKey jwk = new RSAKey.Builder((RSAPublicKey) publickey)
					    .keyID("12389")
					    .build();
				JWKResponse response=new JWKResponse();
				response.setKeys(Arrays.asList(jwk.toPublicJWK().toJSONObject()
						));
				
				//System.out.println(jwk.toPublicJWK().toJSONString());
				
				return response;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
			
	}
	
	@GetMapping("/tokens/keys/.well-known/openid-configuration")
	public OIDCModel getLisJwK() {
		   
			try {
				
				String response ="{\r\n" + 
						"  \"issuer\" : \"http://localhost:8080/uaa/oauth/token\",\r\n" + 
						"  \"authorization_endpoint\" : \"http://localhost/oauth/authorize\",\r\n" + 
						"  \"token_endpoint\" : \"http://localhost/oauth/token\",\r\n" + 
						"  \"token_endpoint_auth_methods_supported\" : [ \"client_secret_basic\", \"client_secret_post\" ],\r\n" + 
						"  \"token_endpoint_auth_signing_alg_values_supported\" : [ \"RS256\", \"HS256\" ],\r\n" + 
						"  \"userinfo_endpoint\" : \"http://localhost/userinfo\",\r\n" + 
						"  \"jwks_uri\" : \"http://localhost/token_keys\",\r\n" + 
						"  \"scopes_supported\" : [ \"openid\", \"profile\", \"email\", \"phone\", \"roles\", \"user_attributes\" ],\r\n" + 
						"  \"response_types_supported\" : [ \"code\", \"code id_token\", \"id_token\", \"token id_token\" ],\r\n" + 
						"  \"subject_types_supported\" : [ \"public\" ],\r\n" + 
						"  \"id_token_signing_alg_values_supported\" : [ \"RS256\", \"HS256\" ],\r\n" + 
						"  \"id_token_encryption_alg_values_supported\" : [ \"none\" ],\r\n" + 
						"  \"claim_types_supported\" : [ \"normal\" ],\r\n" + 
						"  \"claims_supported\" : [ \"sub\", \"user_name\", \"origin\", \"iss\", \"auth_time\", \"amr\", \"acr\", \"client_id\", \"aud\", \"zid\", \"grant_type\", \"user_id\", \"azp\", \"scope\", \"exp\", \"iat\", \"jti\", \"rev_sig\", \"cid\", \"given_name\", \"family_name\", \"phone_number\", \"email\" ],\r\n" + 
						"  \"claims_parameter_supported\" : false,\r\n" + 
						"  \"service_documentation\" : \"http://docs.cloudfoundry.org/api/uaa/\",\r\n" + 
						"  \"ui_locales_supported\" : [ \"en-US\" ]\r\n" + 
						"}";
				
				ObjectMapper ma=new ObjectMapper();
				
				
				OIDCModel oidc=ma.readValue(JWKFormKeyStoreJks.class.getResourceAsStream("/OIDC.json"),OIDCModel.class);
				
				return oidc;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
			
	}
	
	/*@GetMapping("/token/keys")
	public Map<String,List<JSONObject>> getLisJwK() {
		     KeyStore keyStore;
			try {
				keyStore = KeyStore.getInstance("PKCS12");
				InputStream inputStream = JWKFormKeyStoreJks.class.getResourceAsStream("/Examplekeystore.p12");
				keyStore.load(inputStream, "abcdef".toCharArray()); 
				
				java.security.cert.Certificate cert = keyStore.getCertificate("testkey");
				
				//Public Key
				PublicKey publickey=cert.getPublicKey();
				
				RSAKey jwk = new RSAKey.Builder((RSAPublicKey) publickey)
					    .keyID("12389")
					    .build();
				
				Map<String,List<JSONObject>> keys=new HashMap<String,List<JSONObject>>();
				keys.put("keys", Arrays.asList(jwk.toPublicJWK().toJSONObject()));
				
				return keys;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
			
	}*/
}

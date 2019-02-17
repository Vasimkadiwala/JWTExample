package com.vk.jwt.example.utils;

import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Decoder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vk.jwt.example.exception.InvalidTokenException;
import com.vk.jwt.example.model.ClaimSet;
import com.vk.jwt.example.model.JwtHeader;
import com.vk.jwt.example.model.JwtToken;

public class JwtTokenExtractor {
	
	
	private static Decoder decoder=null;
	private static ObjectMapper mapper=null;
	static{
		decoder=Base64.getDecoder();
		mapper=new ObjectMapper();
	}
	
	
	public JwtToken tokenExtractor(String token) throws JsonParseException, JsonMappingException, IOException {
		
		String [] tokenElements=token.split("\\.");
		
		if(tokenElements.length != 3) {
			throw new InvalidTokenException("One or more element/s of token is/are missing ");
		}
		
		JwtHeader header=mapper.readValue(decoder.decode(tokenElements[0]), JwtHeader.class);
		ClaimSet payload=mapper.readValue(decoder.decode(tokenElements[1]), ClaimSet.class);
		
		JwtToken jwtToken=new JwtToken();
		jwtToken.setHeader(header);
		jwtToken.setEncodedHeader(tokenElements[0]);
		jwtToken.setPayload(payload);
		jwtToken.setEncodedPayload(tokenElements[1]);
		jwtToken.setEncodedSignature(tokenElements[2]);
		
		return jwtToken;
	}

}

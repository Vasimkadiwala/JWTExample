package com.vk.jwt.example.ppki.helper;

import com.vk.jwt.example.exception.UnauthorizedException;
import com.vk.jwt.example.model.JwtRegistration;
import com.vk.jwt.example.model.JwtToken;
import com.vk.jwt.example.model.KeyDetails;
import com.vk.jwt.example.model.SymAuthDetails;

public class RegistrationRetreiver {
	
	public JwtRegistration fetchRegistration(JwtToken token,long requestReceivedTime) {
		
		/**
		 * 1. Get Registration detail from DB based on clientId
		 * 2. check if request has expired
		 */
		JwtRegistration jwtRegistration=mockDBServicePPKI(token.getPayload().getIss());
		
		if((requestReceivedTime-token.getPayload().getExp()) > jwtRegistration.getExpDiffinSeconds()) {
			throw new UnauthorizedException("Request expired");
		}
		
		return jwtRegistration;
	}
	
	
	private JwtRegistration mockDBServicePPKI(String clientId) {
		
		
		if(clientId.contains("Symm")) {
			
			SymAuthDetails sym=new SymAuthDetails();
			sym.setClientSecret("testClientSecret".toCharArray());
			
			JwtRegistration reg=new JwtRegistration();
			
			reg.setExpDiffinSeconds(2000);
			reg.setSymAuthDteails(sym);
			return reg;
		}
		
		KeyDetails keyDetails=new KeyDetails();
		keyDetails.setAlias("testkey");
		keyDetails.setKeyStoreName("/Examplekeystore.p12");
		keyDetails.setStorePass("abcdef".toCharArray());
		
		
		JwtRegistration reg=new JwtRegistration();
		
		reg.setExpDiffinSeconds(20);
		reg.setKeyDetails(keyDetails);
		
		
		
		return reg;
		
	}

}

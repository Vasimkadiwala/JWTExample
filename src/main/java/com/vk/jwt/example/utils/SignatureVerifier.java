package com.vk.jwt.example.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.Base64;
import java.util.Base64.Decoder;

import com.vk.jwt.example.exception.InvalidTokenException;
import com.vk.jwt.example.model.JwtRegistration;
import com.vk.jwt.example.model.JwtToken;
import com.vk.jwt.example.model.SymAuthDetails;
import com.vk.jwt.example.ppki.helper.PublicKeyGenerator;

public class SignatureVerifier {

	private static final String SIGN_ALG = "SHA256withRSA";
	private static final String UTF_8 = "UTF-8";
	private static Decoder decoder = Base64.getDecoder();

	public boolean verifySignature(JwtRegistration jwtRegistration, JwtToken token)
			throws InvalidKeyException, SignatureException, UnsupportedEncodingException, NoSuchAlgorithmException,
			KeyStoreException, CertificateException, UnrecoverableEntryException, IOException {

		String headerAlg = token.getHeader().getAlg();
		if (headerAlg.startsWith("RS") || headerAlg.startsWith("rs")) {

			return verifySignature(token,
					new PublicKeyGenerator().getPublicKeyFromKeyStore(jwtRegistration.getKeyDetails()));

		} else if (headerAlg.startsWith("HS") || headerAlg.startsWith("hs")) {

			return verifySignature(token, jwtRegistration.getSymAuthDteails());

		} else {
			throw new InvalidTokenException("Invalid/unsupported Algorithm in header");
		}

	}

	//This should be private
	public boolean verifySignature(JwtToken token, PublicKey publicKey)
			throws SignatureException, UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {

		Signature signature = Signature.getInstance(SIGN_ALG);
		signature.initVerify(publicKey);

		signature.update((token.getEncodedHeader() + "." + token.getEncodedPayload()).getBytes(UTF_8));

		return signature.verify(decoder.decode(token.getEncodedSignature()));

	}

	private boolean verifySignature(JwtToken token, SymAuthDetails authDetails)
			throws SignatureException, UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {

		return false;
	}
}

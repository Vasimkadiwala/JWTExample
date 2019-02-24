package com.vk.jwt.example.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
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
import java.util.Formatter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.vk.jwt.example.exception.InvalidTokenException;
import com.vk.jwt.example.model.JwtRegistration;
import com.vk.jwt.example.model.JwtToken;
import com.vk.jwt.example.model.SymAuthDetails;
import com.vk.jwt.example.ppki.helper.PublicKeyGenerator;

/**
 * 
 * @author Vasim Kadiwala
 *
 */
public class SignatureVerifier {

	private static final String SIGN_ALG = "SHA256withRSA";
	private static Decoder decoder = Base64.getDecoder();
	private static final String HMAC_SHA512 = "HmacSHA512";
	private static final String HMAC_SHA256 = "HmacSHA256";

	// All methods must be static

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

	// This should be private
	public boolean verifySignature(JwtToken token, PublicKey publicKey)
			throws SignatureException, UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {

		Signature signature = Signature.getInstance(SIGN_ALG);
		signature.initVerify(publicKey);

		signature.update((token.getEncodedHeader() + "." + token.getEncodedPayload()).getBytes(StandardCharsets.UTF_8));

		return signature.verify(decoder.decode(token.getEncodedSignature()));

	}

	private boolean verifySignature(JwtToken token, SymAuthDetails authDetails)
			throws SignatureException, UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {

		SecretKeySpec secretKeySpec = new SecretKeySpec(
				new String(authDetails.getClientSecret()).getBytes(StandardCharsets.UTF_8),
				getALgForSym(token.getHeader().getAlg()));
		Mac mac = Mac.getInstance(HMAC_SHA512);
		mac.init(secretKeySpec);
		String computedSignautre = toHexString(mac.doFinal((token.getEncodedHeader() + "." + token.getEncodedPayload()).getBytes(StandardCharsets.UTF_8)));
		String receivedSignature = toHexString(decoder.decode(token.getEncodedSignature()));
		
		return computedSignautre.equals(receivedSignature);
	}

	private static String getALgForSym(String headerAlg) {

		if (headerAlg.contains("256")) {
			return HMAC_SHA256;
		} else if (headerAlg.contains("512")) {
			return HMAC_SHA512;
		} else {
			throw new InvalidTokenException("Invalid algorithm in header");
		}

	}

	private static String toHexString(byte[] bytes) {
		try(Formatter formatter = new Formatter()){;
			for (byte b : bytes) {
				formatter.format("%02x", b);
			}
			return formatter.toString();
		}
	}
}

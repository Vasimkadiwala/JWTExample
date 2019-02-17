package com.vk.jwt.example.ppki.helper;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.UnrecoverableEntryException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import com.vk.jwt.example.model.KeyDetails;
import com.vk.jwt.example.utils.CommonUtil;
import com.vk.jwt.example.utils.Constants;

public class PublicKeyGenerator {

	private static final String ALGO = "RSA";
	private static final String KEYSTORE_TYPE = "PKCS12";

	public void generatePrivateKeyFromKeystore(KeyPair keyPair, String privateKeyFileName) throws IOException {

		if (keyPair != null) {
			try (FileWriter fileWriter = new FileWriter(privateKeyFileName + Constants.PRIVATE_KEY_EXTN.getValue())) {
				String encoded = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
				fileWriter.write(Constants.BEGIN_PUBLIC_KEY.getValue() + "\n");
				fileWriter.write(encoded);
				fileWriter.write("\n");
				fileWriter.write(Constants.END_PUBLIC_KEY.getValue());
			}

		}
	}

	public PublicKey getPublicKeyFromFile(String fileName, String filePathAlongWithFileName)
			throws IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeySpecException {

		Path path = null;
		if (CommonUtil.isNullOrEmpty(filePathAlongWithFileName) && !CommonUtil.isNullOrEmpty(fileName)) {
			path = Paths.get(ClassLoader.getSystemResource(fileName).toURI());
		} else {
			path = Paths.get(filePathAlongWithFileName);
		}

		String privateKeyContent = new String(Files.readAllBytes(path));
		privateKeyContent = privateKeyContent.replaceAll("\\n", "").replace(Constants.BEGIN_PUBLIC_KEY.getValue(), "")
				.replace(Constants.END_PUBLIC_KEY.getValue(), "");

		KeyFactory keyFactory = KeyFactory.getInstance(ALGO);

		PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyContent));
		return keyFactory.generatePublic(keySpecPKCS8);
	}

	public PublicKey getPublicKeyFromKeyStore(KeyDetails keyDetails) throws IOException, KeyStoreException,
			NoSuchAlgorithmException, CertificateException, UnrecoverableEntryException {

		if (CommonUtil.isNullOrEmpty(keyDetails.getKeyStorePath())
				&& !CommonUtil.isNullOrEmpty(keyDetails.getKeyStoreName())) {
			try (InputStream inputStream = PrivateKeyGenerator.class
					.getResourceAsStream(keyDetails.getKeyStoreName())) {
				return getPublicKey(inputStream, keyDetails);
			}
		} else {
			try (InputStream inputStream = new FileInputStream(keyDetails.getKeyStorePath())) {
				return getPublicKey(inputStream, keyDetails);
			}
		}

	}

	private PublicKey getPublicKey(InputStream inputStream, KeyDetails keyDetails) throws KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableEntryException {
		KeyStore keyStore = KeyStore.getInstance(KEYSTORE_TYPE);
		keyStore.load(inputStream, keyDetails.getStorePass());

		Certificate cert = keyStore.getCertificate(keyDetails.getAlias());
		return cert.getPublicKey();
	}

	public PublicKey getPublicKeyFromKeyStore(String keyStoreName, String keyStorePath, char[] storePass, String alias)
			throws IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException,
			UnrecoverableEntryException {

		if (CommonUtil.isNullOrEmpty(keyStorePath) && !CommonUtil.isNullOrEmpty(keyStoreName)) {
			try (InputStream inputStream = PrivateKeyGenerator.class.getResourceAsStream(keyStoreName)) {
				return getPublicKey(inputStream, storePass, alias);
			}
		} else {
			try (InputStream inputStream = new FileInputStream(keyStorePath)) {
				return getPublicKey(inputStream, storePass, alias);
			}
		}

	}

	private PublicKey getPublicKey(InputStream inputStream, char[] storePass, String alias) throws KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableEntryException {
		KeyStore keyStore = KeyStore.getInstance(KEYSTORE_TYPE);
		keyStore.load(inputStream, storePass);

		Certificate cert = keyStore.getCertificate(alias);
		return cert.getPublicKey();
	}

}

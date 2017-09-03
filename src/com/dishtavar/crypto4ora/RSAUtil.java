package com.dishtavar.crypto4ora;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * 
 * @author atul.singh Encryption and Sign logic
 *
 */
public class RSAUtil {
	private static final String ALGORITHM = "RSA";

	public static String encrypt(String valueToEncrypt, String publicKey) throws Exception {
		byte[] publicKeyByteArr = Base64.decode(publicKey);
		PublicKey key = KeyFactory.getInstance(ALGORITHM).generatePublic(new X509EncodedKeySpec(publicKeyByteArr));
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.PUBLIC_KEY, key);
		byte[] encryptedBytes = cipher.doFinal(valueToEncrypt.getBytes());
		return Base64.encode(encryptedBytes);
	}

	public static String decrypt(String valueToDecrypt, String privateKey) throws Exception {
		byte[] privateByteArr = Base64.decode(privateKey);
		PrivateKey key = KeyFactory.getInstance(ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(privateByteArr));
		byte[] valueToDecryptBArr = Base64.decode(valueToDecrypt);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.PRIVATE_KEY, key);
		byte[] decryptedBytes = cipher.doFinal(valueToDecryptBArr);
		return CryptoHelper.getStringFromByte(decryptedBytes);
	}

	public static String sign(String valueToEncrypt, String privateKey) throws Exception {
		byte[] privateKeyByteArr = Base64.decode(privateKey);
		PrivateKey key = KeyFactory.getInstance(ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(privateKeyByteArr));
		Signature rsa = Signature.getInstance("SHA1withRSA");
		rsa.initSign(key);
		rsa.update(valueToEncrypt.getBytes());
		String signature = Base64.encode(rsa.sign());
		return signature;
	}

	public static boolean verify(String messageText, String signature, String publicKey) throws Exception {
		byte[] publicKeyByteArr = Base64.decode(publicKey);
		PublicKey key = KeyFactory.getInstance(ALGORITHM).generatePublic(new X509EncodedKeySpec(publicKeyByteArr));
		Signature sig = Signature.getInstance("SHA1withRSA");
		sig.initVerify(key);
		sig.update(messageText.getBytes());
		return sig.verify(Base64.decode(signature));

	}

	public static byte[] encrypt(byte[] valueToDecrypt, byte[] publicKey) throws Exception {
		PublicKey key = KeyFactory.getInstance(ALGORITHM).generatePublic(new X509EncodedKeySpec(publicKey));
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.PUBLIC_KEY, key);
		byte[] encryptedBytes = cipher.doFinal(valueToDecrypt);
		return encryptedBytes;
	}

	public static byte[] decrypt(byte[] valueToDecrypt, byte[] privateKey) throws Exception {
		PrivateKey key = KeyFactory.getInstance(ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(privateKey));
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.PRIVATE_KEY, key);
		byte[] decryptedBytes = cipher.doFinal(valueToDecrypt);
		return decryptedBytes;
	}
}

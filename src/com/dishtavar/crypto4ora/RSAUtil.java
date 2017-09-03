package com.dishtavar.crypto4ora;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * 
 * @author atul.singh
 *
 */
public class RSAUtil {
	private static final String ALGORITHM = "RSA";

	/**
	 * 
	 * @param valueToEncrypt
	 * @param publicKey
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String valueToEncrypt, String publicKey) throws Exception {
		byte[] publicKeyByteArr = Base64.decode(publicKey);
		PublicKey key = KeyFactory.getInstance(ALGORITHM).generatePublic(new X509EncodedKeySpec(publicKeyByteArr));
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.PUBLIC_KEY, key);
		byte[] encryptedBytes = cipher.doFinal(valueToEncrypt.getBytes());
		return Base64.encode(encryptedBytes);
	}

	/**
	 * 
	 * @param valueToDecrypt
	 * @param privateKey
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String valueToDecrypt, String privateKey) throws Exception {
		byte[] privateKeyByteArr = Base64.decode(privateKey);
		byte[] valueToDecryptBArr = Base64.decode(valueToDecrypt);
		PrivateKey key = KeyFactory.getInstance(ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(privateKeyByteArr));
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.PRIVATE_KEY, key);
		byte[] decryptedBytes = cipher.doFinal(valueToDecryptBArr);
		return CryptoHelper.getStringFromByte(decryptedBytes);
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

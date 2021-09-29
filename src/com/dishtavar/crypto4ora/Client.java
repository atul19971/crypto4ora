package com.dishtavar.crypto4ora;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Signature;

/**
 * 
 * @author atul.singh
 * This is for testing only
 */
public class Client {
	private static String publicKeyStr = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAMYjmZwg3LuDUv1SLP7phOgqUgMx1jxmmLJV2lpqMjQRlGi/XcfetVJ0jHTsBLimfPY4653EBzOoiZ+NdgLhYzcCAwEAAQ==";
	private static String privateKeyStr = "MIIBVgIBADANBgkqhkiG9w0BAQEFAASCAUAwggE8AgEAAkEAxiOZnCDcu4NS/VIs/umE6CpSAzHWPGaYslXaWmoyNBGUaL9dx961UnSMdOwEuKZ89jjrncQHM6iJn412AuFjNwIDAQABAkBDzECunMwK2afXCHxfCVax/nErqKzu5AhR1XCuyXG+QL+NrAjzF4MAyD4cL4W02LC5SdJVmEf2h55bBX1FEoRxAiEA4ztCZecHLKIlFCpKpfYnFzceWkiQOYcCUjcmknrsJgkCIQDfOW8Og7HlFchu6guSpuO/XGtLGQ6GPH5XnbFZ1p2PPwIhAMVdZEeUScP3tdL1BM9vpPZiFof+fN6qqFZ2tpcGE3TZAiEAqNbmO1I9IgtB2TLJohj9NmaO+PRw2fOzlE3AMTV1Yz0CIQDcm/cc4tnRcMS6l8vABGzWN+I8SbTqc1zFS4GQqeNG/g==";

	public static void main(String[] args) throws Exception {
		
		// KeyPair generateKeyPair = GenerateKey.generateKeyPair();
		 //byte[] publicKey= Base64.decode(publicKeyStr);
		// byte[] privateKey=Base64.decode(privateKeyStr);
		//// byte[] encryptedDatab = RSAUtil.encrypt("It is test".getBytes(), publicKey);

		//byte[] decryptedDatab = RSAUtil.decrypt(encryptedDatab, privateKey);

		signTest();

	}
	public static void cryptoTest() throws Exception{
		String encryptedData = RSAUtil.encrypt("It is test", publicKeyStr);
		System.out.println(new String(encryptedData));
		String decryptedData = RSAUtil.decrypt(new String(encryptedData), privateKeyStr);
		System.out.println(decryptedData);

	}
	public static void signTest() throws Exception{
		KeyPair keyPair=GenerateKey.generateKeyPair(2048);
		 String publicKeyStr = Base64.encode(keyPair.getPublic().getEncoded());
		String privateKeyStr = Base64.encode(keyPair.getPrivate().getEncoded());
		String signature = RSAUtil.sign("It is", privateKeyStr);
		System.out.println(new String(signature)+signature.length());
		boolean decryptedData = RSAUtil.verify("It is",signature, publicKeyStr);

		System.out.println(decryptedData);

	}
	
	public static void test() throws Exception {
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");

	    keyGen.initialize(512, new SecureRandom());

	    KeyPair keyPair = keyGen.generateKeyPair();
	    Signature signature = Signature.getInstance("SHA1withRSA");

	    signature.initSign(keyPair.getPrivate());

	    byte[] message = "abc".getBytes();
	    signature.update(message);

	    byte[] sigBytes = signature.sign();
	    signature.initVerify(keyPair.getPublic());
	    signature.update(message);
	    System.out.println(signature.verify(sigBytes));
		
	}

}

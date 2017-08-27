package com.dishtavar.crypto4ora;

public class Client {
	private static String publicKeyStr = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAMYjmZwg3LuDUv1SLP7phOgqUgMx1jxmmLJV2lpqMjQRlGi/XcfetVJ0jHTsBLimfPY4653EBzOoiZ+NdgLhYzcCAwEAAQ==";
	private static String privateKeyStr = "MIIBVgIBADANBgkqhkiG9w0BAQEFAASCAUAwggE8AgEAAkEAxiOZnCDcu4NS/VIs/umE6CpSAzHWPGaYslXaWmoyNBGUaL9dx961UnSMdOwEuKZ89jjrncQHM6iJn412AuFjNwIDAQABAkBDzECunMwK2afXCHxfCVax/nErqKzu5AhR1XCuyXG+QL+NrAjzF4MAyD4cL4W02LC5SdJVmEf2h55bBX1FEoRxAiEA4ztCZecHLKIlFCpKpfYnFzceWkiQOYcCUjcmknrsJgkCIQDfOW8Og7HlFchu6guSpuO/XGtLGQ6GPH5XnbFZ1p2PPwIhAMVdZEeUScP3tdL1BM9vpPZiFof+fN6qqFZ2tpcGE3TZAiEAqNbmO1I9IgtB2TLJohj9NmaO+PRw2fOzlE3AMTV1Yz0CIQDcm/cc4tnRcMS6l8vABGzWN+I8SbTqc1zFS4GQqeNG/g==";

	public static void main(String[] args) throws Exception {
		 //GenerateKey.generateRSAKeys(512);
		// KeyPair generateKeyPair = GenerateKey.generateKeyPair();
		 //byte[] publicKey= Base64.decode(publicKeyStr);
		// byte[] privateKey=Base64.decode(privateKeyStr);
		//// byte[] encryptedDatab = RSAUtil.encrypt("It is test".getBytes(), publicKey);

		//byte[] decryptedDatab = RSAUtil.decrypt(encryptedDatab, privateKey);

		String encryptedData = RSAUtil.encrypt("It is test", publicKeyStr);
		System.out.println(new String(encryptedData));
		String decryptedData = RSAUtil.decrypt(new String("o12LNQMqeBSjJW2ZbQtU8UUz7MDE2Fash/tqg85yYoUdIKvtUoR/g7DWcue1D0nI1T+Dr/gsojW1qQTC2kLSGA=="), privateKeyStr);

		//System.out.println(new String(decryptedData));
		System.out.println(decryptedData);

	}

}

package com.awhyse.concurrent.validate;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.security.crypto.codec.Base64;


public class DESPlus {

	public static byte[] encryptDES(String encryptString, String encryptKey) throws Exception {
		byte[] iv = { 1, 2, 3, 4, 5, 6, 7, 8 };
		IvParameterSpec zeroIv = new IvParameterSpec(iv);
		SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
		byte[] encryptedData = cipher.doFinal(encryptString.getBytes());
		return encryptedData;
	}

    public static byte[] encryptAES(String encryptString, String encryptKey) throws Exception {
        byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        IvParameterSpec zeroIv = new IvParameterSpec(iv);
        SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
        byte[] encryptedData = cipher.doFinal(encryptString.getBytes());
        return encryptedData;
    }

	public static byte[] decryptAES(byte[] bytes, String encryptKey) throws Exception {
	    byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        IvParameterSpec zeroIv = new IvParameterSpec(iv);
		SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
		byte[] encryptedData = cipher.doFinal(bytes);
		return encryptedData;
	}

	public static byte[] decryptDES(byte[] bytes, String encryptKey) throws Exception {
		byte[] iv = { 1, 2, 3, 4, 5, 6, 7, 8 };
		IvParameterSpec zeroIv = new IvParameterSpec(iv);
		SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
		byte[] encryptedData = cipher.doFinal(bytes);
		return encryptedData;
	}

	public static void main(String[] args) throws Exception {
		String rawInfo = "AG8WEG4/ss1ulJq68Vw+E0gKwYJUGtkX8WJGoYelupXmZ7uLMdO+YHdEh07bIVxOVE8yVT/sV7QJGrvYdcREXxtRIdgY8ctHIEwUez5IarU=";
		System.out.println(rawInfo);
		String key = RandomStringUtils.random(10, true, false);
		System.out.println(key);

		byte[] cc = encryptAES(rawInfo, key);
		String mm = Base64.encode(cc).toString();
		System.out.println(mm);

       /* byte[] by = decryptAES(cc, key);
        System.out.println(new String(by));*/


		/*byte[] des = decryptDES(Base64.decode(rawInfo), "xN1DwYQ1");
		String t = new String(des);
		System.out.println(t);*/

		/*String  mmm= "YXR05AEzfPe+n4IW0ehY9laJ2m8nb4rHBxerC8P0upCTg+j3HVgKDbKFO8KXF7RGn0mBD141swlMwXlgN5Dr4MgS4NVAETOjZKeYfbgNlglqsJPsRWQMXs3oTlHfIsKmYciJtF8IlWOLbZOQEVi1YbjGDuQXVJWOLztbqzJhBStwuEsK2TA4VHdMPMO3Dv0a";
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] b=md.digest(mmm.getBytes());
		System.out.println(Base64.encode(b));
*/

	}


}

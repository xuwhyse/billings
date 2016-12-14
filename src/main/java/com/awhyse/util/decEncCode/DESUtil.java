package com.awhyse.util.decEncCode;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * DES,3DES,AES对称加密 DES 算法把 64 位的明文输入块变为 64 位的密文输出块，它所使用的密钥也是 64 位
 * 
 * AES，全称为“Advanced Encryption Standard”，中文名“高级加密标准”，在密码学中又称 Rijndael 加密法，
 * 是美国联邦政府采用的一种区块加密标准。AES 加密算法作为新一代的数据加密标准汇聚了强安全性、高性能、 高效率、易用和灵活等优点。AES
 * 设计有三个密钥长度：128，192，256 位。相对而言，AES 的 128 密钥比 DES 的 56 密钥强了 1021 倍。 author:xumin
 * 2016-10-20 上午11:36:30
 */
public class DESUtil {
	// 长度24字节
	private final static String DES_3_KEY = "213456781234567812345678";
	// 长度16，24，32字节
	private final static String AES_KEY = "1234567812345678";

	private static byte[] decByDes(byte[] data,String key) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom random = new SecureRandom();
		DESKeySpec desKey = new DESKeySpec(key.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(desKey);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, securekey, random);
		return cipher.doFinal(data);
	}
	/**
	 * 
	 * @param data
	 * @param key  编码的key ，长度8
	 * @return
	 * @throws Exception
	 * author:xumin 
	 * 2016-10-20 下午1:47:45
	 */
	private static byte[] encByDes(byte[] data ,String key) throws Exception {
		DESKeySpec desKey = new DESKeySpec(key.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(desKey);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, securekey);
		return cipher.doFinal(data);
	}

	//=======================================================
	private static byte[] decBy3Des(byte[] data) throws Exception {
		SecureRandom random = new SecureRandom();
		SecretKey deskey = new SecretKeySpec(DES_3_KEY.getBytes(), "DESede");
		Cipher cipher = Cipher.getInstance("DESede");
		cipher.init(Cipher.DECRYPT_MODE, deskey, random);
		return cipher.doFinal(data);
	}

	private static byte[] encBy3Des(byte[] data) throws Exception {
		SecureRandom random = new SecureRandom();
		SecretKey deskey = new SecretKeySpec(DES_3_KEY.getBytes(), "DESede");
		Cipher cipher = Cipher.getInstance("DESede");
		cipher.init(Cipher.ENCRYPT_MODE, deskey, random);
		return cipher.doFinal(data);
	}

	
	//=======================================================
	private static byte[] decByAes(byte[] data) throws Exception {
		SecretKey deskey = new SecretKeySpec(AES_KEY.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, deskey);
		return cipher.doFinal(data);
	}

	private static byte[] encByAes(byte[] data) throws Exception {
		SecretKey deskey = new SecretKeySpec(AES_KEY.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, deskey);
		return cipher.doFinal(data);
	}

	public static void main(String[] args) throws Exception {
		String DES_KEY = "dsgfdsdf";//8位
		System.out.println("DES密钥:\n" + DES_KEY);
		System.out.println("DES密钥字节长度:\n" + DES_KEY.getBytes().length);
		String word = "123";
		System.out.println("原文：" + word);
		System.out.println("=============DES=============");
		byte b[] = encByDes(word.getBytes(),DES_KEY);
		String encWord = new String(b);
		System.out.println("加密后：" + encWord);
		System.out.println("解密后：" + new String(decByDes(b,DES_KEY)));
		System.out.println("=============3DES=============");
		System.out.println("3DES密钥:" + DES_3_KEY);
		System.out.println("3DES密钥字节长度:" + DES_3_KEY.getBytes().length);
		b = encBy3Des(word.getBytes());
		encWord = new String(b);
		System.out.println("加密后：" + encWord);
		System.out.println("解密后：" + new String(decBy3Des(b)));
		System.out.println("=============AES=============");
		System.out.println("AES密钥:" + AES_KEY);
		System.out.println("AES密钥字节长度:" + AES_KEY.getBytes().length);
		b = encByAes(word.getBytes());
		encWord = new String(b);
		System.out.println("加密后：" + encWord);
		System.out.println("解密后：" + new String(decByAes(b)));
	}
}

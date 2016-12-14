package com.awhyse.util.decEncCode;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import com.alibaba.fastjson.JSONObject;

public class RSAUtil {
	 /** 
     * 加密算法RSA 
     */  
    public static final String KEY_ALGORITHM = "RSA";  
  
    /** 
     * 签名算法 
     */  
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";  
  
    /** 
     * 获取公钥的key 
     */  
    private static final String PUBLIC_KEY = "RSAPublicKey";  
  
    private static final int keyLen = 512;// 密钥长度  
  
    /** 
     * 获取私钥的key 
     */  
    private static final String PRIVATE_KEY = "RSAPrivateKey";  
  
    /** 
     * RSA最大加密明文大小 
     */  
    private static final int MAX_ENCRYPT_BLOCK = keyLen / 8 - 11;  
  
    /** 
     * RSA最大解密密文大小 
     */  
    private static final int MAX_DECRYPT_BLOCK = keyLen / 8;  
  
    /** 
     *  
     * genKeyPair:(). <br/>   
       
     *  生成公司钥对，返回的是经过BASE64编码的密钥 
     * @author chiwei   
     * @return 
     * @throws Exception   
     * @since JDK 1.6 
     */  
    public static Map<String, Object> genKeyPair() throws Exception {  
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);  
        keyPairGen.initialize(keyLen);  
        KeyPair keyPair = keyPairGen.generateKeyPair();  
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();  
        Map<String, Object> keyMap = new HashMap<String, Object>(2);  
        keyMap.put(PUBLIC_KEY, publicKey);  
        keyMap.put(PRIVATE_KEY, privateKey);  
        return keyMap;  
    }  
  
    /** 
     *  
     * sign:(). <br/>   
       
     *  用私钥进行签名，返回BASE64编码的内容 
     * @author chiwei   
     * @param data 
     * @param privateKey 
     * @return 
     * @throws Exception   
     * @since JDK 1.6 
     */  
    public static String sign(byte[] data, String privateKey) throws Exception {  
        byte[] keyBytes = Base64Util.decode(privateKey);  
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);  
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);  
        signature.initSign(privateK);  
        signature.update(data);  
        return Base64Util.encode(signature.sign());  
    }  
  
    /** 
     *  
     * verify:(). <br/>   
       
     *  用公钥对签名进行验证 
     * @author chiwei   
     * @param data 
     * @param publicKey 
     * @param sign 
     * @return 
     * @throws Exception   
     * @since JDK 1.6 
     */  
    public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {  
        byte[] keyBytes = Base64Util.decode(publicKey);  
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
        PublicKey publicK = keyFactory.generatePublic(keySpec);  
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);  
        signature.initVerify(publicK);  
        signature.update(data);  
        return signature.verify(Base64Util.decode(sign));  
    }  
  
    /** 
     *  
     * decryptByPrivateKey:(). <br/>   
       
     *  私钥解密 
     * @author chiwei   
     * @param encryptedData 
     * @param privateKey 
     * @return 
     * @throws Exception   
     * @since JDK 1.6 
     */  
    public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey)  
            throws Exception {  
        byte[] keyBytes = Base64Util.decode(privateKey);  
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);  
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
        cipher.init(Cipher.DECRYPT_MODE, privateK);  
        int inputLen = encryptedData.length;  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        int offSet = 0;  
        byte[] cache;  
        int i = 0;  
        // 对数据分段解密  
        while (inputLen - offSet > 0) {  
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {  
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);  
            } else {  
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);  
            }  
            out.write(cache, 0, cache.length);  
            i++;  
            offSet = i * MAX_DECRYPT_BLOCK;  
        }  
        byte[] decryptedData = out.toByteArray();  
        out.close();  
        return decryptedData;  
    }  
  
    /** 
     *  
     * decryptByPublicKey:(). <br/>   
       
     *  公钥解密 
     * @author chiwei   
     * @param encryptedData 
     * @param publicKey 
     * @return 
     * @throws Exception   
     * @since JDK 1.6 
     */  
    public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey)  
            throws Exception {  
        byte[] keyBytes = Base64Util.decode(publicKey);  
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
        Key publicK = keyFactory.generatePublic(x509KeySpec);  
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
        cipher.init(Cipher.DECRYPT_MODE, publicK);  
        int inputLen = encryptedData.length;  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        int offSet = 0;  
        byte[] cache;  
        int i = 0;  
        // 对数据分段解密  
        while (inputLen - offSet > 0) {  
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {  
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);  
            } else {  
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);  
            }  
            out.write(cache, 0, cache.length);  
            i++;  
            offSet = i * MAX_DECRYPT_BLOCK;  
        }  
        byte[] decryptedData = out.toByteArray();  
        out.close();  
        return decryptedData;  
    }  
  
    /** 
     *  
     * encryptByPublicKey:(). <br/>   
       
     *  公钥加密 
     * @author chiwei   
     * @param data 
     * @param publicKey 
     * @return 
     * @throws Exception   
     * @since JDK 1.6 
     */  
    public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {  
        byte[] keyBytes = Base64Util.decode(publicKey);  
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
        Key publicK = keyFactory.generatePublic(x509KeySpec);  
        // 对数据加密  
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
        cipher.init(Cipher.ENCRYPT_MODE, publicK);  
        int inputLen = data.length;  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        int offSet = 0;  
        byte[] cache;  
        int i = 0;  
        // 对数据分段加密  
        while (inputLen - offSet > 0) {  
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {  
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);  
            } else {  
                cache = cipher.doFinal(data, offSet, inputLen - offSet);  
            }  
            out.write(cache, 0, cache.length);  
            i++;  
            offSet = i * MAX_ENCRYPT_BLOCK;  
        }  
        byte[] encryptedData = out.toByteArray();  
        out.close();  
        return encryptedData;  
    }  
  
    /** 
     *  
     * encryptByPrivateKey:(). <br/>   
       
     *  私钥加密 
     * @author chiwei   
     * @param data 
     * @param privateKey 
     * @return 
     * @throws Exception   
     * @since JDK 1.6 
     */  
    public static byte[] encryptByPrivateKey(byte[] data, String privateKey) throws Exception {  
        byte[] keyBytes = Base64Util.decode(privateKey);  
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);  
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
        cipher.init(Cipher.ENCRYPT_MODE, privateK);  
        int inputLen = data.length;  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        int offSet = 0;  
        byte[] cache;  
        int i = 0;  
        // 对数据分段加密  
        while (inputLen - offSet > 0) {  
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {  
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);  
            } else {  
                cache = cipher.doFinal(data, offSet, inputLen - offSet);  
            }  
            out.write(cache, 0, cache.length);  
            i++;  
            offSet = i * MAX_ENCRYPT_BLOCK;  
        }  
        byte[] encryptedData = out.toByteArray();  
        out.close();  
        return encryptedData;  
    }  
  
    /** 
     *  
     * getPrivateKey:(). <br/>   
       
     *  获取BASE64编码的私钥 
     * @author chiwei   
     * @param keyMap 
     * @return 
     * @throws Exception   
     * @since JDK 1.6 
     */  
    public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {  
        Key key = (Key) keyMap.get(PRIVATE_KEY);  
        return Base64Util.encode(key.getEncoded());  
    }  
  
    /** 
     *  
     * getPublicKey:(). <br/>   
       
     *  获取BASE64编码的公钥 
     * @author chiwei   
     * @param keyMap 
     * @return 
     * @throws Exception   
     * @since JDK 1.6 
     */  
    public static String getPublicKey(Map<String, Object> keyMap) throws Exception {  
        Key key = (Key) keyMap.get(PUBLIC_KEY);  
        return Base64Util.encode(key.getEncoded());  
    }  
  
    public static void main(String args[]) {  
        JSONObject json = new JSONObject();  
        json.put("deviceId", "1");  
        json.put("clientVersion", "2");  
        json.put("platform", "1");  
        JSONObject position = new JSONObject();  
        position.put("location_latitude_key", "10.98");  
        position.put("location_longtitude_key", "110.98");  
        json.put("position", position);  
        json.put("city", "杭州市");  
        json.put("locatedAddress", "");  
        json.put("romVersion", "1");  
        json.put("commandId", 0x125);  
        json.put("userId", 10049);  
        json.put("orderId", 10053);  
        System.out.println(json);  
        String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIc7AHO19/BdTKI18QDeqOxHCNHI w8+2C15kvE5h+kYoQxCq3IdVDsJJKFJSAtfOe7WHgLUDOpAZaHcUnpW7HpUC AwEAAQ==";// "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCZozfHvyz7LZIAo7BPX2HE DHc4CZR9UIwPC/omfaJ62f8l2na+YRKQHBE220+RQfqupVMKmy7QYo+hAjs4 MqCgCedqyWBwHhrhTbQp5FuZ/xaqFCuz/ntyYzz7NrYoD3ijtwBXUrx6kTJf xtkB/Nhd317JHuzxPR9bNUj3K5pLfwIDAQAB";  
        String privateKey = "MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEAhzsAc7X38F1M ojXxAN6o7EcI0cjDz7YLXmS8TmH6RihDEKrch1UOwkkoUlIC1857tYeAtQM6 kBlodxSelbselQIDAQABAkBwhC4XGMPoMajumpUhFSJWHbB/5FzQOXbyHjzz tt/neKONwuB25QJ+DehwtKGnjDtcrSlUbNYIc0L95eKPGvJBAiEA39pNrReU WThfcQkz1TEmL+Pqh09Uf5KdkUJFRn3rEsUCIQCapplvyKoWiC2//J8NXUds 1SyNv5+vsXlwhtPqJsNZkQIgEEIy0hecVr6ZcARTF3DybRgIuLsyT/G+MAa4 MV6D7GECICocr6+G3vofvwWGjvEes3JpYiZ/Rcab90uzC0W5pHxhAiAvPq16 AvszXkXVZXgvEHoyG2qvbj0I7VkCsHI8C0E1xQ==";// "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJmjN8e/LPst kgCjsE9fYcQMdzgJlH1QjA8L+iZ9onrZ/yXadr5hEpAcETbbT5FB+q6lUwqb LtBij6ECOzgyoKAJ52rJYHAeGuFNtCnkW5n/FqoUK7P+e3JjPPs2tigPeKO3 AFdSvHqRMl/G2QH82F3fXske7PE9H1s1SPcrmkt/AgMBAAECf0sEZzghINWE asXlJzGaYSJY891o0BhgPAMc1gf1UGCsEOlqwpAy1d2H1t/yNee3T6/3CZUk MLePaJI1hLTsA4FcGzEWKUy7Hn2CqIjUzkJdax6CYXlK0hcoI47fiCr7Wtcx U/6P1uN+shA5ICHF0nZYJ2vEO1Vw/xMVGnLIRykCQQDwHJ0yOJZde7nZxo56 xka2C1tfMNjziXkdFH6EGfaiT0iJkJ+Kd11m0LukUxLPo5U1O63ZYkWOtFen wL2FmJTzAkEAo83FAO23KDZAyaPJg4K1PWqScF8+BDK51QxmJR0iCcl2umck jTfnO53wE8zjGNH/nqKQK6D+RUs8vEWaI37CRQJBAKKEW4mQb4XappJWKD3F UjsJONEXOOCtncInCvLSt/JoA0rJDpMj854Rjc/NQqAzslwThrnqH/ZU7jdm 52AzRC0CQB62djm2WKExivRDwYTm/RSG5u4q7XXcDPvlV0GeNMOhAqHwtOnF kZWcB2evAuWkeklEMcP8a7CSatDiPARrwAECQQC0SMsT/xukGzOh4SkHEirN bdFwdLdFeJf+8O3Tsf7iYlTNhnGEgTLQeaf3xOoivGtJEXZwT7vJWtiTUXRW 8P1v";  
        try {  
            // Map key = genKeyPair();  
            // publicKey = getPublicKey(key);  
            // privateKey = getPrivateKey(key);  
            System.out.println("公钥-->" + publicKey);  
            System.out.println("私钥-->" + privateKey);  
            System.out.println("待加密的字符串：" + json);  
            byte[] enBy = encryptByPublicKey(json.toJSONString().getBytes(), publicKey);  
            String enStr = new String(enBy, "UTF-8");  
            System.out.println("加密后：" + enStr);  
            String enEncode = Base64Util.encode(enBy);  
            System.out.println("加密后编码内容：" + enEncode);  
            byte[] deBy = decryptByPrivateKey(Base64Util.decode(enEncode), privateKey);  
            System.out.println("解密后：" + new String(deBy));  
            System.out.println("解密后编码内容：" + Base64Util.encode(deBy));  
            String sign = sign(enBy,privateKey);  
            System.out.println("签名："+sign);  
            System.out.println("对签名验证："+verify(enBy, publicKey, sign));  
            System.out.println("BASE64编码密文摘要："+MD5Util.getMD5(enEncode));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }
}

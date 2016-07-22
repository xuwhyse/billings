/**
 * @Title TokenUtils.java
 * @Description 请填写描述
 * @author shangke
 * @date 2014年1月3日
 * @version V1.0
 */
package com.billings.billingsystem.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Hex;


/** .
 * @author: shangke
 * @since: 2014年1月3日 下午1:24:05
 * @history:
 */
public final class TokenUtils {

    /**
     * 这个是从配置文件中拿到的登陆后时效时间，单位分钟
     */
    public static int timeLimit = 12;//Integer.parseInt(GobalConfig .getValue(Constant.GOBAL_SECURITY_TIMELIMIT));

    /**.
     * 私有构造函数
     */
    private TokenUtils() {

    }

    /**
     * @Fields MAGIC_KEY : (用一句话描述这个变量表示什么)
     */
    public static final String MAGIC_KEY = "obfuscate";

    /**
     * 生成账户名:时间戳:校验公钥 
     * @param userDetails UserDetails
     * @param expires long 时间戳，下次自动登录就是持续一个星期
     * @param identification 增加ip校验等
     * @return String
     * @create: 2014年1月3日 下午1:25:34 Administrator
     * @history:
     */
    public static String createToken(UserDetails userDetails, long expires,
            String identification) {
        StringBuilder tokenBuilder = new StringBuilder();
        tokenBuilder.append(userDetails.getUsername());
        tokenBuilder.append(":");
        tokenBuilder.append(expires);
        tokenBuilder.append(":");
        tokenBuilder.append(TokenUtils.computeSignature(userDetails, expires,
            identification));
        return tokenBuilder.toString();
    }

    /**自定义创建token.
     * @param userDetails UserDetails
     * @param maxAge 《=0就是15分钟
     * @param identification 标识，这里很可能是ip
     * @return String
     * @create: 2014年1月9日 下午1:26:09 Administrator
     * @history:
     */
    public static String createMyToken(UserDetails userDetails, int maxAge,
            String identification) {
        /* Expires in one hour */
        long expires = -1;
        if (maxAge > 0)
            expires = System.currentTimeMillis() + 1000L * maxAge;
        else
            expires = System.currentTimeMillis() + 1000L * timeLimit * 60;// 15分钟
        return createToken(userDetails, expires, identification);
    }

    /**
     * 生成用户名+时间戳的校验公钥
     * @param userDetails UserDetails
     * @param expires long
     * @param identification 一般是ip标识
     * @return String
     * @create: 2014年1月9日 下午1:26:33 Administrator
     * @history:
     */
    public static String computeSignature(UserDetails userDetails,
            long expires, String identification) {
        StringBuilder signatureBuilder = new StringBuilder();
        signatureBuilder.append(userDetails.getUsername());
        signatureBuilder.append(":");
        signatureBuilder.append(expires);
        signatureBuilder.append(":");
        signatureBuilder.append(userDetails.getPassword());// userDetails.getPassword();identification
//        signatureBuilder.append(":");
//        signatureBuilder.append(userDetails.getPassword());
        signatureBuilder.append(":");
        signatureBuilder.append(TokenUtils.MAGIC_KEY);

        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No MD5 algorithm available!");
        }

        return new String(Hex.encode(digest.digest(signatureBuilder.toString()
            .getBytes())));
    }

    /**用一句话描述这个方法的作用.
     * @param authToken String
     * @return String
     * @create: 2014年1月3日 下午1:27:02 Administrator
     * @history:
     */
    public static String getUsernameFromToken(String authToken) {
        if (null == authToken) {
            return null;
        }
        String[] parts = authToken.split(":");
        return parts[0];
    }

    /**
     * 用一句话描述这个方法的作用.
     * @param authToken
     * @return
     * @create: 2014-7-28 上午10:19:26 xunmin
     * @history:
     */
    public static long getLimitTime(String authToken) {
        if (null == authToken) {
            return 0;
        }
        String[] parts = authToken.split(":");
        if (parts.length < 2)
            return 0;
        return Long.parseLong(parts[1]);
    }

    /**
     * 获取用户加密过的token,验证是否过期和合法性
     * @param authToken String
     * @param userDetails  UserDetails类型
     * @return boolean
     * @create: 2014年1月3日 下午1:27:36 Administrator
     * @history:
     */
    public static boolean validateToken(String authToken,
            UserDetails userDetails, String identfy) {
        String[] parts = authToken.split(":");
        long expires = Long.parseLong(parts[1]);
        String signature = parts[2];
        // 过期
        if (expires > 0 && expires < System.currentTimeMillis())
            return false;

        // 校验是否有没有被篡改
        return signature.equals(TokenUtils.computeSignature(userDetails,
            expires, identfy));
    }
}

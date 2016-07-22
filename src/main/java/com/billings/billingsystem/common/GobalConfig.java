package com.billings.billingsystem.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 读取ihms2全局配置，比如是否打开校验等.
 * @author: xunmin
 * @since: 2014-7-9 下午4:59:10
 * @history:
 */
public class GobalConfig {
    /**
     * Properties 对象
     */
    private static Properties prop;

    /**
     * @Fields logger : (输出日志)
     */
    private static Logger logger = Logger.getLogger(GobalConfig.class);

    /**
     * 获取全局配置文件中对应的KEY值.
     * @param key 键
     * @return value 值
     * @create: 2014年4月21日 下午3:56:38 shangke
     * @history:
     */
    public static String getValue(String key) {
        String retValue = "";

        if (prop == null) {
            try {
                // InputStream in = new BufferedInputStream(new FileInputStream(
                // ihoms2));
                InputStream in = GobalConfig.class
                    .getResourceAsStream("/billing.properties");
                prop = new Properties();
                prop.load(in);
            } catch (IOException e) {
                logger.error("读取全局配置文件异常!", e);
            }
        }

        retValue = prop.getProperty(key, "");

        return retValue;
    }
}

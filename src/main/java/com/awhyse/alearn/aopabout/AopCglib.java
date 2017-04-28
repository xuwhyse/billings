package com.awhyse.alearn.aopabout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by whyse
 * on 2017/4/24 18:59
 */
public class AopCglib {
    static Logger logger = LoggerFactory.getLogger(AopCglib.class);
    public void a(){
        logger.info("a did");
    }

    /**
     * 该方法不能被继承，所以无法cglib
     */
    public final void b(){
        logger.info("b did");
    }
}

package com.awhyse.alearn.aopabout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by whyse
 * on 2017/4/24 18:40
 */
public class AopTestImpl implements AopTestI {
    static Logger logger = LoggerFactory.getLogger(AopTestImpl.class);

    public static void main(String[] args) {
        double aa = 0.0;
        if(aa==0){
            logger.info("dsffd");
        }
    }
    @Override
    public int a() {
        logger.info("a did");
        return 0;
    }

    @Override
    public int b() {
        logger.info("b did");
        return 0;
    }
}

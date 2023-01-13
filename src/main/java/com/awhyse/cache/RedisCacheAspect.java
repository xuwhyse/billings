//package com.awhyse.cache;
//
//import cn.gov.zcy.paas.finance.loan.api.internal.constants.RedisConstants;
//import cn.gov.zcy.paas.finance.loan.api.internal.exception.CustomErrorException;
//import cn.gov.zcy.paas.finance.loan.api.internal.msg.StateCode;
//import cn.gov.zcy.paas.finance.loan.api.internal.utils.BeanUtil;
//import cn.gov.zcy.spring.boot.redis.ms.JedisClientUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang.StringUtils;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.nustaq.serialization.FSTConfiguration;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.text.MessageFormat;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * RedisCache的切面类
// *
// * @author xumin 和歌
// * @date 2019-07-17 15:36
// */
//@Service
//@Aspect
//@Slf4j
//public class RedisCacheAspect {
//
//    static String startSym = "{";
//    static String endSym = "}";
//    static String update = "1";
//    static String strName = "java.lang.String";
//
//    static String cacheLog = "RedisCache 触发缓存 key : {0}，缓存时间 : {1}";
//
//    /**
//     * 以.分割的
//     */
//    static String splSym = "\\.";
//
//    /**
//     * 使用fst序列化和反序列化
//     */
//    static FSTConfiguration configuration = FSTConfiguration.createDefaultConfiguration();
//
//    @Autowired
//    private JedisClientUtil jedisClientUtil;
//
//    /**
//     * 定义切入点，使用了 @RedisCache 的方法
//     */
//    @Pointcut("@annotation(cn.gov.zcy.paas.finance.loan.center.cache.RedisCache)")
//    public void point() {
//
//    }
//
//    /**
//     * 环绕通知，方法拦截器
//     * 如果缓存配置异常，将直接调用方法完成
//     */
//    @Around("point()")
//    public Object around(ProceedingJoinPoint point) {
//        try {
//            // 获取RedisCache注解
//            RedisCache redisCache = ((MethodSignature) point.getSignature()).getMethod().getAnnotation(RedisCache.class);
//            //获取方法返回类型
//            Class returnType = ((MethodSignature) point.getSignature()).getReturnType();
//            String returnName = returnType.getName();
//            if (redisCache == null || "void".equals(returnName)) {
//                return null;
//            }
//
//            // 参数名
//            String[] argNames = ((MethodSignature) point.getSignature()).getParameterNames();
//            // 参数值
//            Object[] args = point.getArgs();
//            Map<String, Object> nameVMap = new HashMap<>(args.length);
//            for (int i = 0; i < args.length; i++) {
//                nameVMap.put(argNames[i], args[i]);
//            }
//
//            //key值动态获取
//            String strKeyOld = redisCache.key();
//            String strKeyNew = getNewKey(strKeyOld, nameVMap);
//            strKeyNew = RedisConstants.PRE_KEY + strKeyNew;
////            System.err.println("strKeyNew:"+strKeyNew);
//
//            //update 值动态获取
//            String strUpdateOld = redisCache.update();
//            String strUpdateNew = getNewKey(strUpdateOld, nameVMap);
////            System.err.println("strUpdateNew:"+strUpdateNew);
//            //是否强制更新，默认不更新
//            boolean isUpdate = update.equals(strUpdateNew);
////            System.err.println("isUpdate:"+isUpdate);
//            if (strName.equals(returnName)) {
//                String obj = jedisClientUtil.get(strKeyNew);
//                if (StringUtils.isBlank(obj) || isUpdate) {
//                    obj = (String) point.proceed();
//                    if (StringUtils.isBlank(obj)) {
//                        jedisClientUtil.del(strKeyNew);
//                        return null;
//                    }
////                    log.info(MessageFormat.format(cacheLog,strKeyNew,redisCache.expired()));
//                    jedisClientUtil.setNewnx(strKeyNew, obj, redisCache.expired());
//                }
//
//                return obj;
//            }
//        } catch (Throwable ex) {
//            log.warn("<====== RedisCache 执行异常，放弃缓存: {} ======>", ex);
//            try {
//                Object rst = point.proceed();
//                return rst;
//            } catch (Throwable throwable) {
//                log.warn("error", throwable);
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 解析用户约定输入,返回带参数的新key
//     *
//     * @param strKeyOld
//     * @param nameVMap
//     * @return
//     * @throws Exception
//     */
//    private String getNewKey(String strKeyOld, Map nameVMap) {
//        String paraName = getParaName(strKeyOld);
//        //如果key中没有参数变量，就直接返回key
//        if (paraName == null) {
//            return strKeyOld;
//        }
//        String[] strs = paraName.split(splSym);
//        String val = null;
//        //一级数据直接获取
//        if (strs.length == 1) {
//            val = nameVMap.get(paraName).toString();
//            return getNewKey(strKeyOld, paraName, val);
//        }
//
//        //获取对象
//        Object obj = nameVMap.get(strs[0]);
//        for (int i = 1; i < strs.length; i++) {
//            String fileName = strs[i];
//            obj = BeanUtil.getFieldValueByFieldName(fileName, obj);
//            if (obj == null) {
//                StateCode stateCode = new StateCode("-1", paraName + "属性值为null");
//                throw new CustomErrorException(stateCode);
//            }
//            if (i == strs.length - 1) {
//                //最后一级，获取数据后返回
//                val = obj.toString();
//            }
//        }
//        return getNewKey(strKeyOld, paraName, val);
//    }
//
//
//    /**
//     * @param key 原生的key
//     * @return
//     */
//    private static String getParaName(String key) {
//        if (StringUtils.isBlank(key) || !key.contains(startSym) || !key.contains(endSym)) {
//            return null;
//        }
//        int i1 = key.indexOf(startSym);
//        int i2 = key.indexOf(endSym);
//        return key.substring(i1 + 1, i2);
//    }
//
//    /**
//     * @param key      原生的key
//     * @param paraName 从原生key中截取的变量名
//     * @param val      变量名对应的值
//     * @return
//     */
//    private static String getNewKey(String key, String paraName, String val) {
//        String tempStr = key.replace(paraName, "0");
//        return MessageFormat.format(tempStr, val);
//    }
//
//
//}

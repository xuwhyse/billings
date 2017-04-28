package com.awhyse.concurrent.ScheduleAbout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by whyse
 * on 2017/4/6 11:21
 */
public class ScheduledExecutorService {
    static Logger logger = LoggerFactory.getLogger(ScheduledExecutorService.class);
    public static void main(String[] args) {
        ScheduledExecutorServiceTest();
    }

    /**
     * 不管前一个任务执行完成没有，时间到了就执行。
     * 重点!如果执行过程中产生错误，就中断任务
     */
    private static void ScheduledExecutorServiceTest() {
        int count =3;
        Runnable runnable = new Runnable() {

            int index = 0;
            ConcurrentHashMap<String,Object> map =  new ConcurrentHashMap<>(3);
            public void run() {
//                try {
//
//                }catch (Exception e){
//                    logger.info(e.getMessage());
//                }

                logger.info("++++");
                if(index>count){
                    exception(map);
                }
                index++;


            }
        };
        java.util.concurrent.ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        service.scheduleAtFixedRate(runnable, 10, 1000, TimeUnit.MILLISECONDS);
    }

    private static void exception(ConcurrentHashMap<String, Object> map) {
        map.put(null,"sfd");
    }
}

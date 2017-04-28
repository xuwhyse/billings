package com.awhyse.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 实现一个支持定时回收超时的内存数据的map
 * Created by whyse
 * on 2017/2/23 20:02
 */
public class CacheMapXM {
    public ConcurrentHashMap<String, XmItem> mapMatchInfo = null;
    static Logger logger = LoggerFactory.getLogger(CacheMapXM.class);
    int revoverTime = 60;//回收间隔时间
    public String name ;
    int size = 500;//初始大小，mapMatchInfo如果改变，就会发生变化
    private AtomicInteger countOb = new AtomicInteger(0);
    CahceXMListen cahceXMListen;
    /**
     * @param size  可存放对象大小
     * @param revoverTime 回收间隔时间秒
     */
    public CacheMapXM(int size, int revoverTime) {
        this.size = size;
        this.revoverTime = revoverTime;
        init();
    }
    public CacheMapXM(){
        init();
    }
    /**
     * 多线程任务回收
     * 出现回收报错，不会影响其他
     */
    private synchronized void init() {
        if(mapMatchInfo!=null){
            return;
        }
        mapMatchInfo = new ConcurrentHashMap<>(size);
        int index = countOb.incrementAndGet();
        name = "CacheMapXM_"+index;
        logger.info(name+" has init totalSizeInit: " +size);
        //回收任务
        Runnable runnable = new Runnable() {

            public void run() {
                long time = System.currentTimeMillis();
                logger.info(name+" start schedule. size: " +mapMatchInfo.size());
                mapMatchInfo.forEachEntry(1,item->{
                    //这个方法用到forkJoinPool，没执行完任务之前是阻塞的。第二个任务必须等前面一个执行完
                    try {
                        XmItem xmItem = item.getValue();
                        if (xmItem.endTime <= time) {
                            mapMatchInfo.remove(xmItem.key);
                            if (cahceXMListen != null) {
                                cahceXMListen.timeOverEvent(xmItem.item, xmItem.key);
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                });
                logger.info(name+" end schedule. newSize:" +mapMatchInfo.size()+"  time:"+(System.currentTimeMillis()-time));
            }
        };
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        service.scheduleAtFixedRate(runnable, 10, revoverTime, TimeUnit.SECONDS);
    }
    //==========================================================================================

    /**
     * 实现这个接口，可以在回收的时候做些什么，如果里面使用了线程池，那么
     * 回收时间将瞬间结束
     * @param cahceXMListen
     */
    public void addListener(CahceXMListen cahceXMListen){
        this.cahceXMListen = cahceXMListen;
    }
    //==========================================================================================
    /**
     * 如果有相同key，覆盖后，时间也同时计算
     * @param key
     * @param item 存放的对象
     * @param remainTime 停留的秒数
     */
    public void put(String key, Object item, int remainTime) {
        XmItem itemTemp = mapMatchInfo.get(key);
        if(itemTemp!=null){
            itemTemp.endTime = remainTime*1000+System.currentTimeMillis();
            itemTemp.item = item;
            return ;
        }
        XmItem xmItem = new XmItem();
        xmItem.endTime = remainTime*1000+System.currentTimeMillis();
        xmItem.item = item;
        xmItem.key = key;
        mapMatchInfo.put(key,xmItem);
    }
    public Object get(String key) {
        XmItem xmItem = mapMatchInfo.get(key);
        if(xmItem!=null){
            return xmItem.item;
        }
        return null;
    }
    public void remove(String key) {
        mapMatchInfo.remove(key);
    }
}

//=====================================================================================
class XmItem{
    String key;
    volatile Object item;
    volatile long endTime;
}

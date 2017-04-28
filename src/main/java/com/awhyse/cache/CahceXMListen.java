package com.awhyse.cache;

/**
 * CacheMapXM的回调接口相关
 * Created by whyse
 * on 2017/2/28 11:01
 */
public interface CahceXMListen {
    /**
     * 超时后，移除对象后，会把超时对象返回
     * @param item 超时对象
     */
    void timeOverEvent(Object item, String key);
}

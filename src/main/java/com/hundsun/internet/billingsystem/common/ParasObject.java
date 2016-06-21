package com.hundsun.internet.billingsystem.common;

import java.util.HashMap;

public class ParasObject {
    /**
     * @Fields paras list页面自动保存的参数
     */
    // private String paras = "";

    /**
     * @Fields parasCus list页面中自定义并自动保存的参数
     */
    // private String parasCus = "";

    /**
     * @Fields parasHT
     */
    private HashMap<String, String> parasHT = new HashMap<String, String>();

    /**
     * @Fields parasCusHT
     */
    private HashMap<String, String> parasCusHT = new HashMap<String, String>();

    /**
     * @Fields uri 缓存数据的URI
     */
    private String uri = "";

    public HashMap<String, String> getParasHT() {
        return parasHT;
    }

    public HashMap<String, String> getParasCusHT() {
        return parasCusHT;
    }

    public void addPara(String key, String value) {
        this.parasHT.put(key, value);
    }

    public void addParaCus(String key, String value) {
        this.parasCusHT.put(key, value);
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getParas() {
        return this.getParas(false);
    }

    public String getParasUrl() {
        return this.getParas();
    }

    private String getParas(boolean isUrl) {
        return MapUtil.mapToString(this.parasHT);
    }

    public void setParas(String paras) {
        setHT(paras, this.parasHT);
    }

    public void setParasCus(String parasCus) {
        setHT(parasCus, this.parasCusHT);
    }

    private void setHT(String paras, HashMap ht) {
        ht.putAll(MapUtil.stringToMap(paras));
    }

    public String getParasCus() {
        return getParasCus(false);
    }

    public String getParasCusUrl() {
        return getParasCus(true);
    }

    public String getParasCus(boolean isUrl) {
        return MapUtil.mapToString(this.parasCusHT);
    }

    /**
     * 对URL中的特殊字符进行处理.
     * @param str 需要处理的URL字符串
     * @return
     * @create: 2014年1月24日 下午1:53:46 shangke
     * @history:
     */
    private String URLencode(String str) {
        // TODO 还缺%逃逸
        return str.replaceAll("%", "%25").replaceAll("\\+", "%2B")
            .replaceAll(" ", "%20").replaceAll("/", "%2F")
            .replaceAll("\\?", "%3F").replaceAll("#", "%23")
            .replaceAll("&", "%26").replaceAll("=", "%3D");
    }
}

package com.awhyse.util;

import com.awhyse.concurrent.dbaction.DruidJdbcTempDriver;
import com.awhyse.concurrent.socket.NetUtil;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by whyse
 * on 2018/5/23 下午8:17
 */
public class HTMLParse {
    static JdbcTemplate jdbcTemplate =null;
    public static void main(String[] args) {
        initJdbcTemplate();
        htmlDo();
    }

    private static void htmlDo() {
        String url = "http://gaokao.chsi.com.cn/sch/search.do";
        //        String para = "searchType=1&start=2680";
        int times = 136;//135
        Set<String> set = new HashSet<>(4000);
        for(int i=0;i<times;i++){
            try {
                String para = "searchType=1&start=" + i * 20;
                String htmlStr = NetUtil.sendGet(url, para, null);
//        System.err.print(htmlStr);
                Document doc = Jsoup.parse(htmlStr);
                Elements aa = doc.getElementsByClass("js-yxk-yxmc");

                List<String> listSchool = new ArrayList<>(20);
                for (Element link : aa) {
                    String str = link.text();
                    if (StringUtils.isNotEmpty(str)) {
                        listSchool.add(str);
                        set.add(str);
//                    System.err.println(str);
                    }
                }
                if (!listSchool.isEmpty()) {
//                    insertIntoTB(listSchool);//插入
                }
            }catch (Exception e){
                System.err.println("i:"+i);
                e.printStackTrace();
            }
        }
        System.err.println(set.size());
    }

    public static void initJdbcTemplate() {
        String dburl = "jdbc:mysql://127.0.0.1/test100?useUnicode=true&characterEncoding=utf8";
        String user = "root";
        String pwd = "xumin123";
        jdbcTemplate = DruidJdbcTempDriver.getJdbcTemp(dburl,user,pwd);
    }
    //insert into MusicAct_MsgBoard_School(name) values ('北京大学'),('中国人民大学');
    private static void insertIntoTB(List<String> listSchool) {
        StringBuffer sb = new StringBuffer("insert into MusicAct_MsgBoard_School(name) values ");
        for(String item : listSchool){
            sb.append("('"+item+"'),");
        }
        String temp = sb.substring(0,sb.length()-1);
//        System.err.println(temp);
        jdbcTemplate.execute(temp);
    }

    //insert into MusicAct_MsgBoard_School(name) values ('北京大学',1),('中国人民大学',1);
    public static void insertIntoTBFor1(List<String> listSchool) {
        StringBuffer sb = new StringBuffer("insert into MusicAct_MsgBoard_School(name,type) values ");
        for(String item : listSchool){
            sb.append("('"+item+"',1),");
        }
        String temp = sb.substring(0,sb.length()-1);
//        System.err.println(temp);
        jdbcTemplate.execute(temp);
    }
}

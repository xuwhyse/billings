package com.awhyse.newskill;

import com.awhyse.util.HttpUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * https://open-doc.dingtalk.com/docs/doc.htm?spm=a219a.7629140.0.0.karFPe&treeId=257&articleId=105735&docType=1#s5
 * @author xumin
 * @description 将告警通过钉钉机器人发到指定群
 * @date 2018/10/16 下午3:49
 */
public class DingAlarmTest {
    static String webhook = "https://oapi.dingtalk.com/robot/send?access_token=bc831dc251fbf7b6103b1a63f2ef47220934a7ed1ff18c067f2b1101cc2fd5b0";

    public static void main(String[] args) {
        sendAlarm();
    }

    private static void sendAlarm() {
        String url = webhook;
        Map<String, Object> mapPara = new LinkedHashMap<>(3);
        mapPara.put("msgtype","link");

        Map<String, Object> link = new LinkedHashMap<String, Object>(5);
        mapPara.put("link",link);
        //消息内容。如果太长只会部分展示
        link.put("text","我是内容");
        //消息标题
        link.put("title","我是标题");
        //点击消息跳转的URL
        link.put("messageUrl","http://172.16.103.134:8080/#/trace");
        //图片URL  可以不填
        link.put("picUrl","");

        String jsonRsp = HttpUtils.postJson(url,null,mapPara);
        System.err.print(jsonRsp);
    }
}

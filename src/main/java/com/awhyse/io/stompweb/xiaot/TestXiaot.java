package com.awhyse.io.stompweb.xiaot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
/queue/game/doReady
请求 ：  {"userId":"g-HXe3eQ7Cw3TJW3rFbKIL","userType":3,"gameId":"dc36efe2-e8fd-45f6-8d6e-cfceb2ba5fa8",
"headimgurl":"//img.investmaster.cn/998af7f38430482bb74fdc0f0a8171fe.png","userName":"setr"}

/user/queue/game/doReady
返回  ： {"rspCode":200,"data":{gameName:"AAA比赛"}}   其他就是准备失败

 */
public class TestXiaot {
    private static Logger logger = LoggerFactory.getLogger(TestXiaot.class);
    public static ExecutorService executorService = Executors.newFixedThreadPool(200);
    static String dev = "devxiaot.forexmaster.cn/xiaots";
    static String local = "localhost:12306/xiaots";
    static String test = "testxiaot.forexmaster.cn/xiaots";
    static String onLine = "xiaot.forexmaster.cn/xiaots";

    //----------比赛id和环境这边配置即可--------------------
    static String currentEnv = local;
    static String gameId = "0e08e9c3-f9b6-4fe5-a32c-7bce31d8b831";
    static int clientSize = 1;
    //--------------------------------------------------
    static List<ClientXiaot>  listXiaot = new ArrayList<>(500);

    public static void main(String[] args) {
        String userId = "userId_";
        String headimgurl = "//img.investmaster.cn/998af7f38430482bb74fdc0f0a8171fe.png";
        int userType = 3;
        executorService.submit(()->{
            for(int i=0;i<clientSize;i++) {
                String userIdT = userId + i;
                String userName = userIdT;
                newClient(gameId, userIdT, userType, headimgurl, userName);
            }
        });
    }

    private static void newClient(String gameId, String userId, int userType, String headimgurl, String userName) {

        ClientXiaot clientXiaot = new ClientXiaot(gameId,userId,userType,headimgurl,userName);
        listXiaot.add(clientXiaot);
        clientXiaot.connect(currentEnv);

//        clientXiaot.subReadyInfoTest();
//        clientXiaot.subListInfoLimit();
        clientXiaot.subListInfoSimple();
//        clientXiaot.subListInfoTest();
        clientXiaot.subTimePointTest();
        clientXiaot.subGameEnd();
//        clientXiaot.subClientInfo();
        clientXiaot.subGameStart(false);
        clientXiaot.doReady();//必须要准备，准备后进入

    }
}

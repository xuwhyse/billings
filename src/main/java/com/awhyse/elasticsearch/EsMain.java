package com.awhyse.elasticsearch;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.util.*;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;


/**
 * Created by whyse
 * on 2017/9/15 10:38
 */
public class EsMain {
    static Logger logger = LoggerFactory.getLogger(EsMain.class);
    static String host = "192.168.4.151";
    static int port = 9301;
    static String esIndex = "refdata";//库
    static String esType = "refdata";//表
    static String clusterName = "dev_uic";//集群名
    static TransportClient client;
    static String[] esCloums = {"symbol","side","conversionRatio","exercisePrice"};
    //====================================================
    public static void main(String[] args) throws Exception {
        initClient();
//        index();//插入
//        get();
        testMultiGet();
//        testMatchAllQuery();
        Map<String,Object> map = map = getEsCbbcSC();//getEsCbbc(null);
//        map = getEsCbbcSC();
        System.err.println(map);
    }

    private static Map<String,Object> getEsCbbcSC() {
        Map<String,Object> mapTar = new HashMap<>(10000);
        SearchResponse scrollResp = client.prepareSearch(esIndex)
                .setTypes(esType)
                .setScroll(new TimeValue(60000))
                .setFetchSource(esCloums,null)
                .setSize(1000).get();
        //Scroll until no hits are returned
        do {
            for (SearchHit hit : scrollResp.getHits().getHits()) {
                //Handle the hit...
                Map<String, Object> map = hit.getSource();
                if (okHits(map)) {
                    String symbol = map.get("symbol").toString();
                    mapTar.put(symbol, map);
//                    System.err.println(map);
                }
            }
            scrollResp = client.prepareSearchScroll(scrollResp.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();
        } while(scrollResp.getHits().getHits().length != 0); // Zero hits mark the end of the scroll and the while loop.
        return mapTar;
    }

    public static Map<String,Object> getEsCbbc(List<String> listTar) {
//        if(listTar.isEmpty()){
//            logger.error("shoes数据为空");
//            return null;
//        }
        Map<String,Object> mapTar = null;
        //setSize 最大10000条 所以有问题
       SearchResponse response = client.prepareSearch(esIndex)
                .setTypes(esType)
                .setSize(10000)
                .setFetchSource(esCloums,null)
                .get();
        SearchHits seHits = response.getHits();
        if(seHits.totalHits>0) {
            mapTar = new HashMap<>((int)seHits.totalHits);
            for(int i=0;i<seHits.totalHits;i++){
                Map<String, Object> map = seHits.getAt(i).getSource();
                if (okHits(map)) {
                    String symbol = map.get("symbol").toString();
                    mapTar.put(symbol, map);
//                    System.err.println(map);
                }
            }
        }
        return mapTar;
    }
    /**
     * 判断本条es的数据是否符合订阅规则
     * @param map
     * @return
     */
    private static boolean okHits(Map<String, Object> map) {
        if (map.containsKey("symbol") && map.containsKey("conversionRatio") && map.containsKey("side")
                && map.containsKey("exercisePrice")) {
            int side = (int) map.get("side");
            if(side >0) {
                return true;
            }
        }
        logger.error("es hits errormsg :"+map.toString());
        return false;
    }

    private static void testMatchAllQuery() {
        MatchAllQueryBuilder qb = matchAllQuery();
    }

    private static void testMultiGet() {
        List<String> listSymbol = new ArrayList<>(5);
        listSymbol.add("13576.HK");
        listSymbol.add("69028.HK");
        listSymbol.add("12124.HK");
        listSymbol.add("69257.HK");

        MultiGetResponse multiGetResponse = client.prepareMultiGet().add(esIndex,esType,listSymbol)
                .get();

        for (MultiGetItemResponse itemResponse : multiGetResponse) {
            GetResponse response = itemResponse.getResponse();
            if (response.isExists()) {
                String sourceAsString = response.getSourceAsString();
                System.out.println(sourceAsString);
            }
        }
    }

    /**
     * 直接批量更新，注意有阻塞
     * quote.tag 必须是doc的目标数据
     * @param listQutes
     * @return
     */
    public int updateQuotes(List<String> listQutes) {
        logger.info("批量更新es  size:"+listQutes.size());
        if(listQutes.isEmpty()){
            return 0;
        }
        BulkRequestBuilder bulkRequest = client.prepareBulk();//创建一个批量处理客户端
        for(String item : listQutes) {
            Map<String,Object> map = null;//(Map<String, Object>) item.tag;
            bulkRequest.add(client.prepareUpdate(esIndex, esType,item).setDoc(map));
        }
        bulkRequest.get();//批量获取结果
        return 0;
    }

    /**
     *
     */
    private static void get() {
        GetResponse response = client.prepareGet(esIndex, esType, "13576.HK").setFetchSource(esCloums,null)
//                .setOperationThreaded(false)    // 线程安全
                .get();
        System.out.println(response.getSourceAsString());
    }
    private static void index() {
        Map<String,Object> infoMap = new HashMap<String, Object>();
        infoMap.put("name", "广告信息11");
        infoMap.put("title", "我的广告22");
        infoMap.put("createTime", new Date());
        infoMap.put("count", 1022);
        IndexResponse indexResponse = client.prepareIndex(esIndex, esType,"100").setSource(infoMap).execute().actionGet();
        System.out.println("id:"+indexResponse.getId());
    }

    private static void initClient() throws Exception {
        InetSocketTransportAddress address = new InetSocketTransportAddress(InetAddress.getByName(host),port);
        Settings settings = Settings.builder()
                .put("cluster.name",clusterName)
//                .put("client.transport.ignore_cluster_name", true) // 忽略集群名字验证, 打开后集群名字不对也能连接上
                .put("client.transport.sniff", true)   // 开启嗅探,自动发现集群
                .build();
        client = new PreBuiltTransportClient(settings).addTransportAddress(address);
    }
}

package com.awhyse.newskill;

import com.alibaba.fastjson.JSON;
import com.awhyse.util.HttpUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author xumin
 * @description
 * @date 2018/10/16 下午1:48
 */
public class GraphQLClient {

    static final String queryErrors = "\n  query BasicTraces($condition: TraceQueryCondition) {\n    " +
            "queryBasicTraces(condition: $condition) {\n      traces {\n        key: segmentId\n        " +
            "operationNames\n        duration\n        start\n        isError\n        " +
            "traceIds\n      }\n      total\n    }\n  }\n";

    public static void main(String[] args) {
//        getErrorTrace();
        String traceId = "5866.217.15396803397141179";
        getSpansByTraceId(traceId);
    }

    private static void getSpansByTraceId(String traceId) {
        String query = "query Spans($traceId: ID!) { queryTrace(traceId: $traceId) {    spans {      " +
                "traceId      segmentId      spanId      parentSpanId      refs {        " +
                "traceId        parentSegmentId        parentSpanId        type      }      " +
                "applicationCode      startTime      endTime      operationName      type      " +
                "peer      component      isError      layer      tags {        key        " +
                "value      }      logs {        time        data {          key          " +
                "value        }      }    }  }}";

        String url = "http://172.16.103.134:8080/api/spans";
        Map<String, Object> mapPara = new LinkedHashMap<>(3);
        mapPara.put("query",query);

        Map<String, Object> variables = new HashMap<String, Object>(1);
        mapPara.put("variables",variables);
        variables.put("traceId",traceId);

        String jsonRsp = HttpUtils.postJson(url,null,null);
        System.err.print(jsonRsp);
    }

    private static void getErrorTrace() {

        String url = "http://172.16.103.134:8080/api/trace";
        Map<String, Object> mapPara = new HashMap<String, Object>(8);
        mapPara.put("query",queryErrors);

        Map<String, Object> variables = new HashMap<String, Object>(1);
        mapPara.put("variables",variables);

        Map<String, Object> condition = new LinkedHashMap<>(4);
        variables.put("condition",condition);
        condition.put("traceState","ERROR");
        condition.put("queryOrder","BY_START_TIME");

        Map<String, Object> queryDuration = new HashMap<String, Object>(3);
        condition.put("queryDuration",queryDuration);
        queryDuration.put("start","2018-10-16 1421");
        queryDuration.put("end","2018-10-16 1436");
        queryDuration.put("step","MINUTE");

        Map<String, Object> paging = new HashMap<String, Object>(3);
        condition.put("paging",paging);
        paging.put("pageNum",1);
        paging.put("pageSize",20);
        paging.put("needTotal",true);


        Map<String,String> mapHeards = new HashMap<>(2);
        String jsonRsp = HttpUtils.postJson(url,mapHeards, JSON.toJSONString(paging));
        System.err.print(jsonRsp);
    }
}

package com.awhyse.concurrent.netty.http.server;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.StringUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by whyse
 * on 2017/12/7 15:06
 */
public class HttpServerHelper {
    private static final Pattern INSECURE_URI = Pattern.compile(".*[<>&\"].*");
    static String url = "/src/main/java/com/";
    static byte[] pngBytes;
    //========================================================================
    public static void init() {
        String userDir = System.getProperty("user.dir");
        File file = new File(userDir+"/src/main/resources/favicon.ico");
        try {
            FileInputStream is = new FileInputStream(file);
            pngBytes = new byte[is.available()];
            is.read(pngBytes); // 读数据
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
   HTTP/1.1 400 Bad Request
content-type: text/html;charset=UTF-8

Failure: 400 Bad Request
    */
    public static void sendError(ChannelHandlerContext ctx, HttpResponseStatus status,String myMsg) {
//        ByteBuf content;
        byte[] body;
        if(StringUtil.isNullOrEmpty(myMsg)){
            body = ("Failure: " + status.toString()).getBytes(CharsetUtil.UTF_8);
//            content = Unpooled.copiedBuffer("Failure: " + status.toString() + "\r\n", CharsetUtil.UTF_8);
        }else{
            body = myMsg.getBytes(CharsetUtil.UTF_8);
//            content = Unpooled.copiedBuffer(myMsg + "\r\n", CharsetUtil.UTF_8);
        }
        //HttpVersion version, HttpResponseStatus status, ByteBuf content
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status);//请求行
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html;charset=UTF-8");//请求头

        response.content().writeBytes(body);//请求体

        ctx.writeAndFlush(response).
                addListener(ChannelFutureListener.CLOSE);
    }
    /**
     * 重定向到新的url
     * @param ctx
     * @param newUri
     */
    public static void sendRedirect(ChannelHandlerContext ctx, String newUri){
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.FOUND);
//        response.headers().set("LOCATIN", newUri);
        response.headers().set(HttpHeaderNames.LOCATION, newUri);
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    public static String sanitizeUri(String uri) {
        try{
            uri = URLDecoder.decode(uri, "UTF-8");
        }catch(UnsupportedEncodingException e){
            try{
                uri = URLDecoder.decode(uri, "ISO-8859-1");
            }catch(UnsupportedEncodingException e1){
                throw new Error();
            }
        }
        if(!uri.startsWith(url))
            return null;
//        if(!uri.startsWith("/"))
//            return null;
        uri = uri.replace('/', File.separatorChar);
        if(uri.contains(File.separator + '.') || uri.contains('.' + File.separator) || uri.startsWith(".") || uri.endsWith(".")
                || INSECURE_URI.matcher(uri).matches()){
            return null;
        }
        return System.getProperty("user.dir") + File.separator + uri;
    }

    /**
     * 获取request里面的参数,post方法请求的，必须是json返回
     * @param request
     * @return
     */
    public static Map<String,Object> getMapByRequest(FullHttpRequest request) {
        HttpMethod method = request.method();
        Map<String, Object> parmMap = new HashMap<>();
        if (HttpMethod.GET == method) {
            // 是GET请求
            QueryStringDecoder decoder = new QueryStringDecoder(request.uri());
            decoder.parameters().entrySet().forEach( entry -> {
                // entry.getValue()是一个List, 只取第一个元素
                parmMap.put(entry.getKey(), entry.getValue().get(0));
            });
        } else if (HttpMethod.POST == method) {
            // 是POST请求
            String str = new String(ByteBufUtil.getBytes(request.content()));
            return JSON.parseObject(str);
        } else {
            // 不支持其它方法
//            throw new Exception(""); // 这是个自定义的异常, 可删掉这一行
        }

        return parmMap;
    }

    public static void sendWebIcon(ChannelHandlerContext ctx, FullHttpRequest request) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);//请求行
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "image/png;charset=UTF-8");//请求头
        response.content().writeBytes(pngBytes);//请求体
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

}

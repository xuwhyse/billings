package com.awhyse.concurrent.netty.http.server;

import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.stream.ChunkedFile;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.regex.Pattern;

/**
 * Created by whyse
 * on 2017/12/7 13:42
 */
public class HttpHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    private static final Pattern ALLOWED_FILE_NAME = Pattern.compile("[A-Za-z0-9][-_A-Za-z0-9\\.]*");
    //========================================================================
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        if(!request.decoderResult().isSuccess()){
            HttpServerHelper.sendError(ctx, HttpResponseStatus.BAD_REQUEST,null);
            return;
        }
        String uri = request.uri();
//        Map<String,Object> mapPara = HttpServerHelper.getMapByRequest(request);
        System.err.println(uri);
        if(uri.equals("/favicon.ico")){
            HttpServerHelper.sendWebIcon(ctx, request);
            return;
        }
//        HttpServerHelper.sendError(ctx, HttpResponseStatus.BAD_REQUEST,"哈哈错了");
        final String path = HttpServerHelper.sanitizeUri(uri);
        if(path == null) {
            HttpServerHelper.sendError(ctx, HttpResponseStatus.FORBIDDEN,"没有权限访问");
            return;
        }

        File file = new File(path);
        if(file.isHidden() || !file.exists()) {
            HttpServerHelper.sendError(ctx, HttpResponseStatus.NOT_FOUND,"不存在或者已经隐藏");
            return;
        }
        if(file.isDirectory()) {
            //如果是目录的话
            if(uri.endsWith("/")) {
                sendListing(ctx, file);
            }else{
                HttpServerHelper.sendRedirect(ctx, uri + "/");
            }
            return;
        }
        if(!file.isFile()) {
            HttpServerHelper.sendError(ctx, HttpResponseStatus.FORBIDDEN,null);
            return;
        }
        //-------------------------
        RandomAccessFile randomAccessFile = null;
        try{
            randomAccessFile = new RandomAccessFile(file, "r");
        }catch(FileNotFoundException fnfd){
            HttpServerHelper.sendError(ctx, HttpResponseStatus.NOT_FOUND,null);
            return;
        }

        long fileLength = randomAccessFile.length();
        HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        HttpUtil.setContentLength(response,fileLength);//请求头
//        setContentLength(response, fileLength);
        setContentTypeHeader(response, file);

        if(HttpUtil.isKeepAlive(request)){
            response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        }

        ctx.write(response);//flush之前都还没发
        ChannelFuture sendFileFuture = null;
        //这边发送也没flush,分块发送
        sendFileFuture = ctx.write(new ChunkedFile(randomAccessFile, 0, fileLength, 8192), ctx.newProgressivePromise());
        sendFileFuture.addListener(new ChannelProgressiveFutureListener() {
            @Override
            public void operationProgressed(ChannelProgressiveFuture future, long progress, long total) throws Exception {
                if(total < 0)
                    System.err.println("Transfer progress: " + progress);
                else
                    System.err.println("Transfer progress: " + progress + "/" + total);
            }

            @Override
            public void operationComplete(ChannelProgressiveFuture future) throws Exception {
                System.out.println("Transfer complete.");
            }
        });
        //----------------------------------
        //这个是执行发送,没有断开连接
        ChannelFuture lastContentFuture = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
        if(!HttpUtil.isKeepAlive(request)) {
            //如果客户端要断开才断开
            lastContentFuture.addListener(ChannelFutureListener.CLOSE);
        }
    }

    private void setContentTypeHeader(HttpResponse response, File file) {
        MimetypesFileTypeMap mimetypesFileTypeMap = new MimetypesFileTypeMap();
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, mimetypesFileTypeMap.getContentType(file.getPath()));
    }

    private void sendListing(ChannelHandlerContext ctx, File dir) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
//        response.headers().set("CONNECT_TYPE", "text/html;charset=UTF-8");
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html;charset=UTF-8");

        String dirPath = dir.getPath();
        StringBuilder buf = new StringBuilder();

        buf.append("<!DOCTYPE html>\r\n");
        buf.append("<html><head><title>");
        buf.append(dirPath);
        buf.append("目录:");
        buf.append("</title></head><body>\r\n");

        buf.append("<h3>");
        buf.append(dirPath).append(" 目录：");
        buf.append("</h3>\r\n");
        buf.append("<ul>");
        buf.append("<li>链接：<a href=\" ../\">..</a></li>\r\n");
        for (File f : dir.listFiles()) {
            if(f.isHidden() || !f.canRead()) {
                continue;
            }
            String name = f.getName();
            if (!ALLOWED_FILE_NAME.matcher(name).matches()) {
                continue;
            }

            buf.append("<li>链接：<a href=\"");
            buf.append(name);
            if(f.isDirectory()) {
                buf.append("/\">");
            }else{
                buf.append("\">");
            }
            buf.append(name);
            buf.append("</a></li>\r\n");
        }

        buf.append("</ul></body></html>\r\n");

//        ByteBuf buffer = Unpooled.copiedBuffer(buf, CharsetUtil.UTF_8);
        response.content().writeBytes(buf.toString().getBytes());
//        buffer.release();
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }


}

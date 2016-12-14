package com.awhyse.concurrent.netty.server.traderls.netty;

import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;
import java.util.Map;


import com.alibaba.fastjson.JSON;
import com.awhyse.concurrent.netty.server.traderls.businessclient.ClientDispatch;
import com.awhyse.concurrent.netty.server.traderls.types.LoginClientType;
import com.awhyse.concurrent.netty.server.traderls.types.TraderMsgType;

public class TraderLSServerClientHelp {

	static final String serverId = "fdt_as_xm_0";
	static ChannelHandlerContext ctx = null;
//	static List<Object>  packageList = new ArrayList<Object>(6);
//	static String msg = null;
	//===========================================================
	/**
	 * @param args
	 * author:xumin 
	 * 2016-10-13 下午6:58:20
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static void channelRegistered(ChannelHandlerContext ctx) {
		TraderLSServerClientHelp.ctx = ctx;
		
		Map<String, Object> map = new HashMap<String, Object>(2);
    	map.put("123", "hello jsonServer");
    	writeAndFlush(map);
	}
	public static void channelUnregistered(ChannelHandlerContext ctx) {
		TraderLSServerClientHelp.ctx = null;
	}
	//========================================================================================
	/**
	 * 传输完成后直接获取里面json数据,包头已经过滤，加解密这些也可以考虑
	 * 这个方法拿到数据后可以异步
	 * @param ctx
	 * @param msg 
	 * @return
	 * author:xumin 
	 * 2016-10-13 下午8:39:05
	 */
	@SuppressWarnings("unchecked")
	public static String decodeAndDispatch(ChannelHandlerContext ctx, String msg) {
		//-----------------------
		//这边开始可以异步
		HashMap<String, Object>  map;
		try{
		  map = JSON.parseObject(msg, HashMap.class);
		}catch(Exception e){
			System.err.println("收到异常数据："+msg);
			return null;
		}
		System.out.println(map);
		int MsgType =  (Integer) map.get("MsgType");
		if(MsgType==TraderMsgType.HeartBeat){
			heartBeat(map,ctx);
		}else{
			ClientDispatch.decodeAndDispatch(ctx,map,MsgType);
		}
		return null;
	}
	private static void heartBeat(HashMap<String, Object> map,
			ChannelHandlerContext ctx) {
		map.put("UserID", serverId);
		map.put("ClientType", LoginClientType.None);
		map.put("Param", "十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话" +
				"十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话" +
				"十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话" +
				"十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话" +
				"十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话" +
				"十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话" +
				"十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话十多个烦得很的过分的话");
		writeAndFlush(map);
	}

	public static int getLengthFromHead(byte[] head) {
		int iOutcome = 0;
	    byte bLoop;
	    int length = 4;
	    for (int i = 0; i < length; i++) {
	        bLoop = head[i];
	        int temp = length-i-1;
	        iOutcome += (bLoop & 0xFF) << (8 * temp);
	    }
	    return iOutcome;
	}
	//===================================================================
	/**
	 * class CommandReq
    {
        public CommFunction MsgType;			//消息类型
        public string SessionID;				//连接id
        public string UserID;					//用户id
        public int Serial;						//序列号
        public LoginClientType ClientType;		//客户端类型
        public object Param;					//参数
    }
	 * @param map
	 * author:xumin 
	 * 2016-10-19 下午8:08:32
	 */
	public static void writeAndFlush(Map<String, Object> map) {
		ctx.writeAndFlush(JSON.toJSONString(map));
	}

}

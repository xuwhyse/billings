package com.awhyse.concurrent.netty.server.lengthbase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import io.netty.channel.ChannelHandlerContext;

public class NettyLengthDefServerHelp {

	public final static int connectFirstSize = 500;
	//这样能防止超长字符窜多次调用，乱码的问题
	static Map<String, Map<String, Object>> packageMap = new HashMap<String, Map<String, Object>>(
			connectFirstSize);
	public final static int headSize = 8;
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
		String id = ctx.channel().id().asShortText();// 这个id可以做key，区分不同连线
		Map<String, Object> map = new HashMap<String, Object>(2);
		map.put("length", 0);
		List<Object>  list = new ArrayList<Object>(5);
		map.put("list", list);
		packageMap.put(id, map);
//		ClientDispatch.channelRegistered(ctx);
		System.out.println("channelRegistered:" + id);// channelRegistered:a86f543a
	}
	public static void channelUnregistered(ChannelHandlerContext ctx) {
		String id = ctx.channel().id().asShortText();// 这个id可以做key，区分不同连线
		packageMap.remove(id);
//		ClientDispatch.channelUnregistered(ctx);
		System.out.println("channelUnregistered:" + id);// channelUnregistered:a86f543a
	}
	//========================================================================================
	/**
	 * 这个是防止大包拆分，粘合过程
	 * @param ctx
	 * @param msg
	 * author:xumin 
	 * 2016-10-13 下午8:40:54
	 */
	public static void channelRead(ChannelHandlerContext ctx, Object msg) {
		String id = ctx.channel().id().asShortText();// 这个id可以做key，区分不同连线
		Map<String, Object>  map = packageMap.get(id);
		int lengthR = (Integer) map.get("length");
		List<Object>  list = (List<Object>) map.get("list");
//		byte[] obj = msg.toString().getBytes();
		byte[] obj = (byte[]) msg;
		int length = lengthR+obj.length;
		map.put("length", length);
		list.add(obj);
		System.out.println("server channelRead:" + id + "  length:" + length);
		int hashCode = ctx.hashCode();
		System.out.println("id:"+id+"  code:"+hashCode);
		// System.out.println(msg);
	}
	/**
	 * 获取源生包，包含包头
	 * @param ctx
	 * @return
	 * author:xumin 
	 * 2016-10-13 下午8:41:49
	 */
	public static byte[] channelReadComplete(ChannelHandlerContext ctx) {
		String id = ctx.channel().id().asShortText();//这个id可以做key，区分不同连线
    	Map<String, Object>  map = packageMap.get(id);
    	int lengthR = (Integer) map.get("length");
		List<Object>  list = (List<Object>) map.get("list");
		byte[]  bytes = new byte[lengthR];
		int destPos = 0;
		for(Object item : list){
			byte[] tar = (byte[]) item;
			System.arraycopy(tar, 0, bytes, destPos, tar.length);
			destPos += tar.length;
		}
		System.out.println(new String(bytes));
		//----清理数据--------------
		map.put("length", 0);
		list.clear();
		//-----------------------
		return bytes;
	}
	/**
	 * 传输完成后直接获取里面json数据,包头已经过滤，加解密这些也可以考虑
	 * 这个方法拿到数据后可以异步
	 * @param ctx
	 * @return
	 * author:xumin 
	 * 2016-10-13 下午8:39:05
	 */
	public static String decodeAndDispatch(ChannelHandlerContext ctx) {
		String id = ctx.channel().id().asShortText();//这个id可以做key，区分不同连线
    	Map<String, Object>  map = packageMap.get(id);
    	int lengthR = (Integer) map.get("length");
    	if(lengthR==0){
    		System.err.println("!!!不应该调用");
    		return null;
    	}
		List<Object>  list = (List<Object>) map.get("list");
		byte[] head = new byte[headSize];
		byte[] body = new byte[lengthR-headSize];
		int destPos = 0;
		for(int i=0;i<list.size();i++){
			byte[] item = (byte[]) list.get(i);
			if(i==0){
				//第一个包里面有头
				System.arraycopy(item, 0, head, 0, headSize);
				System.arraycopy(item, headSize, body, destPos, item.length-headSize);
			}else{
				System.arraycopy(item, 0, body, destPos, item.length);
				System.err.println("---------------------有拆分包");
			}
			destPos += item.length;
		}
		//----清理数据--------------
		map.put("length", 0);
		list.clear();
		//-----------------------
		//这边开始可以异步
//		ClientDispatch.decodeAndDispatch(ctx,head,body);
		return null;
	}
	/**
	 * 将数据封装到自定义包里，返回字符窜数组
	 * @param msg
	 * @return
	 * author:xumin 
	 * 2016-10-13 下午7:10:18
	 */
	public static byte[] getMyEncodePack(String msg) {
		byte[] bodyB = msg.getBytes();
		int length = bodyB.length;
		//-------------------
		byte[] tar = new byte[length+8];
		byte[] lengthB = ByteAndInt.toByteArray(length, 4);
		System.arraycopy(lengthB, 0, tar, 0, 4);
		tar[4] = 0;//1个字节压缩类型（0为不压缩）
		tar[5] = 0;//1字节加密类型（0为不加密）
		tar[6] = 0;//1个字节序列化方法（0为json）
		tar[7] = 0;//1个字节保留
		System.arraycopy(bodyB, 0, tar, headSize, length);
		return tar;
	}
	

}

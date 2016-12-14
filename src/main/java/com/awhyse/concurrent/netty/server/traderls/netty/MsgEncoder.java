package com.awhyse.concurrent.netty.server.traderls.netty;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MsgEncoder extends MessageToByteEncoder<String>{

	/**
	 * @param args
	 * author:xumin 
	 * 2016-10-18 下午5:13:00
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void encode(ChannelHandlerContext arg0, String msg, ByteBuf out)
			throws Exception {
		byte[] tar = getMyEncodePack(msg);
		out.writeBytes(tar);
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
		System.arraycopy(bodyB, 0, tar, MsgDecoder.headSize, length);
		return tar;
	}

}

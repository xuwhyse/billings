package com.awhyse.concurrent.netty.decandenc.protocGoogle;


import com.awhyse.concurrent.netty.decandenc.lengthbaseUtil.ByteAndInt;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 协议思路：
 * 1.使用自定义协议格式头长8，0-3位长度，4-7其他位保留. 包体:8-11位是协议位，余下是protoBuf对象
 * 2.每个proto都有协议号字段，在ProtobufHandler的writeFlush阶段就要组装成,包体字符组
 * 本类只负责将包体加上自定义头，解决传输和编解包体的责任
 * @author whyse
 * @date 2017/1/25 10:31
 */
public class LengthBaseEncoderNew extends MessageToByteEncoder<ProtoBufTransfer> {

	private  int headSize = 8;
	static public final  int protoIdSize = 4;
	/**
	 * @param args
	 * author:xumin 
	 * 2016-10-18 下午5:13:00
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public LengthBaseEncoderNew() {
	}
	public LengthBaseEncoderNew(int headSize) {
		this.headSize = headSize;
	}

	@Override
	protected void encode(ChannelHandlerContext arg0, ProtoBufTransfer msg, ByteBuf out)
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
	public  byte[] getMyEncodePack(ProtoBufTransfer msg) {
		byte[] bodyB = msg.protoBytes;
		int lengthBody = bodyB.length;
		//-------------------
		byte[] tar = new byte[lengthBody+headSize+protoIdSize];
		//注意长度要加上协议号的长度
		byte[] headBytesLen = ByteAndInt.toByteArray(lengthBody+protoIdSize, 4);
		byte[] protoIdBytes = ByteAndInt.toByteArray(msg.protoId, protoIdSize);
		//前4位是长度
		System.arraycopy(headBytesLen, 0, tar, 0, 4);//前面四位写入包长
		tar[4] = 0;//1个字节压缩类型（0为不压缩）
		tar[5] = 0;//1字节加密类型（0为不加密）
		tar[6] = 0;//1个字节序列化方法（0为json）
		tar[7] = 0;//1个字节保留
		//8-11位是协议号
		System.arraycopy(protoIdBytes, 0, tar,headSize, protoIdSize);
		//12后是协议体长度
		System.arraycopy(bodyB, 0, tar,headSize+protoIdSize, lengthBody);
		return tar;
	}

}

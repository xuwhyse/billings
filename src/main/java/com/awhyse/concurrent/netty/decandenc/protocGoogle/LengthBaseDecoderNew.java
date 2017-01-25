package com.awhyse.concurrent.netty.decandenc.protocGoogle;

import com.awhyse.concurrent.netty.decandenc.lengthbaseUtil.ByteAndInt;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
/**
 * 只解析协议号，不序列化协议体有利于后续更多好处
 * @author whyse
 * @date 2017/1/25 11:10
 */
public class LengthBaseDecoderNew extends ByteToMessageDecoder {

	private  int headSize = 8;
	/**
	 * @param args
	 * author:xumin 
	 * 2016-10-18 下午5:14:21
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	public LengthBaseDecoderNew(){
	}
	public LengthBaseDecoderNew(int headSize){
		this.headSize = headSize;
	}
	/*
	根据和encoder协议： 本解码器将包体byte获取，并把头四位协议号去除，余下位数就是protoc协议体
	out里面将存放自定义结构ProtoBufTransfer
	 */
	@Override
	protected void decode(ChannelHandlerContext arg0, ByteBuf in,
                          List<Object> out) throws Exception {
		//这个是没有拆分版，而且一次性的
		decodeWithHead(in,out);
	}

	private void decodeWithHead(ByteBuf in, List<Object> out) {
		int length = in.readableBytes();
		if(length<headSize+LengthBaseEncoderNew.protoIdSize){
			return;
		}
		byte[] head = new byte[headSize];
		byte[] protoIdBytes = new byte[LengthBaseEncoderNew.protoIdSize];
		byte[] body = new byte[length-headSize-LengthBaseEncoderNew.protoIdSize];
		in.getBytes(0, head);
		in.getBytes(headSize, protoIdBytes);
		in.getBytes(headSize+LengthBaseEncoderNew.protoIdSize, body);
		in.clear();
//		in.readerIndex(length);
		//===================================
		ProtoBufTransfer paraReq = new ProtoBufTransfer();
		//将协议号先解析出来，并把协议体bytes暂时不解析。  后续有些逻辑，只要知道协议号就行
		//省去序列化协议体的问题
		paraReq.protoId = ByteAndInt.toInt(protoIdBytes,0,4);//读取包协议号
		paraReq.protoBytes = body;

		out.add(paraReq);
	}

}

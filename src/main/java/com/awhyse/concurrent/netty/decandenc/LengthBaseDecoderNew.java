package com.awhyse.concurrent.netty.decandenc;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.io.UnsupportedEncodingException;
import java.util.List;

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
	@Override
	protected void decode(ChannelHandlerContext arg0, ByteBuf in,
                          List<Object> out) throws Exception {
		//这个是没有拆分版，而且一次性的
		decodeWithHead(in,out);
	}

	private void decodeWithHead(ByteBuf in, List<Object> out) {
		int length = in.readableBytes();
		if(length<headSize){
			return;
		}
		byte[] head = new byte[headSize];
		byte[] body = new byte[length-headSize];
		in.getBytes(0, head);
		in.getBytes(headSize, body);
		in.clear();
//		in.readerIndex(length);
		String str = null;
		try {
			str = new String(body,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		out.add(str);
	}

}

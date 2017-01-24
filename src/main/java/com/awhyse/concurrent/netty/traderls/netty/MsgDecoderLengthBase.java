package com.awhyse.concurrent.netty.traderls.netty;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class MsgDecoderLengthBase extends ByteToMessageDecoder{

	public final static int headSize = 8;
	/**
	 * @param args
	 * author:xumin 
	 * 2016-10-18 下午5:14:21
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void decode(ChannelHandlerContext arg0, ByteBuf in,
			List<Object> out) throws Exception {
		//这个是没有拆分版，而且一次性的
		decodeWithHead(in,out);
	}

	private void decodeWithHead(ByteBuf in, List<Object> out) {
		int length = in.readableBytes();
		byte[] head = new byte[headSize];
		byte[] body = new byte[length-headSize];
		in.getBytes(0, head);
		in.getBytes(headSize, body);
		in.clear();
//		in.readerIndex(length);
		String str = new String(body);
		out.add(str);
	}

}

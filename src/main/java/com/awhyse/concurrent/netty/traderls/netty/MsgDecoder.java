package com.awhyse.concurrent.netty.traderls.netty;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class MsgDecoder extends ByteToMessageDecoder{

	public final static int headSize = 8;
	boolean isMsgWithHead = true; 
	byte[] body = null;
	int lengthPacket = 0;
	int lengthLeft = 0;
	byte[] halfPackTemp = null;
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
		int length = in.readableBytes();
		if(lengthLeft==0){
			byte[] req = new byte[length];
			in.readBytes(req);
			byte[] head = new byte[headSize];
			int lengthBodyCanRead = length-headSize;
			System.arraycopy(req, 0, head, 0, headSize);
			lengthPacket = TraderLSServerClientHelp.getLengthFromHead(head);
			body = new byte[lengthPacket];
			if(lengthPacket==lengthBodyCanRead){
				//最常见的一种，就是一个包就是一个包
				System.arraycopy(req, headSize, body, 0, lengthBodyCanRead);
				lengthLeft = 0;
				out.add(new String(body));
			}else if(lengthPacket > (length-headSize)){
				//夸包了
				System.arraycopy(req, headSize, body, 0, lengthBodyCanRead);
				lengthLeft = lengthPacket-lengthBodyCanRead;
			}else{
				System.err.println("超小包,里面有包A和包B的一部分,暂时没考虑");
				//超小包,里面有包A和包B的一部分
//				System.arraycopy(req, headSize, body, 0, lengthPacket);
//				out.add(new String(body));
//				lengthLeft = 0;
//				//---------------------------------
//				halfPackTemp = new byte[lengthBodyCanRead-lengthPacket];
//				System.arraycopy(req, headSize+lengthPacket, halfPackTemp, 0, lengthBodyCanRead-lengthPacket);
			}
		}
		else{
			byte[] req = new byte[length];
			in.readBytes(req);
			if(lengthLeft==length){
				//大包常见
				System.arraycopy(req, 0, body, (lengthPacket-lengthLeft), length);
				lengthLeft = 0;
				out.add(new String(body));
			}else if(lengthLeft >length){
				//超级大包，还不够
				System.arraycopy(req, 0, body, (lengthPacket-lengthLeft), length);
				lengthLeft -= length;
			}else{
				//一个大包的一半，加小包开头
				System.err.println("一个大包的一半，加小包开头,暂时没考虑");
			}
		}
		//--------------------
	}

}

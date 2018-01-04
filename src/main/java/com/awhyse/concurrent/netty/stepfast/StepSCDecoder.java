package com.awhyse.concurrent.netty.stepfast;

import com.awhyse.concurrent.netty.stepfast.common.StepCommon;
import com.awhyse.concurrent.netty.stepfast.openfast.FastCSDecoder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
TradingSessionID 336
全市场行情状态：该字段为8位字符串，左起每位表示特定的含义，无定义则填空格。
第1位：‘S’表示全市场启动期间（开市前），‘T’表示全市场处于交易期间（含中间休市）， ‘E’表示全市场处于闭市期间。
第2位：‘1’表示开盘集合竞价结束标志，未结束取‘0’。
第3位：‘1’表示市场行情闭市标志，未闭市取‘0’
 */
public class StepSCDecoder extends ByteToMessageDecoder{
	static Logger logger = LoggerFactory.getLogger(StepSCDecoder.class);
	static final String HEART_BEAT = "35=UA1202";
	static final String HEAD_FIXT = "96=8=FIXT";
	static final int startLength = 13;
	static final int tailLength = 7;
	static final int leastLength = startLength+tailLength+20;//这个可以解出长度
	static byte[] headByte = {56,61,83,84,69,80};//8=STEP
	static String testStr = "8=STEP.1.0.0#9=56#35=A#49=VSS#56=VDE#34=0#52=20101027-13:37:56#98=0#108=0#10=140#";
	//8=STEP.1.0.0<SOH> 以这个字符串开始,以“10=nnn<SOH>”字符串结束
//	static byte stx = 0x02;//start of text,ETX (end of text) 0x03
	//============================================================================================
	/**
	 * 8=STEP.1.0.0<SOH>9=56<SOH>35=A<SOH>49=VSS<SOH>56=VDE<SOH>34=0<SOH>52=
	 20101027-13:37:56<SOH>98=0<SOH>108=0<SOH>10=140<SOH>
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		decodeTest2();
		decodeTest3();
//		byte[] bytes = "=".getBytes();
//		System.err.println((byte) '=');
	}

	private static void decodeTest3()throws Exception {
		byte[] allBytes = Files.readAllBytes(Paths.get("e:/data.step"));//e:/data.step;);
		ByteBuf allBuf = Unpooled.buffer(allBytes.length);
		allBuf.writeBytes(allBytes);

		StepSCDecoder stepSCDecoder = new StepSCDecoder();
		List<Object> out = new ArrayList<>(100);
		stepSCDecoder.decode(null, allBuf, out);
	}

	private static void decodeTest()throws Exception {
		Map<Integer,String> map = StepCommon.getLogon();
		ByteBuf in1 = StepSCEncoder.getMyEncodePack(map);
		map.put(123,"asd");
		ByteBuf in2 = StepSCEncoder.getMyEncodePack(map);
		map.put(234,"aaa");
		ByteBuf in3 = StepSCEncoder.getMyEncodePack(map);
		ByteBuf in = Unpooled.buffer(300);
		in.writeByte(56);
		in.writeByte(56);
		in.writeBytes(in1);
		StepSCDecoder stepSCDecoder = new StepSCDecoder();
		//伪造数据，对拆包粘包解码器进行测试
		stepSCDecoder.decode(null,in,null);
	}

	@Override
	protected void decode(ChannelHandlerContext arg0, ByteBuf in,
			List<Object> out) throws Exception {
		//1.本次in是否包含头，如果包含则读取到头位置
		while(isOkPack(in)){
//			int bodyLength = getBodyLength(in);
			//8=STEP.1.0.0#9=56#35=A
			int readIndex = in.readerIndex();
			int start = readIndex+15;
			while(in.getByte(start++)!= StepCommon.soh);
			byte[] lengthField = new byte[start-16-readIndex];

			in.getBytes(15+readIndex,lengthField,0,lengthField.length);
			String len = new String(lengthField);
			int bodyLength = Integer.parseInt(len);

			int len89 = 15+len.length()+1;//8=STEP.1.0.0#9=  +长+分隔符
			int packLen = len89+bodyLength+7;//完整包的长度
			if(in.readableBytes()>=packLen){
				//1.将包读取出来
				ByteBuf bufTar = Unpooled.buffer(packLen);
				in.readBytes(bufTar);
				decodePack(bufTar,out,len89);
//				System.err.println(out);
				if(in.readableBytes()<leastLength){
					//读完
					break;
				}
			}else{
//				logger.info("拆包 :readable: "+in.readableBytes()+"  packNeed: "+packLen);
				break;//more data needed;
			}
		}
	}

	/**
	 * 此处已经完成拆包粘包
	 * 实际上96中还有8=FIXT.1.1，里面还有96
	 * @param pack
	 * @param out
	 * @param len89
	 */
	private static void decodePack(ByteBuf pack, List<Object> out, int len89) {
		//8=STEP.1.0.09=6635=A49=VDE56=VSS34=052=20171213-15:35:19347=UTF-898=0108=010=192
		pack.readerIndex(len89);//去除头
		boolean fixtFlag = false;
		Map<Integer,Object> mapTar = new LinkedHashMap<>(17);
		while(pack.readableBytes()!=0){
			byte[] field;
			int tarIndex = pack.bytesBefore(StepCommon.soh);
			field = new byte[tarIndex];
			pack.readBytes(field);
			pack.skipBytes(1);
			String value = new String(field);//到这边都已经过滤了soh
			String[] strTemp = value.split("=");
			int tag = Integer.parseInt(strTemp[0]);
			//----------第一层step协议过滤--------------------
			if(!fixtFlag && tag==35){
				if(value.equals(HEART_BEAT)){
					mapTar.put(tag,-11);
					out.add(mapTar);
					logger.info("heart beat");
					return;//是心跳包，停止后面解包
				}
				//在step协议里面，使用35=UA5302来过滤行情，其他的行情还有35=UA2201,35=UA7301
				if(value.equals("35=UA5302")){
					continue;
				}else{
					mapTar.put(tag,strTemp[1]);
					out.add(mapTar);
					logger.info("过滤行情类型："+value);
					return;
				}
			}
			if(!fixtFlag && tag==96){
				//step协议里面的FIXT协议开始
				fixtFlag = true;//value.startsWith(HEAD_FIXT)
				continue;
			}
			//---------------------------------------------------
			if(fixtFlag){
				//step协议里面的FIXT协议开始
				mapTar.put(tag,strTemp[1]);
				if(tag==35){
					if(strTemp[1].equals("W")){
//						continue;
					}else if(strTemp[1].equals("h")){
						logger.info("!!!市场状态："+new String(pack.array()));
						return;
					}else{
						logger.info("过滤行情类型："+value);
						out.add(mapTar);
						return;
					}
				}
				if(tag==95){
					int bodayLength = Integer.parseInt(strTemp[1]);//value.startsWith("95=")
					pack.skipBytes(3);//过滤96=,之后的bodayLength位是二进制数据
					field = new byte[bodayLength];
					pack.readBytes(field);
					//--------------------------------------
					FastCSDecoder.fastDecode(field,mapTar);
					System.err.println(mapTar);
//					if(mapTar.containsKey(96)){
//						System.out.println(mapTar.get(96));
//					}
					out.add(mapTar);
					return;
				}

			}
			//------------if(fixtFlag)---end-----------------------------------------
		}
//		System.out.println(mapTar);
	}
	/**
	 * 如果本次读取的byte包含头，则读取到头部位置。
	 * 如果本次读完也没有头，就全部丢弃返回false.
	 * 包含头，而且余下数据可以尝试解析，则true
	 * @param in
	 * @return
	 */
	private static boolean isOkPack(ByteBuf in) {
		//{56,61,83,84,69,80};//8=STEP
		if(in.readableBytes()<leastLength){
			return false;
		}
		for(int i=in.readerIndex();i<in.writerIndex();i++){
			if(in.getByte(i) ==56 && in.getByte(i+1) ==61 && in.getByte(i+2) ==83 &&
					in.getByte(i+3) ==84 && in.getByte(i+4) ==69 && in.getByte(i+5) ==80 ){
				//找到头部，把之前的去掉
				if(i>in.readerIndex()){
					in.readerIndex(i);//读去不是头的包
				}
				if(in.readableBytes()>leastLength){
					return true;
				}else{
					//数据不够，等待下一次,但下次肯定以头开始
					logger.info("!!读到含头半包");
					return false;
				}
			}
		}
		//全部不是头，都丢弃
		logger.error("error: 本包没有头，全部丢弃");
		in.readerIndex(in.readerIndex()+in.readableBytes());//读去不是头的包
		return false;
	}

}

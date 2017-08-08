package com.awhyse.gateway;

import com.awhyse.gateway.common.Quote;
import com.awhyse.gateway.transport.FDTFields;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 对接上级gateway的最后一个handleer，具体连接上后初始化信息这些应该在这边
 */
public class MsgPackLiteDataClientHandler extends ChannelInboundHandlerAdapter {

	private static final Logger log = LoggerFactory.getLogger(MsgPackLiteDataClientHandler.class);
	public ChannelHandlerContext ctx = null;
	MsgPackLiteDataClient client = null;
	
	private int bufLenMin = 0,bufLenMax = 0,blockCount = 0;
	private long throughput = 0,msLastTime = 0,msDiff = 0;
	/**
	 * 里面以symbol作为key,后面是所有订阅的symbol的当时quote
	 */
	public static ConcurrentHashMap<String,HashMap<Integer,Object>> mapQuotation = new ConcurrentHashMap<String,HashMap<Integer,Object>>();
	public static ConcurrentHashMap<String,HashMap<Integer,Object>> mapTransaction = new ConcurrentHashMap<String,HashMap<Integer,Object>>();
	/**
	 * 级联的时候，请求codeTable，就把这里面的数据发送给客户端。
	 * 初始化的时候更新这些map
	 */
//	public static ConcurrentHashMap<String,CascadingCodeTable> mapCascadingCodeTable = new ConcurrentHashMap<String,CascadingCodeTable>();
	public static ConcurrentHashMap<String,HashMap<Integer,Object>> mapCodeTable = new ConcurrentHashMap<String,HashMap<Integer,Object>>();

	public MsgPackLiteDataClientHandler(MsgPackLiteDataClient client) {
		this.client = client;
	}

	public void channelActive(ChannelHandlerContext arg0) throws Exception {
		ctx = arg0;
		client.ctx = ctx;
		client.onActive();
		log.info(ctx.channel().localAddress().toString() + " Connected with data server : " + ctx.channel().remoteAddress().toString());
	}

	/**
	 * 发送给上游gateway的请求，比如订阅等
	 * @param str
	 */
	public  void sendRequest(String str) {
		ctx.writeAndFlush(str);
	}
	
    public void sendBufferingSizeMsg(final Integer bufferingSize){
    	StringBuffer msg = new StringBuffer("API=RequestBuffering|");
    	msg.append("BufferingMS=" + bufferingSize);
		String bufStr = msg.toString();
		bufStr += "|Hash=" + bufStr.hashCode();
		sendRequest(bufStr);
    }


	public void channelInactive(ChannelHandlerContext arg0) throws Exception {
		ctx = null;
		client.ctx = ctx;
	}
	
	public void channelReadComplete(ChannelHandlerContext arg0)
			throws Exception {
		arg0.flush();

	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {    	
    	log.warn("ExceptionCaught : " + cause.getMessage() + " - " + ctx.channel().remoteAddress().toString());
		ctx.close();
		if(cause.getMessage() == "Direct buffer memory") {
			log.info("Request System GC");
			System.gc();
		} else {
	    	log.warn(cause.getMessage(),cause);	     		
		}
	}
	/**
	 * 上游原始数据
	 * @param arg0
	 * @param arg1
	 * @throws Exception
	 */
	public void channelRead(ChannelHandlerContext arg0, Object arg1)
			throws Exception {
		//由decode解析成map了
		if(arg1 instanceof HashMap<?,?>) {
			@SuppressWarnings("unchecked")
			HashMap<Integer,Object> in = (HashMap<Integer,Object>)arg1;
			if(in != null) {
				processData(in,false);
			}
		}else{
			log.error("unknow read :"+arg1.toString());
		}
		client.mkGWMsgReciver.onMsg(arg1);
	}
	/**
	 * 处理上游gateway打过来的消息。比如分发行情到各订阅客户端
	 * @param in
	 * @param inArray
	 */
	public void processData(HashMap<Integer,Object> in , boolean inArray) {
		
		try {
			// 沒有 packet type , 就無法處理.
			Object ob = in.get(FDTFields.PacketType);
			if(ob ==null) {
				return;
			}				
			int iPacketType = ((Number)ob).intValue();

			switch(iPacketType) {
			case FDTFields.PacketArray :
				//如果是PacketArray的包，就拆开，并解析
				ArrayList<HashMap<Integer,Object>> lst = (ArrayList<HashMap<Integer,Object>>)in.get(FDTFields.ArrayOfPacket);
				if(lst != null) {
					for(HashMap<Integer,Object> map : lst) {
						processData(map,true);
					}					
				}
				break;
			case FDTFields.WindIndexData :
			case FDTFields.WindMarketData :	//2015这个是A股的类型，iPacketType
			case FDTFields.HKStockData:
				if(in.containsKey(FDTFields.WindSymbolCode)) {					
					String symbol = new String((byte[])in.get(FDTFields.WindSymbolCode), CharsetUtil.UTF_8);
					HashMap<Integer,Object> mp = mapQuotation.get(symbol);
					if(mp == null) {
						mapQuotation.put(symbol, in);
						mp = in;
					} else {
						mp.putAll(in);
					}
					Quote quote = Quote.getQuoteByMap(mp);
					System.err.println(quote.toString());
//					client.mkGWMsgReciver.onQupte(arg1);
				}
				break;
//			case FDTFields.WindFutureData :
//				if(in.containsKey(FDTFields.WindSymbolCode)) {
//					String symbol = new String((byte[])in.get(FDTFields.WindSymbolCode), CharsetUtil.UTF_8);
//					MsgPackLiteDataServerHandler.sendMssagePackToAllClientByRegistration(in, symbol,inArray);
//					HashMap<Integer,Object> mp = mapQuotation.get(symbol);
//					if(mp == null) {
//						mapQuotation.put(symbol, in);
//					} else {
//						mp.putAll(in);
//					}
//				}
//				break;
			case FDTFields.WindCodeTable :
				processCodeTable(in);
				break;				
			case FDTFields.WindCodeTableResult :
				//这个是获取codetable的
				processCodeTableResult(in);				
				break;	
			case FDTFields.SnapShotEnds :
				break;
			case FDTFields.Heartbeat :
				//心跳
				break;
			}
		}
		catch(Exception e) {
			log.warn(e.getMessage(),e);	
		}
	}	
	
	public static void processMarkets(Object obj) throws Exception 
	{
		if(obj == null) {
			return;			
		}
		ArrayList<String> markets = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		ArrayList<byte[]> lst = (ArrayList<byte[]>)obj;
		for(byte[] bytes : lst) {
			markets.add(new String(bytes, CharsetUtil.UTF_8));
		}		
//		Gateway4j.instance.convertMarketsMP(markets);
	}
	
	public static void processCodeTable(HashMap<Integer,Object> in) {
		try {			
			String symbol = new String((byte[])in.get(FDTFields.WindSymbolCode), CharsetUtil.UTF_8);
			String market = new String((byte[])in.get(FDTFields.SecurityExchange), CharsetUtil.UTF_8);
			int ser = ((Number)in.get(FDTFields.SerialNumber)).intValue();
//			if (Gateway4j.instance.isRunProdNameCheck())
//				mapCodeTable.put(symbol, in);
//			CascadingCodeTable cct = mapCascadingCodeTable.get(market);
//			if(cct != null) {
//				cct.mapMPCode.put(symbol,in);
//			} else {
//				log.warn("Missing Cascading Code Table , Market : " + market);
//				return;
//			}
//			if(ser < 0 && cct.mpCodeTableResult != null) {
//				log.info("Received Code Table : " + market + " , count " + cct.mapMPCode.size() + " , last serial : " + ser);
//				MsgPackLiteDataServerHandler.sendMessagePackToAllClient(cct.mpCodeTableResult);
//				if (checkCodeTableReady())
//				{
//					MsgPackLiteDataClient.setCodeTableReady(true);
//				}
//			}
			
		} catch (Exception e) {
			log.warn("Exception : " + e.getMessage(),e);
		}
	}
	public static void processCodeTableResult(HashMap<Integer,Object> in) {
		String market = null;
//		CascadingCodeTable cct =  null;
//		long codesHashCode = 0;
//		int dataCount = 0;
//		try {
//			market = new String((byte[])in.get(FDTFields.SecurityExchange), CharsetUtil.UTF_8);
//			codesHashCode = ((Number)in.get(FDTFields.HashCode)).longValue();
//			cct = mapCascadingCodeTable.get(market);
//			if(cct != null) {
//				dataCount = ((Number)in.get(FDTFields.DataCount)).intValue();
//				if(cct.codesHashCode == codesHashCode && cct.mapMPCode.size() == dataCount) {
//					log.info("Code Table No Change , Market : " + market);
//					return;
//				}
//			}
//			if(cct == null) {
//				cct = new CascadingCodeTable();
//			}
//			cct.mapMPCode.clear();
//			cct.codesHashCode = codesHashCode;
//			cct.mpCodeTableResult = in;
//			cct.strMarket = market;
//			cct.DataCount = dataCount;
//			mapCascadingCodeTable.put(market, cct);
//
//			if(ctx != null && market != null) {
//				ctx.writeAndFlush(MsgPackLiteDataServerHandler.addHashTail("API=GetCodeTable|Market=" + market,true));
//				log.info("Request Code Table , Market : " + market);
//			}
//		} catch (Exception e) {
//			log.warn("Exception : " + e.getMessage(),e);
//		}
	}
	public static ConcurrentHashMap<String, HashMap<Integer,Object>>getMPCodeByMarket(String market) {
//		CascadingCodeTable cct = mapCascadingCodeTable.get(market);
//		if(cct != null) {
//			return cct.mapMPCode;
//		}
		return null;
	}

	private static boolean checkCodeTableReady()
	{
		String[] markets = MsgPackLiteDataClient.getCodeTableNeededMarkets();
		if(null == markets){
			return false;
		}
		for (String market : markets)
		{
//			CascadingCodeTable cct = mapCascadingCodeTable.get(market);
//			if (cct == null || cct.mapMPCode.isEmpty())
//			{
//				return false;
//			}
		}
		return true;
	}
	
	public static String fullcastToHalf(String str)
	{

		for (char c : str.toCharArray())
		{
			str = str.replaceAll("　", " ");
			if ((int) c >= 65281 && (int) c <= 65374)
			{
				str = str.replace(c, (char) (((int) c) - 65248));
			}
		}
		return str;
	}
}


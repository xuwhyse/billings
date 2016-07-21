//package com.cyanspring.info.alert;
//
//import com.cyanspring.common.alert.ParseData;
//import com.cyanspring.common.alert.SendNotificationRequestEvent;
//import com.cyanspring.common.event.AsyncTimerEvent;
//import com.cyanspring.common.event.ScheduleManager;
//
//import org.hibernate.SessionFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.ConcurrentLinkedQueue;
//
///**
// * Created by xiaowenda on 2015/9/21.
// */
//public class IMManager extends Compute
//{
//	private static final Logger log = LoggerFactory.getLogger(AlertManager.class);
//
//	public ConcurrentLinkedQueue<ParseData> ParseDataQueue;
//	private ArrayList<IMThread> IMThreadList;
//
//	private AsyncTimerEvent timerEvent = new AsyncTimerEvent();
//	private int timeoutSecond;
//	private int createThreadCount;
//	private int maxRetrytimes;
//	private long killTimeoutSecond;
//
//	private String accountPrice = "im_price";
//	private String accountOrder = "im_trade";
//	private String uri = "https://api.netease.im/nimserver/";
//	private String appKey = "";
//	private String appSecret = "";
//	private String tokenSalt = "0123456789abcdef";
//	private String iv = "0123456789abcdef";
//	private String action = "";
//	private boolean attach = true;
//
//	private int CheckThreadStatusInterval = 60000; // 60 seconds
//
//	@Autowired
//	SessionFactory sessionFactory;
//
//	@Autowired
//	ScheduleManager scheduleManager;
//
//	@Override
//	public void SubscirbetoEvents()
//	{
//	}
//
//	@Override
//	public void SubscribetoEventsMD()
//	{
//		SubscirbetoEvent(SendNotificationRequestEvent.class);
//	}
//
//	@Override
//	public void init()
//	{
//		String strThreadId = "";
//		try
//		{
//			log.info("Initialising...");
//
//			ParseDataQueue = new ConcurrentLinkedQueue<ParseData>();
//			IMThreadList = new ArrayList<IMThread>();
//
//			scheduleManager.scheduleRepeatTimerEvent(CheckThreadStatusInterval,
//					getEventProcessorMD(), timerEvent);
//
//			if (createThreadCount > 0)
//			{
//				for (int i = 0; i < createThreadCount; i++)
//				{
//					strThreadId = "IMThread" + String.valueOf(i);
//					IMThread IMT = new IMThread(strThreadId, ParseDataQueue, 
//							timeoutSecond, maxRetrytimes, accountPrice, accountOrder,
//							uri, appKey, appSecret, tokenSalt, iv, action, getGateway(), attach);
//					log.info("[" + strThreadId + "] New.");
//					IMThreadList.add(IMT);
//					IMT.start();
//				}
//			}
//			else
//			{
//				log.warn("createThreadCount Setting error : "
//						+ String.valueOf(getCreateThreadCount()));
//			}
//
//		}
//		catch (Exception e)
//		{
//			log.warn("[" + strThreadId + "] Exception : " + e.getMessage());
//		}
//
//	}
//
//	@Override
//	public void processSendNotificationRequestEvent(SendNotificationRequestEvent event,
//			List<Compute> computes)
//	{
//		ParseDataQueue.add(event.getPD());
//	}
//
//	public int getCreateThreadCount()
//	{
//		return createThreadCount;
//	}
//
//	public void setCreateThreadCount(int createThreadCount)
//	{
//		this.createThreadCount = createThreadCount;
//	}
//
//	public long getKillTimeoutSecond()
//	{
//		return killTimeoutSecond;
//	}
//
//	public void setKillTimeoutSecond(long killTimeoutSecond)
//	{
//		this.killTimeoutSecond = killTimeoutSecond;
//	}
//
//	public String getUri()
//	{
//		return uri;
//	}
//
//	public void setUri(String uri)
//	{
//		this.uri = uri;
//	}
//
//	public String getAppKey()
//	{
//		return appKey;
//	}
//
//	public void setAppKey(String appKey)
//	{
//		this.appKey = appKey;
//	}
//
//	public String getAppSecret()
//	{
//		return appSecret;
//	}
//
//	public void setAppSecret(String appSecret)
//	{
//		this.appSecret = appSecret;
//	}
//
//	public String getTokenSalt()
//	{
//		return tokenSalt;
//	}
//
//	public void setTokenSalt(String tokenSalt)
//	{
//		this.tokenSalt = tokenSalt;
//	}
//
//	public String getIv()
//	{
//		return iv;
//	}
//
//	public void setIv(String iv)
//	{
//		this.iv = iv;
//	}
//
//	public String getAction()
//	{
//		return action;
//	}
//
//	public void setAction(String action)
//	{
//		this.action = action;
//	}
//
//	public String getAccountPrice()
//	{
//		return accountPrice;
//	}
//
//	public void setAccountPrice(String accountPrice)
//	{
//		this.accountPrice = accountPrice;
//	}
//
//	public String getAccountOrder()
//	{
//		return accountOrder;
//	}
//
//	public void setAccountOrder(String accountOrder)
//	{
//		this.accountOrder = accountOrder;
//	}
//
//	public int getTimeoutSecond()
//	{
//		return timeoutSecond;
//	}
//
//	public void setTimeoutSecond(int timeoutSecond)
//	{
//		this.timeoutSecond = timeoutSecond;
//	}
//
//	public int getMaxRetrytimes()
//	{
//		return maxRetrytimes;
//	}
//
//	public void setMaxRetrytimes(int maxRetrytimes)
//	{
//		this.maxRetrytimes = maxRetrytimes;
//	}
//
//	public boolean isAttach()
//	{
//		return attach;
//	}
//
//	public void setAttach(boolean attach)
//	{
//		this.attach = attach;
//	}
//}

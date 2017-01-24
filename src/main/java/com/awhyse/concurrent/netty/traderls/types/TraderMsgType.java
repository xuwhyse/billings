package com.awhyse.concurrent.netty.traderls.types;

/**
 * 跟交易员服务器通信的MsgType类型
 * 
 * author:xumin 
 * 2016-10-18 上午10:25:32
 */
public class TraderMsgType {

	public final static int Login = 0x001;//登录
	public final static int Logoff = 0x002;//注销
	public final static int ChangePassword = 0x003;//修改密码
	public final static int HeartBeat = 0x00F;//心跳
	public final static int NewOrder = 0x102;//下单
	public final static int CancelOrder = 0x104;//撤单
	public final static int StopStatus = 0x10E;//停机状态更新
	public final static int UpdatePositions = 0x111;//仓位更新
	public final static int UpdateExecutions = 0x112;//成交更新
	public final static int UpdateOrders = 0x116;//委托更新
	public final static int UserInfo = 0x117;//用户信息(包含停机位和可用资金)
	public final static int QueryHistoryNet = 0x118;//查询用户历史业绩

}

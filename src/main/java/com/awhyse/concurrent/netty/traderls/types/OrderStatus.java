package com.awhyse.concurrent.netty.traderls.types;

public class OrderStatus {
	public final static int None = 0;//
	public final static int PendingNew = 1;//
	public final static int New = 2;//
	public final static int Rejected = 3;//
	public final static int PartFilled = 4;//手机交易app
	public final static int Filled = 5;//
	public final static int Cancelled = 7;//
	public final static int PartFillPartCancel = 8;//
}

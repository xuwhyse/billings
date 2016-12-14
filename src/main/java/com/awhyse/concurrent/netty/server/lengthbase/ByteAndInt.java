package com.awhyse.concurrent.netty.server.lengthbase;

public class ByteAndInt {

	/**
	 * @param args
	 * author:xumin 
	 * 2016-10-13 下午5:23:30
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		byte[] temp = toByteArray(9,4);
		System.err.println(toInt(temp));
	}
	/**
	 * 将int转化成指定长度的byte数组
	 * @param iSource
	 * @param iArrayLen <=4
	 * @return
	 * author:xumin 
	 * 2016-10-13 下午5:23:56
	 */
	public static byte[] toByteArray(int iSource, int iArrayLen) {
	    byte[] bLocalArr = new byte[iArrayLen];
	    for (int i = 0; (i<=4 && i < iArrayLen); i++) {
	    	int temp = iArrayLen-i-1;
	        bLocalArr[i] = (byte) (iSource >> 8 * temp & 0xFF);
	    }
	    return bLocalArr;
	}

	// 将byte数组bRefArr转为一个整数,字节数组的低位是整型的低字节位
	public static int toInt(byte[] bRefArr) {
	    int iOutcome = 0;
	    byte bLoop;
	    int length = bRefArr.length;
	    for (int i = 0; i < length; i++) {
	        bLoop = bRefArr[i];
	        int temp = length-i-1;
	        iOutcome += (bLoop & 0xFF) << (8 * temp);
	    }
	    return iOutcome;
	}

}

package com.awhyse.util;

import com.billings.billingsystem.bean.util.MenuBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtil {

	static Logger logger = LoggerFactory.getLogger(TimeUtil.class);
	static DateFormat formatDate=new SimpleDateFormat("yyyyMMdd");
	static DateFormat fadeLoginFormatDate=new SimpleDateFormat("yyyy/MM/dd HH:mm");
	static DateFormat formatYear=new SimpleDateFormat("yyyy");
	static DateFormat formatDate2 = new SimpleDateFormat("yyyy-MM-dd");
	static DateFormat formatTime=new SimpleDateFormat("HH:mm:ss");
	static DateFormat formatTime2=new SimpleDateFormat("HHmmss");
	/**
	 * String dateInString = "Jul 3, 2017 10:27:00 AM"; formatter.parse(dateInString);
	 */
	static SimpleDateFormat formatter = new SimpleDateFormat("MMM d, yyyy h:m:s aa", Locale.ENGLISH);
	/**
	 * @param args
	 * author:xumin 
	 * 2016-4-22 下午8:01:21
	 */
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		MenuBean menuBean = new MenuBean();
		getBean(menuBean);
		System.err.print("");
	}

	private static void getBean(MenuBean menuBean) {
//		menuBean = new MenuBean();
		menuBean.setMenuid("dfdg");
	}

	public static String getYear() {
		Date date=new Date();
		String time=formatYear.format(date);
		return time;
	}
	public static String getToday() {
		Date date=new Date();
		String time=formatDate.format(date);
		return time;
	}

	public static String getTodayTime() {
		Date date=new Date();
		String time=formatTime.format(date);
		return time;
	}
	/**
	 * 之前或者之后的日子
	 * @param days
	 * @return
	 * author:xumin 
	 * 2016-5-25 上午10:36:42
	 */
	public static String getDay(int days) {
		Date dat = null;
		Calendar cd = Calendar.getInstance();
		cd.add(Calendar.DATE, days);
		dat = cd.getTime();
		String time = formatDate.format(dat);
		return time;
	}

	public static String transferAlertDate(String date) {
		String sfstr = "";
		try {
			sfstr = formatDate2.format(formatDate.parse(date));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sfstr;
	}

	public static String transferAlertTime(String time) {
		String sfstr = "";
		try {
			sfstr = formatTime.format(formatTime2.parse(time));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sfstr;
	}
	/**
	 * 今天是否是周末
	 * @return
	 * author:xumin 
	 * 2016-8-3 下午2:44:43
	 */
	public static boolean isWeekDayCTP() {
		Date bdate=new Date();
		int timeNow = TimeUtil.getTodayTimeInt();
		boolean flag = (0<=timeNow && timeNow<=23000);
		Calendar cal = Calendar.getInstance();
		cal.setTime(bdate);
		if((cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY && !flag) || cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)
		   return true; 
		return false;
	}

	public static int getTodayTimeInt() {
		Date date=new Date();
		String time=formatTime2.format(date);
		return Integer.parseInt(time);
	}
	public static String getFadeLoginTimeByLong(long fadeLoginTime) {
		Date date=new Date(fadeLoginTime);
		String time=fadeLoginFormatDate.format(date);
		return time;
	}


}

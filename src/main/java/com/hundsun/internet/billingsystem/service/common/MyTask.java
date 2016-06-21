package com.hundsun.internet.billingsystem.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.hundsun.internet.billingsystem.service.billingsoper.BillingsOper;

/*
 * 1.定时器的任务方法不能有返回值（如果有返回值，spring初始化的时候会告诉你有个错误、需要设定一个proxytargetclass的某个值为true
 * 一个cronExpression表达式有至少6个（也可能是7个）由空格分隔的时间元素。从左至右，这些元素的定义如下：  
 * 1．秒（0–59） 2．分钟（0–59） 3．小时（0–23）  4．月份中的日期（1–31） 5．月份（1–12或JAN–DEC）  
 * 6．星期中的日期（1–7或SUN–SAT） 7．年份（1970–2099） 
 * 0 0 10,14,16 * * ?  每天上午10点,下午2点和下午4点 
 *  0 0,15,30,45 * 1-10 * ?  每月前10天每隔15分钟  
 * 30 0 0 1 1 ? 2012  在2012年1月1日午夜过30秒时  0 0 8-5 ? * MON-FRI
 * 
 * 
 * “*”——字符可以用于所有字段，在“分”字段中设为"*"表示"每一分钟"的含义。 
 * “?”——字符可以用在“日”和“周几”字段.它用来指定'不明确的值'.这在你需要指定这两个字段中的某一个值而不是另外一个的时候会被用到。在后面的例子中可以看到其含义。 
“-”——字符被用来指定一个值的范围，比如在“小时”字段中设为"10-12"表示"10点到
 */
/**
 * 在安全框架里实例化的触发器任务
 * @author xunmin
 *
 */
@Service
public class MyTask {
	
	@Autowired
	BillingsOper billingsOper;
	
//	MyTask(){
//		System.err.println("TaskTest初始化立刻");
//	}
//	@Scheduled(cron="0/20 * *  * * ? ")
//	public void myTest(){
//		System.err.println("+++++++++++++++");
//	}
//	@Scheduled(cron="0/5 * *  * * ? ")
//	public void myTest3(){
//		System.err.println("+++++");
//	}
	@Scheduled(cron="0 0 0  * * ? ")
	public void everyDayNight(){
		billingsOper.balance();
		System.err.println("每天凌晨执行一次！！！！！！！！");
	}

}

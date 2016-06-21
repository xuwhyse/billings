package com.awhyse.concurrent.fenhongbao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * 拆红包1000块钱 分给100个人  没人得到1-20块之间的钱
 * @author xumin 2015-6-30 下午5:37:02
 *
 */
public class FenBaoUtil {
	
	public static void main(String[] args){
//		long t =15000*300000l;
//		System.err.println(t/3000);
		testFenBao();
//		for(int i=0;i<400;i++)
//			getRate( limitLow, limitHgh);
		
//		getF1Money(12,13,200,1,20);
		
	}
	private static void testFenBao() {
		int totalMoney = 370300;
		int totalPeople = 20000;//人数决定递归数量
		int limitLow = 1;
		int limitHgh = 40;
		List<Integer>  listInt = new ArrayList<Integer>(totalPeople);
		subPackage(totalMoney,totalPeople,limitLow,limitHgh,listInt);
		int total = 0;
		for(int i=0;i<listInt.size();i++){
			int t = listInt.get(i);
			if(t>limitHgh || t<limitLow)
				System.err.println("这个数超过了"+t);
			total+=t;
		}
		System.err.println(total);
		System.err.println(listInt.size());
		System.err.println(listInt);
	}
	/**
	 * 注意: 总钱数不能低于下限，或者高于上限
	 * xumin  2015-6-30 上午10:53:31
	 * @param totalMoney  要分的钱
	 * @param totalPeople  要分的人
	 * @param limitLow 没人分钱下限
	 * @param limitHgh
	 * @param listInt 传入的引用参数，执行后返回分包结果
	 */
	public static void subPackage(int totalMoney, int totalPeople,
			int limitLow, int limitHgh, List<Integer> listInt) {
		if(totalPeople==1){
			//总钱数只有一个人分，则给他全部
//			if(totalMoney>limitHgh || totalMoney<limitLow)
//				System.err.println("totalMoney:"+totalMoney);
			listInt.add(totalMoney);
			return;
		}
		if(totalPeople==2){
			int m1 = getF1Money(1, 1, totalMoney, limitLow, limitHgh);
			listInt.add(m1);
			int m2 = totalMoney-m1;
			listInt.add(m2);
			return;
		}
		int lowCount =   limitLow*totalPeople;
		int highCount =   limitHgh*totalPeople;
		if(totalMoney<=lowCount){
			//总钱数比分的最小的算法还少，则有问题全部设置1返回
			System.err.println("totalMoney<=lowCount  people总数"+totalPeople);
			for(int i=0;i<totalPeople;i++)
				listInt.add(limitLow);
			return;
		}
		if(totalMoney>=highCount){
			//总钱数，>=最大分钱数，则设置最大分钱书返回
			System.err.println("totalMoney>=highCount people总数"+totalPeople);
			for(int i=0;i<totalPeople;i++)
				listInt.add(limitHgh);
			return;
		}
		//人分两半，在最小和最大中获取随机数，并按比例作为总资产递归
		int p1 = totalPeople/2;//totalPeople肯定是>2的数字，所以p1不可能=0
		int p2 = totalPeople-p1;
		//按照比例分总的钱数
		int m1 = getFirstMoney(p1,p2,totalMoney,limitLow,limitHgh);
		int m2 = totalMoney-m1;
		subPackage(m1,p1,limitLow,limitHgh,listInt);
		subPackage(m2,p2,limitLow,limitHgh,listInt);
	}
	/**
	 * 
	 * xumin  2015-6-30 下午1:38:17
	 * @param p1
	 * @param p2
	 * @param totalMoney
	 * @param limitLow
	 * @param limitHgh
	 * @return
	 */
	private static int getFirstMoney(int p1, int p2, int totalMoney,
			int limitLow, int limitHgh) {
		int m1=0;
		if(p1>=4){
			//这个是平分的，所以平滑,这边可以调节平滑度，测试后4比较靠谱
			m1 = getF2Money(p1, p2, totalMoney, limitLow,limitHgh);
		}else
			m1=getF1Money(p1, p2, totalMoney, limitLow, limitHgh);
		return m1;
	}
	/**
	 * xumin  2015-6-30 上午11:45:36
	 * @param p1 >=1
	 * @param p2 >=1
	 * @param totalMoney
	 * @param limitHgh 
	 * @param limitLow 
	 * @return
	 */
	private static int getF1Money(int p1, int p2, int totalMoney, int limitLow, int limitHgh) {
		int r1 = getRate(limitLow,limitHgh);
		//由方程计算得出
		int temp = p1*r1+(limitLow+limitHgh-r1)*p2;
		double mt = r1*p1*totalMoney*1.0/temp;//按照比例分成
		int m1 = new BigDecimal(mt).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
		m1 = getRulerFen(m1,p1,p2,totalMoney,limitLow,limitHgh);
		return m1;
	}
	/**
	 * 增加这个方法的调用平率可以钝化曲线,向中间靠近
	 * xumin  2015-6-30 下午1:32:04
	 * @param p1
	 * @param p2
	 * @param totalMoney
	 * @param limitLow
	 * @param limitHgh
	 * @return
	 */
	private static int getF2Money(int p1, int p2, int totalMoney, int limitLow,
			int limitHgh) {
		Long temp = p1*1l*totalMoney/(p1+p2);//已经划分，但m1必须符合其他规则
		int m1 = temp.intValue();
		m1 = getRulerFen(m1,p1,p2,totalMoney,limitLow,limitHgh);
		return m1;
	}
	/**
	 * 这次调整很重要!!
	 * xumin  2015-6-30 下午2:56:52
	 * @param m1
	 * @param p1
	 * @param p2
	 * @param totalMoney
	 * @param limitLow
	 * @param limitHgh
	 * @return
	 */
	private static int getRulerFen(int m1, int p1, int p2, int totalMoney,
			int limitLow, int limitHgh) {
		int lowCount =   limitLow*(p1+p2);
		int highCount =   limitHgh*(p1+p2);
		if(totalMoney<lowCount || totalMoney>highCount)
			System.err.println("产生的m1有问题，没法优化");
		//1.m1过小，往上调整
		if(m1<=p1*limitLow){
			//m1过小,第一次调整
			m1 = p1*limitLow;
			int m2 = totalMoney-m1;
			if(m2>=p2*limitHgh){
				//调整m1再次扩大
				m2=p2*limitHgh;
				m1 = totalMoney-m2;//如果第二次调整后，m1超过上限则不可能
			}
		}
		else if(m1>=p1*limitHgh){
			//2m1过大，调整
			m1 = p1*limitHgh;//调整到最大
			int m2 = totalMoney-m1;
			if(m2<=p2*limitLow){
				m2 = p2*limitLow;
				m1 = totalMoney-m2;
			}
		}
		else{
			//m1处于正常值,看是否m2需要调整
			int m2 = totalMoney-m1;
			if(m2<=p2*limitLow){
				m2 = p2*limitLow;
				m1 = totalMoney-m2;
			}
			if(m2>=p2*limitHgh){
				//调整m1再次扩大
				m2=p2*limitHgh;
				m1 = totalMoney-m2;//如果第二次调整后，m1超过上限则不可能
			}
		}
		return m1;
	}
	/**
	 * 获取[limitLow,limitHgh] 之中的一个数字[1,20]
	 * xumin  2015-6-30 上午10:10:56
	 * @param limitLow
	 * @param limitHgh
	 * @return
	 */
	private static int getRate(int limitLow, int limitHgh) {
		int tar = new Random().nextInt(limitHgh-limitLow+1)+limitLow;
		return tar;
	}
}

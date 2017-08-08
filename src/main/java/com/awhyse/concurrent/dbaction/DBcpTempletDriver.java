package com.awhyse.concurrent.dbaction;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 */
public class DBcpTempletDriver {

	static Logger logger = LoggerFactory.getLogger(DBcpTempletDriver.class);
	static Map<String, List<String>>  dataMap = new HashMap<String, List<String>>();
    /**
     * init之后就可以使用该对象
     */
    public static JdbcTemplate jdbcTemplate = null;

    static{
		setDataMap("jdbc:mysql://192.168.4.152/TA_DS_TEST","tqt001","tqt001","test");//172.17.5.71:6000适配层套接字非真实mysql
	}
	
	private static void setDataMap(String url, String user, String pass, String key) {
		List<String> list = new ArrayList<String>();
		list.add(url+"?connectTimeout=4000&socketTimeout=60000");
		list.add(user);
		list.add(pass);
		dataMap.put(key, list);
	}

	/**
	 * <property name="removeAbandoned"><value>true</value></property>  
        <property name="removeAbandonedTimeout"><value>180</value></property>  
    <property name="timeBetweenEvictionRunsMillis"><value>60000</value></property>  
    <property name="minEvictableIdleTimeMillis"><value>1800000</value></property>  
	 * @param args
	 */
	public static void main(String[] args) {
		String dburl = "jdbc:mysql://192.168.4.152/info_lunar";
		String user = "tqt001";
		String pwd = "tqt001";
		JdbcTemplate jdbcTemplate = getJdbcTemp(dburl,user,pwd);

		String sql = "SELECT * FROM SH_D WHERE LOW_PRICE >2 AND TURNOVER>50000000 ORDER BY KEYTIME DESC LIMIT 10";
		List<Map<String ,Object>>  listMap = jdbcTemplate.queryForList(sql);
		logger.info("s");
	}

	public static JdbcTemplate getJdbcTemp(String dburl, String user, String pwd) {
		BasicDataSource ds = new BasicDataSource();
		String[] keys = {"test", "online"};
		List<String> listPar = dataMap.get(keys[0]);//test,online
		ds.setUrl(listPar.get(0));//url,user,pass,initialSize
		ds.setUsername(listPar.get(1));
		ds.setPassword(listPar.get(2));
		ds.setDriverClassName("com.mysql.jdbc.Driver");//这边加载Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
		//-----------------------------
		int idle = 10;
		ds.setMaxActive(idle);
		ds.setMaxIdle(idle - 5);
		//保证应用不管，但可能永远得不到线程池返回的有效连接 Timeout waiting for idle object
		ds.setMaxWait(6000);//外面线程池请求连接等待的最大时间,设置这个参数就让  外面的线城池不会永远的等下去，保证应用服务不挂。
		ds.setMinIdle(1);
		ds.setInitialSize(1);

		//----这个回收也很重要，是连接被创建，但现在不通的才回收，select 1通的，不回收--------
		ds.setValidationQuery("select 1");
		ds.setTestWhileIdle(true);
		ds.setTestOnBorrow(false);
		ds.setTimeBetweenEvictionRunsMillis(10000);//回收时间ms300000
		ds.setMinEvictableIdleTimeMillis(10000);//池中的连接空闲30分钟后被回收,默认值就是30分钟
		ds.setNumTestsPerEvictionRun(idle);//每次检查的连接

		//------下面这两行至关重要：在连接获取了，但是一直闲置的情况下，超过那个时间，会被回收利用--------------
		//--锁表的时候连接不会被回收，任然显示活跃--------
	/*
	 * 代码未在finally释放connection ,　不过我们都用sqlmapClientTemplate，底层都有链接释放的过程
遇到数据库死锁。以前遇到过后端存储过程做了锁表操作，导致前台集群中连接池全都被block住，后续的业务处理因为拿不到链接所有都处理失败了。
	 */
		ds.setRemoveAbandoned(true);//连接泄漏回收参数，当可用连接数少于3个时才执行
//		ds.setRemoveAbandonedTimeout(300);//回收废弃时间默认300秒
		ds.setLogAbandoned(true);//出现回收超时连接是不对的！！所以要打印  搜索:AbandonedObjectException
		//--------------------------------

		return new JdbcTemplate(ds);
	}

	private static void testBasicDataSourceJDBCTemp(final BasicDataSource ds) {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		final JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				String temp = "线程:"+Thread.currentThread().getId();
				int num = ds.getNumActive();
				logger.info(temp+" 请求连接,活跃连接数:" +num);
				String sql ="select 1";

//				Object[] args = new Object[] {m_brokerID,m_userID,appType};
//				int[] argTypes = new int[] {Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
//
//				List<Map<String, Object>> listMap =  jdbcTemplate.queryForList(sql, args, argTypes);


				Map<String, Object>  map = jdbcTemplate.queryForMap(sql);
				num = ds.getNumActive();
				logger.info(temp+" 执行完毕,活跃连接数:"+num);
			}
		};
//		//-----------
		for(int i=0;i<1000;i++){
			executorService.execute(runnable);
		}
		executorService.shutdown();
	}
}


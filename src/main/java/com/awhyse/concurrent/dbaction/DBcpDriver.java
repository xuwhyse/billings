package com.awhyse.concurrent.dbaction;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * mysql.url=jdbc:mysql://172.17.5.71:6000/pris_test_new
mysql.user=pris_test_18888
mysql.pass=pris_test_18888
 */
/**
 *INSERT INTO PRIS_GameCode(Id,ProductName,CODE) VALUES('seq','limei','sfadgh');  //seq代替自增长主键
 *@author hzxumin15
 *2015-4-21下午3:49:32
 */
public class DBcpDriver {

	static Logger logger = LoggerFactory.getLogger(DBcpDriver.class);
	static Map<String, List<String>>  dataMap = new HashMap<String, List<String>>();
	static{
		setDataMap("jdbc:mysql://192.168.4.152/TA_DS_TEST","tqt001","tqt001","test");//172.17.5.71:6000适配层套接字非真实mysql
		setDataMap("jdbc:mysql://10.120.154.111:6000/yuedu-online","pris_product","pris_product","online");
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
		BasicDataSource  ds = new BasicDataSource();
		String[] keys = {"test","online"};
		List<String> listPar = dataMap.get(keys[0]);//test,online
		ds.setUrl(listPar.get(0));//url,user,pass,initialSize
		ds.setUsername(listPar.get(1));
		ds.setPassword(listPar.get(2));
		ds.setDriverClassName("com.mysql.jdbc.Driver");//这边加载Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
		//-----------------------------
		int idle = 20;
		ds.setMaxActive(idle);
		ds.setMaxIdle(idle);
		//保证应用不管，但可能永远得不到线程池返回的有效连接 Timeout waiting for idle object
		ds.setMaxWait(6000);//外面线程池请求连接等待的最大时间,设置这个参数就让  外面的线城池不会永远的等下去，保证应用服务不挂。
		ds.setMinIdle(5);
		ds.setInitialSize(5);
		
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
		
//		testBasicDataSource(ds);
		testBasicDataSourceJDBCTemp(ds);
//		doSomeThings(ds);
		
	}
	private static void testBasicDataSourceJDBCTemp(final BasicDataSource ds) {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		final JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		jdbcTemplate.execute("sql");
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				String temp = "线程:"+Thread.currentThread().getId();
				int num = ds.getNumActive();
				logger.info(temp+" 请求连接,活跃连接数:" +num);
				String sql ="select 1";
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

	private static void testBasicDataSource(final BasicDataSource ds) {
		// TODO Auto-generated method stub
		ExecutorService executorService = Executors.newFixedThreadPool(10);
//		jdbcTemplate.setQueryTimeout(queryTimeout)
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				String temp = "线程:"+Thread.currentThread().getId();
				try {
					System.err.println(temp+" 请求连接");
					Connection connection = ds.getConnection();//Timeout waiting for idle object-->外部线程池请求连接超过最大连接
					System.err.println(temp+" 得到连接");
					try {
						Thread.currentThread().sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.err.println(temp+"处理完毕关闭连接");
					connection.close();
				} catch (SQLException e) {
					System.err.println(temp+" 获取连接失败！");
					e.printStackTrace();
				}
			}
		};
		//-----------
		for(int i=0;i<1000;i++){
			int num = ds.getNumActive();
			System.err.println("活跃连接数:"+num);
			executorService.execute(runnable);
			num = ds.getNumActive();
			System.err.println("活跃连接数:"+num);
			try {
				Thread.currentThread().sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
//		executorService.shutdown();
	}
	/**
	 * 自己的业务主要写在这里
	 * xumin  2015-7-8 下午4:25:22
	 * @param ds
	 */
	private static void doSomeThings(BasicDataSource ds) {
		// TODO Auto-generated method stub
		Connection connection=null;
		try {
			connection  = ds.getConnection();//java.sql.Connection-->拿到这个就好办了,就是对数据库的一个连接
			connection.setAutoCommit(false);
			//=========里面是自由代码,增删改查===================
			select1(connection);
			//============================================
			connection.commit();
		} catch (SQLException  e) {
			try {
				if(connection!=null)
					connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			try {
				if(connection!=null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static void select1(Connection connection) throws SQLException {
		String sql = "SELECT * FROM PRIS_SourceComment limit 1";
		List<Map<String, Object>>  list = queryForList(sql,connection);
		System.err.println(list.toString());
	}

	private static void testDB(Connection connection) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM  PRIS_ExchangeCode  ORDER BY id DESC LIMIT 4";
		List<Map<String, Object>>  list = queryForList(sql,connection);
		System.err.println(list.toString());
	}

	/**
	 * 这个是一个实例
	 * @throws SQLException
	 */
	private static void updateTestCommont(Connection connection) throws SQLException {
		// TODO Auto-generated method stub
		List<Map<String, Object>>  list = queryForList("SELECT SourceUuid,COUNT(*) AS number FROM PRIS_SourceComment GROUP BY SourceUuid ",connection);//这个是一般查询语句
		String sql = "UPDATE PRIS_BookSource SET commentCount = 2 WHERE SourceUuid = 'hdj'";
		for(int i=0;i<list.size();i++){
			String SourceUuid = list.get(i).get("SourceUuid").toString();
			int count = Integer.parseInt(list.get(i).get("number").toString());
			sql = "UPDATE PRIS_BookSource SET commentCount = " +
					count +"  WHERE SourceUuid = '" +SourceUuid +"'";
			int aa = uDorAddorDel(sql,connection);
//			System.err.println(SourceUuid+aa+count);
		}
	}
	
	/**
	 * 执行update，del，或者add的sql时候调用
	 * xumin  2015-6-19 下午4:25:59
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	private static int uDorAddorDel(String sql,Connection connection) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(sql);
		return ps.executeUpdate();
	}
	/**
	 * 查询语句调用
	 * xumin  2015-6-19 下午4:26:32
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	private static List<Map<String, Object>> queryForList(String sql,Connection connection) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Map<String, Object>>  list = getListMapFromRS(rs);
		return list;
	}

	private static List<Map<String, Object>> getListMapFromRS(ResultSet rs) {
		try {
			ResultSetMetaData md;
			md = rs.getMetaData();
			md.getColumnCount();
			int num = md.getColumnCount();
			rs.last();
			int rows = rs.getRow();//获取一共行数
			List<Map<String, Object>> listOfRows = new ArrayList<Map<String, Object>>(rows); //
			
			rs.beforeFirst();//游标返回
			while (rs.next()) {
				Map<String, Object> mapOfColValues = new HashMap<String, Object>(num);  
				for (int i = 1; i <= num; i++) {
					String name = md.getColumnName(i);
					Object ob = rs.getObject(i);
					mapOfColValues.put(name, ob);  
				}
				listOfRows.add(mapOfColValues);  
			} 
			return listOfRows;  
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;  
	}

}


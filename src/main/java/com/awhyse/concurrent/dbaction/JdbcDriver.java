package com.awhyse.concurrent.dbaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcDriver {

	static List<String>  urlList = new ArrayList<String>();
	static{
		seturlList("jdbc:mysql://172.17.5.71:6000/pris_test_new","pris_test_18888","pris_test_18888");//172.17.5.71:6000适配层套接字非真实mysql
		seturlList("jdbc:mysql://10.120.154.111:6000/yuedu-online","pris_product","pris_product");
	}
	private static Connection connection;
	private static int test=0,online=1;
//	String url = "jdbc:mysql://localhost:3306/javademo?"
//            + "user=root&password=root&useUnicode=true&characterEncoding=UTF8";
	private static void seturlList(String url, String user, String pass) {
		String tar = url+"?user="+user+"&password="+pass+
				"&connectTimeout=4000&socketTimeout=60000&useUnicode=true&characterEncoding=UTF8";
		urlList.add(tar);
	}
	
	public static void main(String[] args){
		try{
			Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
			connection = DriverManager.getConnection(urlList.get(test));
			connection.setAutoCommit(false);
			//=========里面是自由代码,增删改查===================
			select1();
			//============================================
			connection.commit();
		}catch (Exception  e) {
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
	//=================
	private static void select1() throws SQLException {
		String sql = "SELECT 1";
		List<Map<String, Object>>  list = queryForList(sql);
		System.err.println(list.toString());
	}
	/**
	 * 执行update，del，或者add的sql时候调用
	 * xumin  2015-6-19 下午4:25:59
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	private static int uDorAddorDel(String sql) throws SQLException {
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
	private static List<Map<String, Object>> queryForList(String sql) throws SQLException {
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

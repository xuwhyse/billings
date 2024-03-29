package com.awhyse.concurrent.dbaction;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * ds使用阿里的Druid，mapper使用spring的JDBCTemplet
 * Created by whyse
 * on 2017/5/4 17:25
 */
public class DruidJdbcTempDriver{
    static Logger logger = LoggerFactory.getLogger(DruidJdbcTempDriver.class);
    public static void main(String[] args) {
        test();
    }
    private static void test() {
        String dburl = "jdbc:mysql://192.168.4.152/info_lunar?useUnicode=true&characterEncoding=utf8";
        String user = "tqt001";
        String pwd = "tqt001";
        JdbcTemplate jdbcTemplate = getJdbcTemp(dburl,user,pwd);

        //sql安全的用法别忘了
        //				Object[] args = new Object[] {m_brokerID,m_userID,appType};
//				int[] argTypes = new int[] {Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
//
//				List<Map<String, Object>> listMap =  jdbcTemplate.queryForList(sql, args, argTypes);

        String sql = "SELECT * FROM SH_D WHERE LOW_PRICE >2 AND TURNOVER>50000000 ORDER BY KEYTIME DESC LIMIT 10";
        List<Map<String ,Object>>  listMap = jdbcTemplate.queryForList(sql);
        logger.info("s");
    }

    /**
     * 获取jdbcTemplet
     * @param dburl
     * @param user
     * @param pwd
     * @return
     */
    public static JdbcTemplate getJdbcTemp(String dburl, String user, String pwd) {
        DruidDataSource ds = new DruidDataSource();
        ds.setUrl(dburl);//url,user,pass,initialSize
        ds.setUsername(user);
        ds.setPassword(pwd);
        ds.setDriverClassName("com.mysql.jdbc.Driver");//这边加载Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
        //-----------------------------
        int idle = 20;
        ds.setMaxActive(idle);
        //保证应用不管，但可能永远得不到线程池返回的有效连接 Timeout waiting for idle object
        ds.setMaxWait(10000);//外面线程池请求连接等待的最大时间,设置这个参数就让  外面的线城池不会永远的等下去，保证应用服务不挂。
        ds.setMinIdle(1);
        ds.setInitialSize(1);

        //----这个回收也很重要，是连接被创建，但现在不通的才回收，select 1通的，不回收--------
        ds.setValidationQuery("select 1");
        ds.setTestWhileIdle(true);
        ds.setTestOnBorrow(false);
        //5分钟检查一次
        ds.setTimeBetweenEvictionRunsMillis(300000);//间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        ds.setMinEvictableIdleTimeMillis(300000);//个连接在池中最小生存的时间

        //------下面这两行至关重要：在连接获取了，但是一直闲置的情况下，超过那个时间，会被回收利用--------------
        //--锁表的时候连接不会被回收，任然显示活跃--------
		/*
		 * 代码未在finally释放connection ,　不过我们都用sqlmapClientTemplate，底层都有链接释放的过程
遇到数据库死锁。以前遇到过后端存储过程做了锁表操作，导致前台集群中连接池全都被block住，后续的业务处理因为拿不到链接所有都处理失败了。
		 */
        ds.setRemoveAbandoned(true);//连接泄漏回收参数，当可用连接数少于3个时才执行
//		ds.setRemoveAbandonedTimeout(300);//回收废弃时间默认300秒,5分钟还在执行的sql就废弃
        ds.setLogAbandoned(true);//出现回收超时连接是不对的！！所以要打印  搜索:AbandonedObjectException
        //--------------------------------

        return new JdbcTemplate(ds);
    }
    //=====================================================================
}

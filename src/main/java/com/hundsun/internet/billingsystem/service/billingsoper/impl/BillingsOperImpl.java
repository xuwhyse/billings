package com.hundsun.internet.billingsystem.service.billingsoper.impl;

import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hundsun.internet.billingsystem.bean.util.MyScoreBean;
import com.hundsun.internet.billingsystem.bean.util.TransferRes;
import com.hundsun.internet.billingsystem.dao.DatumBeanMapper;
import com.hundsun.internet.billingsystem.dao.ScoreBeanMapper;
import com.hundsun.internet.billingsystem.dao.SysAccountBeanMapper;
import com.hundsun.internet.billingsystem.dao.TradeFlowBeanMapper;
import com.hundsun.internet.billingsystem.dao.UserDatumBeanMapper;
import com.hundsun.internet.billingsystem.model.DatumBean;
import com.hundsun.internet.billingsystem.model.DatumBeanCriteria;
import com.hundsun.internet.billingsystem.model.ScoreBean;
import com.hundsun.internet.billingsystem.model.ScoreBeanCriteria;
import com.hundsun.internet.billingsystem.model.TradeFlowBean;
import com.hundsun.internet.billingsystem.model.TradeFlowBeanCriteria;
import com.hundsun.internet.billingsystem.model.TradeFlowBeanCriteria.Criteria;
import com.hundsun.internet.billingsystem.model.UserDatumBean;
import com.hundsun.internet.billingsystem.model.UserDatumBeanCriteria;
import com.hundsun.internet.billingsystem.model.UserDatumBeanKey;
import com.hundsun.internet.billingsystem.service.billingsoper.BillingsOper;
import com.hundsun.internet.billingsystem.service.billingsoper.ScoreMng;

/**
 * 虚拟货币的操作
 * 第三方账户+凭证，做jar是不需要的
 * @author xunmin
 * @createTime 下午2:56:36
 */
@Service
public class BillingsOperImpl implements BillingsOper{
	
	/**
	 * 非线程安全map,同一个账号的操作不能同时进行, 存在key则代表正在操作，不存在则不是操作
	 */
	static Map<String, Integer>  mapLock = new HashMap<String, Integer>();
	static int balanceLock = 1;//计算基准的很小时间内禁止操作流水等
	/**
	 * //1可计算，-1是不可计算
	 */
	static int balanceingLock = 1;
	static int pageNoForCheck = 200;//一次取多少个对象到内存，然后运算完释放
	
	@Autowired
	JdbcTemplate  jdbcTemplate;
	/**
	 * 交易流水表操作
	 */
	@Autowired
	TradeFlowBeanMapper  tradeFlowBeanMapper;
	/**
	 * 用户积分表操作
	 */
	@Autowired
	ScoreBeanMapper  scoreBeanMapper;
	@Autowired
	ScoreMng  ScoreMng;
	/**
	 * 第三方调用用户表
	 */
	@Autowired
	SysAccountBeanMapper  sysAccountBeanMapper;
	/**
	 * 用户基准表
	 */
	@Autowired
	UserDatumBeanMapper  userDatumBeanMapper;
	/**
	 * 基准表
	 */
	@Autowired
	DatumBeanMapper  datumBeanMapper;
	//==================================================

	@Override
	@Transactional(rollbackFor=Exception.class)
	public TransferRes transfer(TransferRes transferRes) {
		//readOnly = true,就不能修改数据库
		if(transferRes==null || transferRes.getListTradeFlowBean()==null)
			return null;
		if(balanceLock<1)
			return null;
		List<TradeFlowBean>  listBeans = transferRes.getListTradeFlowBean();
		int flag = 0;
		String uuid = UUID.randomUUID().toString();
		for(int i=0;i<listBeans.size();i++){
			TradeFlowBean item = listBeans.get(i);
			item.setBatchCode(uuid);
			item.setTradeType(1);
			if(item.getScoreNum()<0 || item.getGoodLuckCoinNum()<0)
				flag = reduceP(item);
			else
				flag = raiseP(item);
			if(flag!=0)
				break;
		}
		transferRes.setFlag(flag);
		if(flag!=0){
			//全部回滚
			transferRes.setMsg("批处理异常");
			RuntimeException e = new RuntimeException(String.valueOf(flag));
			throw e; //这个回滚 
		}else{
			transferRes.setMsg("操作成功");
		}
		return transferRes;
	}
	@Override
	@Transactional
	public int raise(TradeFlowBean bean) {
		if(balanceLock<1)
			return -1;
		return raiseP(bean);
	}
	//为了外面套事务
	private int raiseP(TradeFlowBean bean) {
		String account = bean.getUserId();
		int state = existAccount(account,jdbcTemplate,null);
		//1.检测账号是否存在
		if(state==2){//不存在就创建
			ScoreBean target = new ScoreBean();
			target.setUserId(account);
			target.setGoodLuckCoinNum(0);
			target.setScoreNum(0);
			target.setState(1);
			ScoreMng.saveScore(target, 1);
		}
		//1.5.检测订单是否已经存在
		state = isExistOrderNum(bean);
		if(state!=0)
			return state;
		//3.流水记账
		bean.setCreateDatetime(new Date());
    	bean.setCreator(bean.getSysAccountId());
		tradeFlowBeanMapper.insert(bean);
		
		//4.总额变更,1 正常交易  2.人工冲红修正
		if(bean.getTradeType()==1)
			updateScoreByselect(bean,jdbcTemplate);
		
		return 0;
	}
	/**
	 * 存在返回true,
	 * @author xunmin
	 * @createTime 下午5:43:57
	 * @param account
	 * @return
	 */
	private boolean existAccount(String account) {
		ScoreBeanCriteria example = new ScoreBeanCriteria();
		example.createCriteria().andUserIdEqualTo(account);
		List<ScoreBean> lists = scoreBeanMapper.selectByExample(example);
		if(lists!=null && lists.size()>0)
			return true;
		return false;
	}
	@Override
	@Transactional
	public int reduce(TradeFlowBean bean) {
		if(balanceLock<1)
			return -1;
		return reduceP(bean);
	}
	//为了外面包事务
	private int reduceP(TradeFlowBean bean) {
		String account = bean.getUserId();
		//1.锁住账号操作
		int flag = lockOpt(account);
		if(flag<0){
			return 1;
		}
		try{
			int state = existAccount(account,jdbcTemplate,bean);
			//2.检测账号是否存在
			if(state!=0)
				return state;
			//2.5.检测订单是否已经存在
			state = isExistOrderNum(bean);
			if(state!=0)
				return state;
			//3.流水记账
			bean.setCreateDatetime(new Date());
	    	bean.setCreator(bean.getSysAccountId());
			tradeFlowBeanMapper.insert(bean);
			//4.总额变更,1 正常交易  2.人工冲红修正
			if(bean.getTradeType()==1)
				updateScoreByselect(bean,jdbcTemplate);
		}catch(RuntimeException e){
			throw e;
		}
		finally{
			//5.释放账号锁
			mapLock.remove(account);
		}
		return 0;
	}
	@Override
	public int balance() {
		if(balanceingLock<0)
			return 1;
		balanceingLock = -1;
		
		balanceLock = -1;//锁住不能操作流水
		DatumBean record=null;
		DatumBean lastDatumBean = null;
		try{
			//4.查询基准表，获取最近一次正确合算日;确定合算范围是流水的id之间(如果查不到那今天就是基准日，从0算起)
			lastDatumBean =  getLastCheckRightDatum();//有  可能是第一次，不可能是空
			//1.查询交易流水最大单号
			int maxId = getMaxTradeFlowId();//
			if(maxId==0 || maxId<=lastDatumBean.getMaxFlowId()){
				balanceingLock = 1;//不管出什么错误，解除锁定
				return 0;
			}
			//2.插入基准表，锁定合算流水范围
			record = new DatumBean();
			record.setMaxFlowId(maxId);
			insertDatumBeanForPay(record);
			//3.将用户积分表此时数据全部插入用户基准表，也是锁定合算流水范围
			selectInsertForPay(record,lastDatumBean);
		}catch(Exception e){
			balanceingLock = 1;//不管出什么错误，解除锁定
			return -1;
		}finally{
			balanceLock = 1;//不管出什么错误，解除锁定
		}
		try{
			//5.计算每个用户之前合算日+流水加减的值是否等于当前的各种积分.得出每个用户合算积分是否正确，综合是否正确，
			int flag = checkOnceBillingsByWater(record,lastDatumBean);//checkOnceBillings(record,lastDatumBean);
			//6.并更新
			record.setResult(flag);
			record.setState(1);//已经完成
			Date time = new Date();
			record.setCreateDatetime(time);//最好把合算完成的时间保存进去！！！！！！！！
			datumBeanMapper.updateByPrimaryKeySelective(record);
		}catch(Exception e){
			return -2;
		}finally{
			balanceingLock = 1;//不管出什么错误，解除锁定
		}
		return 0;
	}

	//=============================================
	/**
	 * 在流水范围内，对每笔流水从头再充值一遍，然后一起检查是否对错
	 * @author xunmin
	 * @createTime 下午2:02:03
	 * @param record
	 * @param lastDatumBean
	 * @return 1正确   2存在错误
	 */
	private int checkOnceBillingsByWater(DatumBean record,
			DatumBean lastDatumBean) {
		int flowId1 = lastDatumBean.getMaxFlowId()+1;
		int countTemp = 0;
		int flowId2 = record.getMaxFlowId();//从flowId1流水，计算到flowId2范围
		int range = flowId2-flowId1+1;
		String datumId = record.getDatumId();
		while(countTemp<=range){
			// 每条流水进行二次充值
			List<TradeFlowBean>  listTradeFlows = getListTradeFlows(flowId1,flowId2,countTemp,pageNoForCheck);
			for(int i=0;i<listTradeFlows.size();i++){
				//流水范围内，对本账户的币值进行累加
				TradeFlowBean tradeFlowBean = listTradeFlows.get(i);//这个是需要核算的账户
				updateUserDatumByTradeFlow(datumId,tradeFlowBean);//把流水更新到用户结果中
			}
			countTemp= countTemp+pageNoForCheck;
		}
		int flag = checkAllUserDatum(datumId);
		return flag;
	}
	//1正确   2存在错误
	private int checkAllUserDatum(String datumId) {
		String sql = "SELECT COUNT(*) AS number FROM tb_user_datum WHERE " +
				" datum_id = ? AND" +
				"  (cur_score_num <> ret_score_num OR cur_good_luck_coin_num <> ret_good_luck_coin_num)";
		Object[] values = new Object[] {datumId };
        int[] types = new int[] {Types.VARCHAR};
        Map<String,Object>  map =  jdbcTemplate.queryForMap(sql, values, types);
        int temp = Integer.parseInt(map.get("number").toString());
        if(temp!=0)
        	return 2;
        return 1;
	}
	//根据一条流水，更新合算项的一个结果集
	private int updateUserDatumByTradeFlow(String datum_id, TradeFlowBean tradeFlowBean) {
		String sql = "UPDATE tb_user_datum  SET ret_score_num = ret_score_num+ ?," +
				" ret_good_luck_coin_num = ret_good_luck_coin_num+ ? " +
				" WHERE datum_id = ? " +
				" AND user_id = ?" ;
		int ret_score_num = tradeFlowBean.getScoreNum();
		int ret_good_luck_coin_num = tradeFlowBean.getGoodLuckCoinNum();
		Object[] values = new Object[] {ret_score_num,ret_good_luck_coin_num,datum_id,tradeFlowBean.getUserId() };
        int[] types = new int[] {Types.INTEGER,Types.INTEGER,Types.VARCHAR,Types.VARCHAR};
        return jdbcTemplate.update(sql, values, types);
	}
	/**
	 * //5.计算每个用户之前合算日+流水加减的值是否等于当前的各种积分
	 * 重要. 如果每个账户需要0.5秒*10000 = 1.5个小时
	 * @author xunmin
	 * @createTime 下午4:45:58
	 * @param record
	 * @param lastDatumBean
	 * @return 1正确   2存在错误
	 */
	private int checkOnceBillings(DatumBean record, DatumBean lastDatumBean) {
		//如果是10W个账户，就是每天循环10W次，核对计算相当慢
		int resout = 1; //假设全部正确
		int accountNoumber = getAccountNo();
		int countTemp = 0;
		int flowId1 = lastDatumBean.getMaxFlowId()+1;
		int flowId2 = record.getMaxFlowId();//从flowId1流水，计算到flowId2范围
		while(countTemp<accountNoumber){
			// 账户可能是W等级的所以分页
			List<UserDatumBean>  listUserDatums = getListUserDatums(record,countTemp,pageNoForCheck);
			for(int i=0;i<listUserDatums.size();i++){
				//流水范围内，对本账户的币值进行累加
				UserDatumBean userDabean = listUserDatums.get(i);//这个是需要核算的账户
				String userId = userDabean.getUserId();
				Map<String, Object>  myMap = getCheckedMoney(flowId1,flowId2,userId);
				int total_score_num = (Integer) myMap.get("total_score_num");
				int total_good_luck_coin_num = (Integer) myMap.get("total_good_luck_coin_num");
				UserDatumBean oldOne = getUserDatumsByKey(lastDatumBean.getDatumId(),userId);
				int res_score_num = oldOne.getRetScoreNum()+total_score_num;
				int res_good_luck_coin_num = oldOne.getRetGoodLuckCoinNum()+total_good_luck_coin_num;
				userDabean.setRetGoodLuckCoinNum(res_good_luck_coin_num);
				userDabean.setRetScoreNum(res_score_num);
				//更新到本次账户合算的结果，如果和记录值一样则正确，不一样则，本次大的合算肯定出错
				if(userDabean.getCurGoodLuckCoinNum() !=res_good_luck_coin_num ||  userDabean.getCurScoreNum() !=res_score_num )
					resout = 2; //出现一个错误就说明整体失败
				userDatumBeanMapper.updateByPrimaryKeySelective(userDabean);//将合算结果保存进去
			}
			countTemp = countTemp+pageNoForCheck;
		}
		return resout;
	}
	//==================================================
	//获取offset之后pageNoForCheck条流水范围是t1-t2
	private List<TradeFlowBean> getListTradeFlows(int t1, int t2,int offset,
			int pageNoForCheck){
		TradeFlowBeanCriteria example = new TradeFlowBeanCriteria();
		example.createCriteria().andFlowIdBetween(t1, t2);
		RowBounds rowBounds = new RowBounds(offset, pageNoForCheck);
		return tradeFlowBeanMapper.selectByExampleWithRowbounds(example, rowBounds);
	}
	private UserDatumBean getUserDatumsByKey(String datumId, String userId) {
		UserDatumBeanKey key = new UserDatumBeanKey();
		key.setDatumId(datumId);
		key.setUserId(userId);
		return userDatumBeanMapper.selectByPrimaryKey(key);
	}
	//查询流水号在flowId1和flowId2之内的账号id为userId的两个积分总和
	private Map<String, Object> getCheckedMoney(int flowId1, int flowId2, String userId) {
		String sql = "SELECT SUM(score_num) AS total_score_num,SUM(good_luck_coin_num) AS total_good_luck_coin_num " +
				" FROM tb_trade_flow WHERE user_id ='" +userId +
				"' AND flow_id >= " +flowId1 +" AND flow_id <=" +flowId2 ;
		List<Map<String,Object>>  listMap = jdbcTemplate.queryForList(sql);
		return listMap.get(0);
	}
	/**
	 * 从用户基准表中查出countTemp项，需要合算的账户
	 * @author xunmin
	 * @createTime 上午9:15:08
	 * @param record
	 * @param offSet
	 * @return
	 */
	private List<UserDatumBean> getListUserDatums(DatumBean record,
			int offSet,int pageNo) {
		UserDatumBeanCriteria example = new UserDatumBeanCriteria();
		example.createCriteria().andDatumIdEqualTo(record.getDatumId());
		RowBounds rowBounds = new RowBounds(offSet, pageNo);
		return userDatumBeanMapper.selectByExampleWithRowbounds(example, rowBounds);
	}

	/**
	 * 4.查询基准表，获取最近一次正确合算日;确定合算范围是流水的id之间(如果查不到那今天就是基准日，从0算起)
	 * @author xunmin
	 * @createTime 下午4:24:13
	 * @return
	 */
	private DatumBean getLastCheckRightDatum() {
        DatumBeanCriteria example = new DatumBeanCriteria();
        example.createCriteria().andResultEqualTo(1);
        example.setOrderByClause(" create_datetime DESC ");
        RowBounds rowBounds = new RowBounds(0, 1);
        List<DatumBean>  beans = datumBeanMapper.selectByExampleWithRowbounds(example, rowBounds);
        if(beans==null || beans.size()==0){
        	DatumBean record = new DatumBean();
        	String id = UUID.randomUUID().toString();
    		record.setDatumId(id);
    		Date createDatetime = new Date();
    		record.setCreateDatetime(createDatetime);
    		record.setDatumDate(createDatetime);
    		record.setState(1);
    		record.setResult(1);//正确
    		record.setMaxFlowId(0);//从最初开始计算
        	datumBeanMapper.insert(record);
        	return record;
        }
        else
        	return beans.get(0);
	}
	/**
	 * 插入的同时，把上一次计算正确的结果值直接加到结果中
	 * @author xunmin
	 * @createTime 下午4:50:00
	 * @param record
	 * @param lastDatumBean
	 */
	private void selectInsertForPay(DatumBean record, DatumBean lastDatumBean) {
		String id = record.getDatumId();
		String sql=null;
		//注意，如果为null就用0代替
		sql = "INSERT INTO tb_user_datum SELECT  '" +id+
				"',a.user_id,a.score_num,a.good_luck_coin_num," +
				" IFNULL(b.cur_score_num,0) AS cur_score_num,IFNULL(b.cur_good_luck_coin_num ,0) AS cur_good_luck_coin_num " +
				" FROM tb_score a LEFT JOIN tb_user_datum b ON(a.user_id = b.user_id) " +
				" AND b.datum_id = '" +lastDatumBean.getDatumId() +"'" ;
        jdbcTemplate.execute(sql);
	}
	private void insertDatumBeanForPay(DatumBean record) {
		String id = UUID.randomUUID().toString();
		record.setDatumId(id);
		Date createDatetime = new Date();
		record.setCreateDatetime(createDatetime);
		record.setDatumDate(createDatetime);
		record.setState(0);
		datumBeanMapper.insert(record);
	}
	/**
	 * 流水之后，更新总额
	 * @author xunmin
	 * @createTime 下午2:51:03
	 * @param bean
	 */
	public static void updateScoreByselect(TradeFlowBean bean,JdbcTemplate jdbcTemplate) {
		String sql = "UPDATE tb_score SET score_num = score_num + ?, " +
				" good_luck_coin_num = good_luck_coin_num + ?  WHERE user_id = ? " ;
		Object[] values = new Object[] {bean.getScoreNum(),bean.getGoodLuckCoinNum(),bean.getUserId() };
        int[] types = new int[] {Types.INTEGER,Types.INTEGER,Types.VARCHAR};
        jdbcTemplate.update(sql, values, types);
	}
	/**
	 * 0不存在，可以继续 -21已经存在
	 * @author xunmin
	 * @createTime 下午3:39:48
	 * @param bean
	 * @return
	 */
	private int isExistOrderNum(TradeFlowBean bean) {
		String sql="SELECT * FROM tb_trade_flow WHERE order_num = ?  AND sys_account_id = ?";
		Object[] values = null;
		int[] types = null;
		values = new Object[] {bean.getOrderNum(),bean.getSysAccountId() };
        types = new int[] {Types.VARCHAR,Types.VARCHAR};
        List<Map<String,Object>>  listMap = jdbcTemplate.queryForList(sql, values, types);
        if(listMap==null || listMap.size()==0)
        	return 0;
		return -21;
	}
	/**
	 * 检查账号是否存在
	 * 2欲操作账号不存在,或者账号积分或者红运币不足；3账号冻结 ；4账号删除  
	 * @author xunmin
	 * @createTime 下午4:40:03
	 * @param account
	 * @return 0 成功
	 */
	public static int existAccount(String account,JdbcTemplate jdbcTemplate,TradeFlowBean target) {
		String sql="SELECT * FROM tb_score WHERE user_id = ? ";
		Object[] values = null;
		int[] types = null;
		if(target==null){
			values = new Object[] {account };
	        types = new int[] {Types.VARCHAR};
		}else{
			//减少的话，防止有个积分为负数
			int score_num = Math.abs(target.getScoreNum());
			int good_luck_coin_num = Math.abs(target.getGoodLuckCoinNum());
			sql = sql + " AND score_num >= ?  AND good_luck_coin_num >=?";
			values = new Object[] {account,score_num,good_luck_coin_num };
	        types = new int[] {Types.VARCHAR,Types.INTEGER,Types.INTEGER};
		}
        List<Map<String,Object>>  listMap = jdbcTemplate.queryForList(sql, values, types);
        if(listMap==null || listMap.size()==0)
        	return 2;
        int res = (Integer) listMap.get(0).get("state");//2,冻结3删除
        if(res==1)
        	return 0;
		return res+1;
	}
	private int getMaxTradeFlowId() {
		String sql = "SELECT MAX(flow_id) AS id FROM tb_trade_flow " ;
		List<Map<String,Object>>  listMap = jdbcTemplate.queryForList(sql);
        if(listMap==null || listMap.size()==0)
        	return 0;
        Object ob = listMap.get(0).get("id");
        if(ob==null)
        	return 0;
        int res =  (Integer) ob;
		return res;
	}
	private int getAccountNo() {
		String sql = "SELECT COUNT(*) AS nubmer FROM tb_score" ;
		Map<String, Object>  listMap = jdbcTemplate.queryForMap(sql);
        if(listMap==null)
        	return 0;
        int res =  Integer.parseInt(listMap.get("nubmer").toString());
		return res;
	}
	/**
	 * 局部同步操作
	 * @author xunmin
	 * @createTime 上午10:41:52
	 * @param account
	 * @return
	 */
	synchronized private int lockOpt(String account) {
		if(mapLock.containsKey(account))
			return -1; //账户被其他线程在操作
		mapLock.put(account, 1);//锁住账户
		return 1;//该账户该线程可用
	}
	//===========================================
	public static void main(String[] args) {
//		ExecutorService pool = Executors.newFixedThreadPool(5);
//		final BillingsOperImpl ob = new BillingsOperImpl();
//		
//		Runnable command = new Runnable() {
//			
//			@Override
//			public void run() {
//				BillingsOperBean bean = new BillingsOperBean();
//				bean.setAccount("1");
//				int flag = ob.reduce(bean);
//				System.err.println(Thread.currentThread().getId()+"::"+flag);
//			}
//		};
//		//
//		pool.execute(command);
		
	}
	//============================================
	@Override
	public MyScoreBean queryScores(String userId, Date d1, Date d2,
			int offset, int pageSize) {
		
		List<TradeFlowBean>  myList = null;
		if(pageSize!=0){
			TradeFlowBeanCriteria example = new TradeFlowBeanCriteria();
			Criteria  ca = example.createCriteria().andUserIdEqualTo(userId);
			if(d1!=null && d2!=null)
				ca.andCreateDatetimeBetween(d1, d2);
			example.setOrderByClause(" create_datetime DESC ");
			RowBounds rowBounds = new RowBounds(offset, pageSize);
			myList = tradeFlowBeanMapper.selectByExampleWithRowbounds(example, rowBounds);
		}
		 ScoreBean  myBean = scoreBeanMapper.selectByPrimaryKey(userId);
		 if(myBean==null)
			 return null;
		 MyScoreBean tar = new MyScoreBean();
		 //这里报错了,string 和 date 不能相互转
		 tar.setUserId(myBean.getUserId());
		 tar.setCreateDatetime(myBean.getCreateDatetime());
		 tar.setCreator(myBean.getCreator());
		 tar.setGoodLuckCoinNum(myBean.getGoodLuckCoinNum());
		 tar.setScoreNum(myBean.getScoreNum());
		 tar.setState(myBean.getState());
		 tar.setUpdateDatetime(myBean.getUpdateDatetime());
		 tar.setUpdator(myBean.getUpdator());
//		 try {
//			BeanUtils.copyProperties(tar, myBean);
//		} catch (IllegalAccessException | InvocationTargetException e) {
//			e.printStackTrace();
//		}
		 tar.setListTradeFlows(myList);
		return tar;
	}
	@Override
	public String getRealIdByName(String userId) {
		String sql="SELECT * FROM tb_user WHERE user_name = ?";
		Object[] values = null;
		int[] types = null;
		values = new Object[] {userId};
        types = new int[] {Types.VARCHAR};
        Map<String,Object> map = jdbcTemplate.queryForMap(sql, values, types);
        if(map==null)
        	return null;
		return map.get("user_id").toString();
	}


}

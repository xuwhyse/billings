package com.hundsun.internet.billingsystem.service.billingsoper;

import java.util.List;

import com.hundsun.internet.billingsystem.model.ScoreBean;
import com.hundsun.internet.billingsystem.model.UserDatumBean;

/**
 * 专门用于后台操作的
 * @author xunmin
 * @createTime 上午10:46:33
 */
public interface UserDatumMng {

	int getTotalNumber(String searchname);

	List<UserDatumBean> getInfoByPage(int pageIndex, String searchname);

	/**
	 * 2欲操作账号不存在 ；3账号冻结 ；4账号删除 
	 * @author xunmin
	 * @createTime 下午3:44:54
	 * @param target
	 * @param type 1增加 2修改
	 * @return 1正确
	 */
	int saveScore(ScoreBean target,int type);
	
	ScoreBean getUserScoreById(String id);

	int deleteByKey(String id);

	int getTotalNumber2(String id);

	List<UserDatumBean> getInfoByPage2(int pageIndex, String id);
 
}

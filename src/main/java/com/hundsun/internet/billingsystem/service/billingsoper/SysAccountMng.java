package com.hundsun.internet.billingsystem.service.billingsoper;

import java.util.List;

import com.hundsun.internet.billingsystem.model.SysAccountBean;

/**
 * 专门用于后台操作的
 * @author xunmin
 * @createTime 上午10:46:33
 */
public interface SysAccountMng {

	int getTotalNumber(String searchname);

	List<SysAccountBean> getInfoByPage(int pageIndex, String searchname);

	/**
	 * 2欲操作账号不存在 ；3账号冻结 ；4账号删除 
	 * @author xunmin
	 * @createTime 下午3:44:54
	 * @param target
	 * @param type 1增加 2修改
	 * @return 1正确
	 */
	int saveSysAccount(SysAccountBean target,int type);
	
	SysAccountBean getSysAccountById(String id);

	int deleteByKey(String id);

	/**
	 * 查询所有账户到内存（合作商户，不会很多）
	 * 建立以合作商户账户为key的map
	 * @author xunmin
	 * @createTime 下午10:31:23
	 * @return
	 */
	int updateSysAccounts();
 
}

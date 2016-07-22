package com.billings.billingsystem.bean.util;

import java.io.Serializable;

public class UserBean implements Serializable{

	/**
	 * author:xumin 
	 * 2016-3-30 下午3:50:28
	 */
	private static final long serialVersionUID = 1L;
	private String cnname = "徐岷";
	private String userId = "111";
	private String userName = "admin";
	public String getCnname() {
		return cnname;
	}
	public void setCnname(String cnname) {
		this.cnname = cnname;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}

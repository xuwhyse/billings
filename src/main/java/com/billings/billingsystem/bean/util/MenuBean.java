package com.billings.billingsystem.bean.util;

public class MenuBean {

	private String menuid ;
	private String parentmenuid ;
	private String menuname ;
	private String url ;
	public String getMenuid() {
		return menuid;
	}
	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
	public String getParentmenuid() {
		return parentmenuid;
	}
	public void setParentmenuid(String parentmenuid) {
		this.parentmenuid = parentmenuid;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}

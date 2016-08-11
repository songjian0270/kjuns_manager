package com.kjuns.model;

import java.util.List;

public class Role extends BaseModel{
	
	private String name;
	
	private List<Authority> auths;
	
	// 是否选中
	private boolean selected;
	
	private String[] authIds;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Authority> getAuths() {
		return auths;
	}

	public void setAuths(List<Authority> auths) {
		this.auths = auths;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String[] getAuthIds() {
		return authIds;
	}

	public void setAuthIds(String[] authIds) {
		this.authIds = authIds;
	}

}

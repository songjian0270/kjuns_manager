package com.kjuns.model;

public class Authority extends BaseModel{
	
	private String name;
	
	// 是否选中
	private boolean selected;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}

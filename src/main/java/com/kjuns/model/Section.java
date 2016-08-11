package com.kjuns.model;

public class Section extends Model{
	
	private String title;
	
	private String summary;  //栏目简介
	
	private String background;
	
	private String thumbnail;
	
	private int isTop;
	
	private String issuers;
	
	private String issuersName;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public int getIsTop() {
		return isTop;
	}

	public void setIsTop(int isTop) {
		this.isTop = isTop;
	}

	public String getIssuers() {
		return issuers;
	}

	public void setIssuers(String issuers) {
		this.issuers = issuers;
	}

	public String getIssuersName() {
		return issuersName;
	}

	public void setIssuersName(String issuersName) {
		this.issuersName = issuersName;
	}
	
}

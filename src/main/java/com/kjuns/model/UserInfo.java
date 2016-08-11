package com.kjuns.model;

public class UserInfo extends Model{
	
	private String accountId;
	
	private String nickName;
	
	private String mobilePhone;
	
	private String faceSrc;
	
	private String sex;
	
	private Integer automaticThoughts;
	
	private String location;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getFaceSrc() {
		return faceSrc;
	}

	public void setFaceSrc(String faceSrc) {
		this.faceSrc = faceSrc;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getAutomaticThoughts() {
		return automaticThoughts;
	}

	public void setAutomaticThoughts(Integer automaticThoughts) {
		this.automaticThoughts = automaticThoughts;
	}

}

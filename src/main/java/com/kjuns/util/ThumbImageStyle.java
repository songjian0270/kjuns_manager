package com.kjuns.util;

public class ThumbImageStyle {

	public static final String THUMB_640SIZE = "thumb640"; 
	public static final String THUMB_480SIZE = "thumb480"; 
	public static final String THUMB_320SIZE = "thumb320"; 
	public static final String THUMB_160SIZE = "thumb160"; 
	public static final String FACE_50SIZE = "face50"; 
	public static final String FACE_100SIZE = "face100"; 
	public static final String FACE_320SIZE = "face320"; 
	
	
	/**
	 * 将图片地址添加上七牛样式
	 * @param url 原始url
	 * @param style 样式名
	 * @return
	 */
	public static String thumbUrl(String url,String style){
		if(CommonUtils.notEmpty(url)){
			StringBuffer buffer = new StringBuffer(url);
			if(url.indexOf("meitudata")<0 && url.indexOf("qzapp")<0 
					&& url.indexOf("sinaimg")<0 && url.indexOf("!")<0
					&& url.indexOf("qlogo")<0){
				buffer.append("!");
				buffer.append(style);
			}
			return buffer.toString();
		}
		return url;
	}
}

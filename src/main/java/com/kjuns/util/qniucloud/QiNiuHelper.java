package com.kjuns.util.qniucloud;

import com.kjuns.util.SysConf;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

public class QiNiuHelper {

	private Auth auth;
	
	public static final String PIC_TOKEN = "pic";
	
	public static final String VIDEO_TOKEN = "video";
	
	// 创建上传对象
	private UploadManager uploadManager = new UploadManager();

	QiNiuHelper() {
		// 密钥配置
		auth = Auth.create(SysConf.QN_ACCESS_KEY, SysConf.QN_SECRET_KEY);
	}

	private static QiNiuHelper instance = null;

	public static QiNiuHelper getInstance() throws Exception {
		if (instance == null) {
			synchronized (QiNiuHelper.class) {
				if (instance == null) {
					instance = new QiNiuHelper();
				}
			}
		}
		return instance;
	}
	
	//简单上传，使用默认策略，只需要设置上传的空间名就可以了
	public String getUpToken() {
		return auth.uploadToken(SysConf.QN_BUCKET_NAME);
	}
	
	//<bucket>:<key>，表示只允许用户上传指定key的文件。在这种格式下文件默认允许“修改”，已存在同名资源则会被本次覆盖。
	private String getUpToken(String key) {
		return auth.uploadToken(SysConf.QN_BUCKET_NAME, key);
	}
	
	 //设置callbackUrl以及callbackBody，七牛将文件名和文件大小回调给业务服务器
	private String getBreakpointUpToken(){
		return auth.uploadToken(SysConf.QN_BUCKET_NAME, null, 3600, new StringMap()
	         .put("callbackUrl","http://your.domain.com/callback")
	         .put("callbackBody", "filename=$(fname)&filesize=$(fsize)"));
	}

	/**
	 * 简单上传
	 * @param filePath
	 * @param key
	 * @return
	 */
	public StringMap upload(String filePath, String key) {
		try {
			Response res = uploadManager.put(filePath, key, getUpToken());
			return res.jsonToMap();
		} catch (QiniuException e) {
			Response r = e.response;
			try {
				// 响应的文本信息
				System.out.println(r.bodyString());
			} catch (QiniuException e1) {
				// ignore
			}
		}
		return null;
	}
	
	/**
	 * 覆盖上传
	 * @param filePath
	 * @param key
	 * @return
	 */
	public StringMap coverUpload(String filePath, String key) {
		try {
			Response res = uploadManager.put(filePath, key, getUpToken(key));
			return res.jsonToMap();
		} catch (QiniuException e) {
			Response r = e.response;
			try {
				// 响应的文本信息
				System.out.println(r.bodyString());
			} catch (QiniuException e1) {
				// ignore
			}
		}
		return null;
	}
	
	/**
	 * 断点上传
	 * @param filePath
	 * @param key
	 * @return
	 */
	public StringMap breakpointUpload(String filePath, String key) {
		try {
			Response res = uploadManager.put(filePath, key, getBreakpointUpToken());
			return res.jsonToMap();
		} catch (QiniuException e) {
			Response r = e.response;
			try {
				// 响应的文本信息
				System.out.println(r.bodyString());
			} catch (QiniuException e1) {
				// ignore
			}
		}
		return null;
	}
	
}

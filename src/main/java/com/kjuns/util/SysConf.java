package com.kjuns.util; 

import java.util.Locale;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * <b>Function: </b> 常量配置
 * @author James
 * @date 2015-07-28
 * @file SysConf.java
 * @package com.kjuns.util
 * @project kjuns
 * @version 2.0
 */
public class SysConf {
	
	private static Logger logger = LoggerFactory.getLogger(SysConf.class);
	
	private static final String CONF = "conf";
	
	private static final String JDBC = "jdbc";
	
	private static final String JPUSH = "jpush";

	/** 1:正式环境，2：测试环境   */
	public static final boolean ENV = getEnv();
	
	//===================== 日志文件相关   ============================================================
	/** 日志文件目录  */
	public static final String LOG_PATH = SysConf.getBundleString(CONF, "logPath");
	/** 日志文件目录文件数量  */
	public static final Integer LOG_FILE_CNT = getLogFileCnt(); 
	
	//===================== 备用域名设置   ============================================================
/*	*//** 备用主域名  *//*
	public static final String BACK_MASTER_SERVER_URL = SysConf.getBundleString(CONF, "back_master_server_url");
	*//** 备用搜索域名 *//*
	public static final String BACK_SEARCH_SERVER_URL = SysConf.getBundleString(CONF, "back_search_server_url");
	public static final String SIMULATION = SysConf.getBundleString(CONF, "simulation");*/

	//===================== jdbc设置   ============================================================
	/** jdbc.url  */
	public static final String JDBC_URL = SysConf.getBundleString(JDBC, "user.db.url");
	/** jdbc.username  */
	public static final String JDBC_USERNAME = SysConf.getBundleString(JDBC, "user.db.user");
	/** jdbc.password  */
	public static final String JDBC_PWD = SysConf.getBundleString(JDBC, "user.db.password");
	
	//=====================  支付   ============================================================
//	/** 是否启用微信支付  */
//	public static final String WX_PAYMENT_ENABLE = SysConf.getBundleString(CONF, "wx_payment_enable"); 
//	/** 支付回调接口地址  */
//	public static final String WX_PAYMENT_NOTIFICATION = SysConf.getBundleString(CONF, "wx_payment_notification"); 
//	/** 客户端轮询时间设置 */
//	public static final Integer UN_READ_POLLINGTIME_INTERVAL =SysConf.getBundleInteger(CONF, "unReadPollingTimeInterval"); 
//	/** 交易兑现额度 */
//	public static final Integer WX_PAYMENT_RATIO =SysConf.getBundleInteger(CONF, "wx_payment_ratio"); 
	
	//===================== 青牛   ============================================================
	public static final String QN_BUCKET_URL = SysConf.getBundleString(CONF, "qn_bucket_url"); 
	public static final String QN_BUCKET_NAME = SysConf.getBundleString(CONF, "qn_bucket_name");
	public static final String QN_ACCESS_KEY = SysConf.getBundleString(CONF, "qn_access_key");
	public static final String QN_SECRET_KEY = SysConf.getBundleString(CONF, "qn_secret_key");
	
	//==================== 
	public static final String PLL_BASE_URL = SysConf.getBundleString(CONF, "pll_base_url");
	public static final String PLL_ACCESS_KEY = SysConf.getBundleString(CONF, "pll_access_key");
	public static final String PLL_SECRET_KEY = SysConf.getBundleString(CONF, "pll_secret_key");
	
	//===================== 极光推送   ============================================================
	public static final String JPUSH_KEY = SysConf.getBundleString(JPUSH, "jpush_key"); 
	public static final String JPUSH_SECRET = SysConf.getBundleString(JPUSH, "jpush_secret");
	
	//===================== 云通讯   ============================================================
/*	public static final String Y_SERVER_URL = SysConf.getBundleString(YUNTONGXUN, "server_url"); 
	public static final String Y_SERVER_PORT = SysConf.getBundleString(YUNTONGXUN, "server_port");
	public static final String Y_ACCOUNT_SID = SysConf.getBundleString(YUNTONGXUN, "account_sid");
	public static final String Y_ACCOUNT_TOKEN = SysConf.getBundleString(YUNTONGXUN, "account_token");
	public static final String Y_APP_ID = SysConf.getBundleString(YUNTONGXUN, "app_id");
	public static final String Y_SMS_TEMPLATE = SysConf.getBundleString(YUNTONGXUN, "sms_template");
	public static final String Y_ISMS360_TEMPLATE = SysConf.getBundleString(YUNTONGXUN, "isms360_template");
	
	public static final String Y_API_KEY = SysConf.getBundleString(YUNTONGXUN, "api_key"); 
	public static final String Y_GETUSERINFO_URL = SysConf.getBundleString(YUNTONGXUN, "getUserInfo_url"); 
	public static final String Y_SENDSMS_URL = SysConf.getBundleString(YUNTONGXUN, "sendSms_url"); 
	public static final String Y_TPLSENDSMS_URL = SysConf.getBundleString(YUNTONGXUN, "tplSendSms_url"); */
	
	private SysConf(){}//私有构造函数（不允许实例化）
	
	/**
	 * 取得日志文件应保留文件数
	 * @return
	 */
	private static Integer getLogFileCnt(){
		String logFileCntStr=SysConf.getBundleString(CONF, "logFileCnt"); 
		Integer logFileCnt=null;
		try {
			logFileCnt = Integer.valueOf(logFileCntStr);
		} catch (NumberFormatException e) {
			logger.error("取得日志文件应保留文件数出错:",e);
		}
		return logFileCnt;
	}
	
	//======================= private method ==========================================

	/**
	 * 获取环境配置参数
	 * @return
	 */
	private static boolean getEnv(){
		try {
			String env = getBundleString(CONF,"env");//ResourceBundle.getBundle(BUNDLE_NAME, Locale.CHINA).getString("env");
			logger.info("SysConf.env={}",env);
			if(StringUtils.hasText(env)){
				if(!env.matches("[1-3]")){
					logger.error("环境参数“env”配置错误，使用默认配置（1）");
					return false;
				}else if(env.equals("1")){
					return true;
				}else{
					return false;
				}
			}else{
				logger.error("没有配置环境参数“env”配置参数出错，使用默认配置（1）");
				return false;
			}
		} catch (Exception e) {
			logger.error("获取环境配置参数“env”出错，使用默认配置（1）", e);
			return false;
		}
	}
	
/*	private static Boolean getBundleBoolean(String propertiesName, String key) {
		String str = getBundleString(propertiesName, key);
		Boolean  boVal = null;
		if (StringUtils.hasText(str)) {
			try {
				boVal = Boolean.valueOf(key);
			} catch (NumberFormatException e) {
				logger.error("相关参数配置错误：“{}”，将使用默认配置", str);
			}
		}
		return boVal;
	}*/
	
	/*private static Double getBundleDouble(String propertiesName, String key) {
		String str = getBundleString(propertiesName, key);
		Double doubleVal = null;
		if (StringUtils.hasText(str)) {
			try {
				doubleVal = Double.valueOf(str);
			} catch (NumberFormatException e) {
				logger.error("相关参数配置错误：“{}”，将使用默认配置", str);
			}
		}
		return doubleVal;
	}
	
	private static Integer getBundleInteger(String propertiesName, String key) {
		String str = getBundleString(propertiesName, key);
		Integer intVal = null;
		if (StringUtils.hasText(str)) {
			try {
				intVal = Integer.valueOf(str);
			} catch (NumberFormatException e) {
				logger.error("相关参数配置错误：“{}”，将使用默认配置", str);
			}
		}
		return intVal;
	}*/
	
/*	private static Long getBundleLong(String propertiesName, String key) {
		String str = getBundleString(propertiesName, key);
		Long longVal = null;
		if (StringUtils.hasText(str)) {
			try {
				longVal = Long.valueOf(str);
			} catch (NumberFormatException e) {
				logger.error("相关参数配置错误：“{}”，将使用默认配置", str);
			}
		}
		return longVal;
	}
	*/
	private static String getBundleString(String propertiesName, String key){
		return ResourceBundle.getBundle(propertiesName, Locale.CHINA).getString(key);
	}

}
 
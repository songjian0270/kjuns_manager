package com.kjuns.util;

import java.text.SimpleDateFormat;

public class CommonConstants {
	
	// ==================================== 正则 ==========================================
	/** Email地址正则表达式 */
	public static final String REGULAR_EMAIL = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
	/** 用户名校验的正则表达式(字母、数字、下划线、中文) */
	public static final String REGULAR_USER_NAME = "^[a-zA-Z0-9_\u4e00-\u9fa5]+$";
	/** 格式(id为数字)：“id1,id2,id3” */
	public static final String REGULAR_IDS_FORMAT = "\\d+(,\\d+)*";
	/** 数字 */
	public static final String REGULAR_NUMERAL = "\\d+";

	// ==================================== date & time format ====================================
	/** 日期/时间格式 "yyyy-MM-dd HH:mm:ss,SSS" */
	public static final SimpleDateFormat DATETIME_FULL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	/** 日期/时间格式 "yyyyMMddHHmmssSSS" */
	public static final SimpleDateFormat DATETIME_FULL_STR = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	/** 日期/时间格式 "yyyy-MM-dd HH:mm:ss" */
	public static final SimpleDateFormat DATETIME_SEC = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/** 日期/时间格式 "yyyyMMddHHmmss" */
	public static final SimpleDateFormat DATETIME_SEC_STR = new SimpleDateFormat("yyyyMMddHHmmss");
	/** 日期/时间格式 "yyyy-MM-dd" */
	public static final SimpleDateFormat DATE = new SimpleDateFormat("yyyy-MM-dd");
	/** 日期/时间格式 "yyyy" */
	public static final SimpleDateFormat DATE_YEAR = new SimpleDateFormat("yyyy");
	/** 日期/时间格式 "yyyyMM" */
	public static final SimpleDateFormat DATE_YEAR_MONTH = new SimpleDateFormat("yyyyMM");
	/** 日期/时间格式 "yyyyMMdd" */
	public static final SimpleDateFormat DATE_STR = new SimpleDateFormat("yyyyMMdd");
	/** 日期/时间格式 "yyMMdd" */
	public static final SimpleDateFormat DATE_SHORT_STR = new SimpleDateFormat("yyMMdd");
	/** 日期/时间格式 "yyMMdd" */
	public static final SimpleDateFormat DATE_SHORT_YEAR_MONTH = new SimpleDateFormat("yyMM");
	/** 日期/时间格式 "MMdd" */
	public static final SimpleDateFormat DATE_NO_YEAR = new SimpleDateFormat("MMdd");
	/** 获得月份 "MM" */
	public static final SimpleDateFormat DATE_NO_MONTH = new SimpleDateFormat("MM");
	/** 获得天 "dd" */
	public static final SimpleDateFormat DATE_NO_DAY = new SimpleDateFormat("dd");
	/** 日期/时间格式 "HHmmss" */
	public static final SimpleDateFormat TIME_SEC_STR = new SimpleDateFormat("HHmmss");
	/** 日期/时间格式 "HH:mm:ss" */
	public static final SimpleDateFormat TIME_SEC = new SimpleDateFormat("HH:mm:ss");
	/** 日期格式 "yyyy/MM/dd" */
	public static final SimpleDateFormat DATE_INSUR = new SimpleDateFormat("yyyy/MM/dd");
	/** 日期格式 "MM月dd日HH时mm分" */
	public static final SimpleDateFormat DATE_FULL_CHN = new SimpleDateFormat("MM月dd日HH时mm分");
	/** 日期格式 "MM月dd日 HH:mm" */
	public static final SimpleDateFormat DATE_WED_RENT_FULL_CHN = new SimpleDateFormat("MM月dd日 HH:mm");
	/** 日期格式 "HH:mm" */
	public static final SimpleDateFormat DATE_WED_REVERT_FULL_CHN = new SimpleDateFormat("HH:mm");
	
	//1:formal 0:test
	// ==================================== 环境   ====================================
	public static final Integer TEST = 0;
	public static final Integer FORMAL = 1;
	
	public static final String SERVICETYPE_CONTENT_VIDEO = "011";		//资讯
	public static final String SERVICETYPE_CONTENT_IMG = "012";			//栏目
	public static final String SERVICETYPE_CONTENT_AUDIO = "013";		//视频
	public static final String SERVICETYPE_CONTENT_OTHER = "014";		//图片
	
	
	// ==================================== 信息提示模板  ====================================
	public static final String PAY_MSG_CONTENT = "充值的{content}爱豆已到达您的账户";
	public static final String APPLY_MSG_CONTENT = "恭喜你，你已成功申请练习生";
	
	// =========== 复合ID长度 ================
	public static final Integer FH_ID_LEN = 27;
	
	// =========== 分享常量 =============
	public static final String QQ = "QQ";
	public static final String WX = "WX";
	public static final String WB = "WB";

}

package com.kjuns.util;

import java.util.UUID;

/**
 * <b>Function: </b> 36wei
 * @author James
 * @date 2015-08-13
 * @file UtilTool.java
 * @package com.kjuns.util
 * @project kjuns
 * @version 2.0
 */
public class UUIDUtils {

	/**
	 * 获取36位唯一字符串，用作表的主键
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}
	
}


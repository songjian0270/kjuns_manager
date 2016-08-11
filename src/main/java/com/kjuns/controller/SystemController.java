package com.kjuns.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kjuns.util.qniucloud.QiNiuHelper;

/**
 * <b>Function: </b>  系统 
 * @author James
 * @date 2015-12-14
 * @file SystemController.java
 * @package com.kjuns.controller
 * @project kjuns
 * @version 2.0
 */
@Controller
@RequestMapping("/system")
public class SystemController extends BaseController {
	
	/**
	 * 获取青牛token
	 * @param model
	 */
	@RequestMapping(value = "/requestUploadToken")
	public void requestUploadToken(Model model,HttpServletRequest request, HttpSession session) throws Exception{
		try {
			String token = QiNiuHelper.getInstance().getUpToken();
 			sendResponseContent(model, "uptoken", token);
		} catch (Exception ex) {
			logger.error("requestUploadToken >>> {}", ex.getMessage());
			throw ex;
		}
	}
	
}

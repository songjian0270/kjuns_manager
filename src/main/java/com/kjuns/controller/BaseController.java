package com.kjuns.controller; 

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.kjuns.model.Admin;
import com.kjuns.out.BaseOutJB;
import com.kjuns.util.ErrorCode;

/**
 * <b>Function: </b>     
 * @author James
 * @date 2015-07-28
 * @file BaseController.java
 * @package com.kjuns.controller
 * @project kjuns
 * @version 2.0
 */
public class BaseController {

	public Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@SuppressWarnings("unchecked")
	protected Map<String,Object> getContent(HttpServletRequest request){
		return (Map<String, Object>) request.getAttribute("content");
	}
	
	protected Admin getContent(HttpSession session){
		return (Admin) session.getAttribute("loginAdmin");
	}
	
	protected void sendResponseContent(Model model, BaseOutJB out){
		model.asMap().clear();
		model.addAttribute(out);
	}
	
	protected void sendResponseContent(Model model, Object data){
		model.asMap().clear();
		model.addAttribute(data);
	}
	
	protected void sendResponseContent(Model model, String key, Object val){
		model.asMap().clear();
		model.addAttribute(key, val);
	}
	
	protected void sendResponseContent(Model model, ErrorCode error){
		model.asMap().clear();
		BaseOutJB out = new BaseOutJB(error);
		model.addAttribute(out);
	}
	
	protected void sendResponseContent(Model model, ErrorCode error, Object data){
		model.asMap().clear();
		BaseOutJB out = null;
		if(data instanceof String || data instanceof Long || data instanceof Boolean
				|| data instanceof Integer){
			Map<String, Object> params = new HashMap<>();
			params.put("message", data);
			out = new BaseOutJB(error, params);
		}else{
			out = new BaseOutJB(error, data);
		}
		model.addAttribute(out);
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    //true:允许输入空值，false:不能为空值
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
}

 
package com.kjuns.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <b>Function: </b>  异常处理
 * @author James
 * @date 2015-8-31
 * @file MealController.java
 * @package com.kjuns.controller
 * @project kjuns
 * @version 2.0
 */
@Controller
@RequestMapping("/exception")
public class ExceptionController extends BaseController{
	
	@RequestMapping("reminder")
	public String reminder(String msg, String type, Model model){
		if(type.equals("authorize")){
			return "login";
		}else{
			model.addAttribute("message", msg);   
			return "error";
		}
	}

}

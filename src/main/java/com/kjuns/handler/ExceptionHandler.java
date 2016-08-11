package com.kjuns.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.kjuns.controller.BaseController;
import com.kjuns.exception.AuthorizeException;
import com.kjuns.exception.SMSException;
import com.kjuns.exception.TokenInvalidException;
import com.kjuns.util.CommonUtils;

public class ExceptionHandler extends BaseController implements
		HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {

		String msg = "";
		String type = "";
		if (CommonUtils.notEmpty(ex.getMessage())) {
			msg = ex.getMessage();
		}
		if (ex instanceof TokenInvalidException) {
			type = "tokenInvalid";
			return new ModelAndView("redirect:/exception/reminder?msg=" + msg + "&type=" + type);
		} else if (ex instanceof AuthorizeException) {
			type = "authorize";
			return new ModelAndView("redirect:/exception/reminder?msg=" + msg + "&type=" + type);
		} else if (ex instanceof SMSException) {
			type = "sms";
			return new ModelAndView("redirect:/exception/reminder?msg=" + msg + "&type=" + type);
		} else {
			return new ModelAndView("redirect:/exception/reminder?msg=" + msg + "&type=");
		}

	}

}

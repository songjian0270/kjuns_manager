package com.kjuns.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.kjuns.annotation.VerifyToken;
import com.kjuns.controller.BaseController;

@Aspect
@Component
public class VerifyTokenAspect extends BaseController {

//	private static final Logger logger = LoggerFactory.getLogger("VerifyTokenAspect");
	 
	@Before("within(@org.springframework.stereotype.Controller *) && @annotation(verifyToken)")
	public void verifyToken(final JoinPoint joinPoint, VerifyToken verifyToken) throws Exception {
		Object[] args = joinPoint.getArgs();
		HttpServletRequest request = null;
		for (int i = 0; i < args.length; i++) {
			if (args[i] instanceof HttpServletRequest) {
				request = (HttpServletRequest) args[i];
				break;
			}
		}
		if (request == null) {
			throw new Exception("方法中缺失HttpServletRequest参数");
		}
	//	String token = request.getParameter("token");

	
		/*	logger.error("token已失效，请重新登陆...");
			throw new TokenInvalidException();*/
		
	}
}

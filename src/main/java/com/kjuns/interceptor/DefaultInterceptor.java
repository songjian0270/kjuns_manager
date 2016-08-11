package com.kjuns.interceptor; 

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kjuns.annotation.Verify;
import com.kjuns.exception.AuthorizeException;
import com.kjuns.interceptor.base.BaseInterceptor;
import com.kjuns.util.CommonUtils;
import com.kjuns.util.ErrorCode;
import com.kjuns.validation.FieldValiRule;
import com.kjuns.validation.ValidationUrlMapping;
import com.kjuns.validation.Validator;

/**
 * <b>Function: </b>     
 * @author James
 * @date 2015-07-28
 * @file DefaultInterceptor.java
 * @package com.kjuns.controller
 * @project kjuns
 * @version 2.0
 */
public class DefaultInterceptor extends BaseInterceptor {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
		
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		request.setCharacterEncoding("utf-8");
		String reqMethod = request.getMethod();
		HandlerMethod hm = null;
		//输入校验
		LinkedHashMap<String, Object> data = null;
		
		if("POST".equals(reqMethod) || "PUT".equals(reqMethod) 
				|| "DELETE".equals(reqMethod) || "GET".equals(reqMethod)){
			try {
				if (handler instanceof HandlerMethod) {
					hm = (HandlerMethod) handler;
				} else {
					return true;
				}
			} catch (Exception e) {
				throw new AuthorizeException("404 访问地址不存在"); 
			}  
		   	String valiKey = hm.getBeanType().getName() + "." + hm.getMethod().getName();
		   	data = handleRequestDate(request);
			if(hm.getMethodAnnotation(Verify.class) != null){
				if(!verify(request, response, valiKey, data)){
			   		return false;
			   	}	
			}			
			logger.info("********************************");
			//logger.info("SysConf.AOTU_CALL_IP_VATE:"+SysConf.AOTU_CALL_IP_VATE);
			logger.info("ApiUtils.getIpAddr(request):"+CommonUtils.getIpAddr(request));
			logger.info("********************************");
			request.setAttribute("content", data);
		}
		return super.preHandle(request, response, handler);
	}
		
	private boolean verify(HttpServletRequest request, HttpServletResponse response, String valiKey, LinkedHashMap<String, Object> data) throws Exception{
		String msg = null;
		FieldValiRule[] fvr = ValidationUrlMapping.urlValiMapping.get(valiKey);
		if (fvr != null) {
			if ((msg = Validator.validateDataMap(data, fvr)) != null) {
				logger.error("输入错误：{}",msg);
				sendResponseContent(request, response, ErrorCode.PARAM_ERROR, msg);
				return false;	
			}
		} else {
			logger.error("FieldValiRule is null !");
			sendResponseContent(request, response, ErrorCode.SYS_ERROR);
			return false;
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	private LinkedHashMap<String,Object> handleRequestDate(HttpServletRequest request) throws UnsupportedEncodingException{
		LinkedHashMap<String,Object> data = new LinkedHashMap<>();//有序的map，顺序同validation的xml文件中配置的字段顺序，即字段校验顺序
		Enumeration<String> pNames = request.getParameterNames();
		while(pNames.hasMoreElements()){
			String fieldName = pNames.nextElement();
			String fieldVal = request.getParameter(fieldName);
			data.put(fieldName, fieldVal);
		}
		return data;
	}
	
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,
			ModelAndView model) throws Exception {
		if(model != null){
			String path = request.getContextPath(); 
			String basePath = request.getScheme()+"://"+request.getServerName() +":"+request.getServerPort()+path; 
			model.addObject("basePath", basePath);	
		}
	}
	
	public void afterCompletion(    
	    HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)    
	       throws Exception { 
		logger.info("afterCompletion执行完整......");
	}    
	
}
 
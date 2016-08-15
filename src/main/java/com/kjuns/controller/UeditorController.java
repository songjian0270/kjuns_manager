package com.kjuns.controller;

import java.io.File;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.ueditor.ActionEnter;
import com.kjuns.model.Admin;
import com.kjuns.util.CommonConstants;
import com.kjuns.util.qniucloud.QiNiuHelper;

/**
 * <b>Function: </b>  上传
 * @author James
 * @date 2016-07-21
 * @file UeditorController.java
 * @package com.kjuns.controller
 * @project kjuns
 * @version 2.0
 */
@Controller
@RequestMapping("/ueditor")
public class UeditorController extends BaseController {
	
	@RequestMapping(value = "/index")
	public String index() throws Exception{
		return "news/index";
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/config")
	public void name(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		response.setHeader("Content-Type", "text/html");
		String rootPath = request.getRealPath("/") ;
		String action = request.getParameter("action");
		String result = new ActionEnter( request, rootPath ).exec();
		if( action!=null && 
				   (action.equals("listfile") || action.equals("listimage") ) ){
			rootPath = rootPath.replace("\\", "/");
			result = result.replaceAll(rootPath, "");
		}
		response.getWriter().write(result);
	}
	
	/**
	 * 本服务器上传
	 * @param multipartFile
	 * @param model
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadImg")
	public Model uploadImg(@RequestParam("upfile")MultipartFile multipartFile, Model model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		Admin admin = getContent(session);
		Random random =new Random();
		String date = CommonConstants.DATE_STR.format(new Date());
		String path = request.getSession().getServletContext().getRealPath("ueditor/upload") + "/image/" + date + "/";
		String fileName = multipartFile.getOriginalFilename();
		String extName = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
		fileName =  CommonConstants.DATETIME_SEC_STR.format(new Date()) + random.nextInt(10000) + extName;
		String state = "";
		File targetFile = new File(path, fileName);
		if(!targetFile.exists()){
			targetFile.mkdirs();
		}
		String qnpath = date + "/image/" + admin.getId()  + "/" + fileName;
		try {
			multipartFile.transferTo(targetFile);
			QiNiuHelper.getInstance().coverUpload(path+"/"+fileName,  qnpath);
			targetFile.delete();
			state = "SUCCESS";
		} catch (Exception ex) {
			logger.error("uploadImg >>> {}", ex.getMessage());
			state = "FAIL";
		}
		
		model.addAttribute("original", fileName);
		model.addAttribute("url", qnpath);
		model.addAttribute("title", "");
		model.addAttribute("state", state);
 		return model;
	}
	
	/**
	 * 本服务器上传
	 * @param multipartFile
	 * @param model
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadVideo")
	public Model uploadVideo(@RequestParam("upfile")MultipartFile multipartFile, Model model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		Admin admin = getContent(session);
		Random random =new Random();
		String date = CommonConstants.DATE_STR.format(new Date());
		String path = request.getSession().getServletContext().getRealPath("ueditor/upload") + "/video/" + date + "/";
		String fileName = multipartFile.getOriginalFilename();
		String extName = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
		fileName =  CommonConstants.DATETIME_SEC_STR.format(new Date()) + random.nextInt(10000) + extName;
		String state = "";
		File targetFile = new File(path, fileName);
		if(!targetFile.exists()){
			targetFile.mkdirs();
		}
		String qnpath = date + "/video/" + admin.getId()  + "/" + fileName;
		try {
			multipartFile.transferTo(targetFile);
			QiNiuHelper.getInstance().coverUpload(path+"/"+fileName,  qnpath);
			targetFile.delete();
			state = "SUCCESS";
		} catch (Exception ex) {
			logger.error("uploadImg >>> {}", ex.getMessage());
			state = "FAIL";
		}
		
		model.addAttribute("original", fileName);
		model.addAttribute("url", qnpath);
		model.addAttribute("title", "");
		model.addAttribute("state", state);
 		return model;
	}
	
	/**
	 * 本服务器上传
	 * @param multipartFile
	 * @param model
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadScrawl")
	public Model uploadScrawl(@RequestParam("upfile")MultipartFile multipartFile, Model model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		Admin admin = getContent(session);
		Random random =new Random();
		String date = CommonConstants.DATE_STR.format(new Date());
		String path = request.getSession().getServletContext().getRealPath("ueditor/upload") + "/scrawl/" + date + "/";
		String fileName = multipartFile.getOriginalFilename();
		String extName = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
		fileName =  CommonConstants.DATETIME_SEC_STR.format(new Date()) + random.nextInt(10000) + extName;
		String state = "";
		File targetFile = new File(path, fileName);
		if(!targetFile.exists()){
			targetFile.mkdirs();
		}
		String qnpath = date + "/scrawl/" + admin.getId()  + "/" + fileName;
		try {
			multipartFile.transferTo(targetFile);
			QiNiuHelper.getInstance().coverUpload(path+"/"+fileName,  qnpath);
			targetFile.delete();
			state = "SUCCESS";
		} catch (Exception ex) {
			logger.error("uploadScrawl >>> {}", ex.getMessage());
			state = "FAIL";
		}
		
		model.addAttribute("original", fileName);
		model.addAttribute("url", qnpath);
		model.addAttribute("title", "");
		model.addAttribute("state", state);
 		return model;
	}
	
}

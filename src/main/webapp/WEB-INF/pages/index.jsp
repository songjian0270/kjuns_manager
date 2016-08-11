<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>

</head>


<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li>首页</li>
		</ul>
	</div>

	<div class="mainindex">
		<div class="welinfo">
			<b>${loginAdmin.username}您好，欢迎使用看军事后台管理系统。</b>
		</div>
		<div class="xline"></div>
		<div class="box"></div>
		<img src="images/index-banner.jpg" />
		<ul class="infolist">
			<li><span></span></li>
		</ul>
	</div>

</body>

</html>

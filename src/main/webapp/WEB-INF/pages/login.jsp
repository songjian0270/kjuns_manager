<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>欢迎登录后台管理系统</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>


<script language="javascript">
	if("${message}"!=""){
		alert("${message}")
	}
	$(function() {
		$('.loginbox').css({
			'position' : 'absolute',
			'left' : ($(window).width() - 692) / 2
		});
		$(window).resize(function() {
			$('.loginbox').css({
				'position' : 'absolute',
				'left' : ($(window).width() - 692) / 2
			});
		});
	});
	function onLogin() {
		var username = $("#username").val();
		var password = $("#password").val();
		if (username != "" && password != "") {
			return true;
		}
		alert("请输入用户名和密码");
		return false;
	}

	// 登录页面若在框架内，则跳出框架
	if (self != top) {
		top.location = self.location;
	}

	if ("${error}" == "true"){
		alert("用户名或密码错误");
	}
</script>

</head>

<body style="background-color: #1c77ac;">


	<div class="logintop" >
		<span>欢迎登录后台管理界面平台</span>
		<ul>
			<li><a href="#">回首页</a></li>
			<li><a href="#">帮助</a></li>
			<li><a href="#">关于</a></li>
		</ul>
	</div>

	<div class="loginbody">

		<span class="systemlogo"></span>
		<form action="admin/login" method="post">
			<div class="loginbox">
				<ul>
					<li><input name="username" id="username" type="text"
						class="loginuser" value="${username}" /></li>
					<li><input name="password" id="password" type="password"
						class="loginpwd" /></li>
					<li><input name="" type="submit" class="loginbtn" value="登录"
						onclick="return onLogin()" /></li>
				</ul>
			</div>
		</form>
	</div>

	<div class="loginbm" style="position:fixed;bottom:0">版权所有 2015</div>

</body>

</html>

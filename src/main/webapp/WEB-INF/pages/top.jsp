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
<script language="JavaScript" src="js/jquery.js"></script>
<script type="text/javascript">
	$(function() {
		//顶部导航切换
		$(".nav li a").click(function() {
			$(".nav li a.selected").removeClass("selected")
			$(this).addClass("selected");
		})
	})
</script>

</head>
	<body style="background: url(images/topbg.gif) repeat-x;">
	
		<div class="topleft"></div>
	
		<div class="topright">
			<ul>
				<li><span style="color:white;">登陆账号: ${loginAdmin.username} </span></li>
				<li><a href="admin/toUpdatePwd" target="rightFrame">修改密码</a></li>
				<li><a href="admin/logout" target="_parent">退出</a></li>
			</ul>
		</div>
	
	</body>
</html>

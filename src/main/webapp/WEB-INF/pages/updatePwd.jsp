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
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	if("${message}"!=""){
		alert("${message}")
	}
	function onSave() {
		var oldPwd = $("#oldPwd").val();
		var password = $("#password").val();
		var password2 = $("#password2").val();
		if (oldPwd == "") {
			alert("请输入原密码");
			return false;
		}
		if (password == "") {
			alert("请输入新密码");
			return false;
		}
		if (password != password2) {
			alert("两次密码不一致");
			return false;
		}
		return true;
	}
</script>
</head>

<body>
	<div class="formbody">
		<div class="formtitle">
			<span>修改密码</span>
		</div>
		<form action="admin/updatePwd">
			<table class="formtable">
				<tr>
					<td>原密码</td>
					<td><input name="oldPwd" type="password" id="oldPwd"
						class="dfinput w200" /></td>
				</tr>
				<tr>
					<td>新密码</td>
					<td><input name="password" type="password" id="password"
						class="dfinput w200" maxlength="16"/></td>
				</tr>
				<tr>
					<td>确认密码</td>
					<td><input id="password2" type="password" class="dfinput w200" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input name="" type="submit" class="btn" value="确认修改"
						onclick="return onSave()" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>

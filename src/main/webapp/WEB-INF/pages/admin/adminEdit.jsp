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
	function onSave() {
		var userName = $("#username").val();
		var password = $("#password").val();
		var password2 = $("#password2").val();
		if (userName == "") {
			alert("请输入用户名");
			return false;
		}
		if (password == "") {
			alert("请输入密码");
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

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li>编辑用户</li>
		</ul>
	</div>

	<div class="formbody">

		<div class="formtitle">
			<span>编辑用户</span>
		</div>
		<form action="admin/addAdmin">
			<input type="hidden" value="${admin.id}" name="id" />
			<table class="formtable">
				<tr>
					<td class="w100">用户名</td>
					<td><input name="username" id="username" type="text"
						<c:if test="${admin.username!=null}">disabled="disabled"</c:if>
						class="dfinput w200" value="${admin.username}" maxlength="12" /></td>
				</tr>
				<c:if test="${admin.username==null}">
					<tr>
						<td>密码</td>
						<td><input name="password" type="password" id="password"
							class="dfinput w200" maxlength="15"/></td>
					</tr>
					<tr>
						<td>确认密码</td>
						<td><input id="password2" type="password"
							class="dfinput w200" /></td>
					</tr>
				</c:if>
				<tr>
					<td>角色</td>
					<td><c:forEach items="${admin.roles}" var="role">
							<input type="checkbox" value="${role.id}" name="roleIds"
								<c:if test="${role.selected}">checked</c:if> />&nbsp;${role.name}
						</c:forEach></td>
				</tr>
				<tr>
					<td></td>
					<td><input name="" type="submit" class="btn" value="确认保存"
						onclick="return onSave()" /></td>
				</tr>
			</table>
		</form>
	</div>

</body>

</html>

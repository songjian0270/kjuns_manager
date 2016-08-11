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
		var roleName = $("#name").val();
		if (roleName == "") {
			alert("请输入权限名");
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
			<li>编辑权限</li>
		</ul>
	</div>

	<div class="formbody">

		<div class="formtitle">
			<span>编辑权限</span>
		</div>
		<form action="admin/addAuth">
			<input type="hidden" name="id" value="${auth.id}" />
			<table class="formtable">
				<tr>
					<td class="w100">权限名</td>
					<td><input name="name" id="name" type="text"
						class="dfinput w200" value="${auth.name}" maxlength="10"/></td>
				</tr>
				<tr>
					<td></td>
					<td><input name="" type="submit" class="btn" value="确认保存"
						onclick="" /></td>
				</tr>
			</table>
		</form>
	</div>

</body>

</html>

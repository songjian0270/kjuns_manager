<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>无标题文档</title>
	<base href="${basePath}/" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="${basePath}/css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${basePath}/js/jquery.js"></script>
	
	<script type="text/javascript">
		function onSave() {
			var userName = $("#name").val();
			if (userName == "") {
				alert("请输入标签名");
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
			<li>编辑标签</li>
		</ul>
	</div>

	<div class="formbody">

		<div class="formtitle">
			<span>编辑标签</span>
		</div>
		<form action="tag/addTag" method="post">
			<input type="hidden" value="${tag.id}" name="id" />
			<table class="formtable">
				<tr>
					<td class="w100">标签名称</td>
					<td><input name="name" id="name" type="text" class="dfinput w200" value="${tag.name}" maxlength="12" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input name="" type="submit" class="btn" value="确认保存" onclick="return onSave()" /></td>
				</tr>
			</table>
		</form>
	</div>

</body>

</html>

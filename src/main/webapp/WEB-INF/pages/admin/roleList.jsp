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
	$(document).ready(function() {
		$(".click").click(function() {
			$(".tip").fadeIn(200);
		});

		$(".tiptop a").click(function() {
			$(".tip").fadeOut(200);
		});

		$(".sure").click(function() {
			$(".tip").fadeOut(100);
		});

		$(".cancel").click(function() {
			$(".tip").fadeOut(100);
		});

	});
	
	if ("${message}" != ""){
		alert("${message}");
	}
	
	function gotoPage(pageNumber) {
		location.href = "<%=basePath%>admin/roleList?pageNumber=" + pageNumber;
	}
	
	function onDelete(id){
		if(confirm("确定要删除吗？")){
			location.href = "<%=basePath%>admin/deleteRole?id="+id;
		}
	}
</script>


</head>


<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li>角色列表</li>
		</ul>
	</div>

	<div class="rightinfo">

		<div class="tools">
			<div class="toolbar">
				<input type="button" value="＋新增" class="tbtn click"
					onclick="location.href='<%=basePath%>admin/toEditRole'" />
			</div>
		</div>

		<table class="tablelist">
			<thead>
				<tr>
					<th>角色名</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="role">
					<tr>
						<td>${role.name}</td>
						<td><a href="admin/toEditRole?id=${role.id}"
							class="tablelink">修改</a> <a href="javascript:void(0);"
							onclick="onDelete(${role.id})" class="tablelink"> 删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


		<div class="pagin">
			<jsp:include page="../page.jsp" />
		</div>

	</div>

	<script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>
	
</body>

</html>

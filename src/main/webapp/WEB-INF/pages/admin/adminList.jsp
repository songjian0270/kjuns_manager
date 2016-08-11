<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<script language="javascript" type="text/javascript"
	src="<%=basePath%>My97DatePicker/WdatePicker.js"></script>

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

		var type = $('#type').val();
		$('.sitab ul li').each(function(){
			var id = $(this).attr("id");
			if(type==id){
				$(this).addClass('selected'); 
			}else{
				$(this).removeClass('selected'); 	
			}
		})
		
		$('.sitab ul li').click(function(){
			var id = $(this).attr("id");
			$(this).addClass('selected'); 
			$(this).siblings().removeClass('selected'); 	
			$('#type').val(id);
			if(id==0){
				location.href = "<%=basePath%>admin/adminList?pageNumber=1";
			}else{
				location.href = "<%=basePath%>admin/dituiList?pageNumber=1";
			}
		});
	});
	
	if ("${message}" != ""){
		alert("${message}");
	}
	
	function gotoPage(pageNumber) {
		location.href = "<%=basePath%>admin/adminList?pageNumber=" + pageNumber;
	}
	
	function onDelete(id){
		if(confirm("确定要删除吗？")){
			location.href = "<%=basePath%>admin/delete?id="+id;
		}
	}
</script>


</head>


<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li>用户列表</li>
		</ul>
	</div>

	<div class="rightinfo">

		<div class="tools">
			<div class="toolbar">
				<input type="button" value="＋新增" class="tbtn click"
					onclick="location.href='<%=basePath%>admin/toEdit'" />
			</div>
		</div>

		<input type="hidden" id="type" value="${type}" />
		<table class="tablelist">
			<thead>
				<tr>
					<th>帐号</th>
					<th>创建时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="admin">
					<tr>
						<td>${admin.username}</td>
						<td>${admin.createDate}</td>
						<td><a href="admin/toEdit?id=${admin.id}" class="tablelink">修改</a>
							<a href="javascript:void(0);" onclick="onDelete(${admin.id})" class="tablelink">删除</a> 
						</td>
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

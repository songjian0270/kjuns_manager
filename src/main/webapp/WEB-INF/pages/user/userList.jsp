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
	<script type="text/javascript" src="${basePath}/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" charset="utf-8" src="${basePath}/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${basePath}/ueditor/ueditor.all.js"> </script>
    <script type="text/javascript" charset="utf-8" src="${basePath}/ueditor/lang/zh-cn/zh-cn.js"> </script>
	<title>Insert title here</title>
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
			location.href = "${basePath}/user/info/list?pageNumber=" + pageNumber;
		}
		
		function onDelete(id, isf){
			if(isf==0){
				if(confirm("确定封号吗？")){
					location.href = "${basePath}/user/info/delete?id="+id;
				}
			}else{
				if(confirm("确定解封吗？")){
					location.href = "${basePath}/user/info/delete?id="+id;
				}
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
		
		<form action="user/info/list" id="myForm">
			<input type="hidden" name="pageNumber" id="pageNumber" value="${page.pageNumber }"/>
			
			<div class="ftarea">
				<table class="functable">
					<tr>
						<td class="w100">电话号码</td>
						<td><input name="mobliePhone" type="text" class="ftinput w200" value="${userInfo.mobilePhone}"/></td>
						<td class="w100">昵称</td>
						<td><input name="nickName" type="text" class="ftinput w200" value="${userInfo.nickName}"/></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" class="ftbtn" value="搜索" /></td>
					</tr>
				</table>
			</div>
		</form>

		<table class="tablelist">
			<thead>
				<tr>
					<th>手机号码</th>
					<th>昵称</th>
					<th>性别</th>
					<th>军龄</th>
					<th>战区</th>
					<th>头像</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="user">
					<tr>
						<td>${user.mobilePhone}</td>
						<td>${user.nickName}</td>
						<td><c:if test="${user.sex==0 }">女</c:if><c:if test="${user.sex==1 }">男</c:if></td>
						<td>${user.automaticThoughts}</td>
						<td>${user.location}</td>
						<td>${user.faceSrc}</td>
						<td>
							<c:if test="${user.dataFlag==1 }">
								<a href="javascript:void(0);" onclick="onDelete('${user.id}',0)" class="tablelink">封号</a> 
							</c:if>
							<c:if test="${user.dataFlag==0 }">
								<a href="javascript:void(0);" onclick="onDelete('${user.id}',1)" class="tablelink">解封</a> 
							</c:if>
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

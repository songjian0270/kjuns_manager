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
			if(pageNumber==''){
				alert("跳转页码不能为空");
				return ;
			}
			var mobilePhone = $('#mobilePhone').val();
			var nickName = $('#nickName').val();
			location.href = "${basePath}/issuer/list?pageNumber=" + pageNumber +"&mobilePhone="+mobilePhone+"&nickName="+nickName;
		}
		
		function onDelete(id, isf){
			if(isf==21){
				if(confirm("确定要停用吗？")){
					location.href = "${basePath}/issuer/delete?id="+id+"&dataFlag="+isf;
				}
			}else{
				if(confirm("确定要启用吗？")){
					location.href = "${basePath}/issuer/delete?id="+id+"&dataFlag="+isf;
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
		
		<div class="tools">
			<div class="toolbar">
				<input type="button" value="＋新增" class="tbtn click"
					onclick="location.href='${basePath}/issuer/toEdit'" />
			</div>
		</div>
		
		<form action="issuer/list" id="myForm">
			<input type="hidden" name="pageNumber" id="pageNumber" value="${page.pageNumber }"/>
			
			<div class="ftarea">
				<table class="functable">
					<tr>
						<td class="w100">电话号码</td>
						<td><input name="mobliePhone" id="mobilePhone" type="text" class="ftinput w200" value="${userInfo.mobilePhone}"/></td>
						<td class="w100">昵称</td>
						<td><input name="nickName" id="nickName" type="text" class="ftinput w200" value="${userInfo.nickName}"/></td>
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
						<td><img src="${user.faceSrc}" height="20px" width="20px"/></td>
						<td>
							<c:if test="${user.dataFlag==2 }">
								<a href="issuer/toEdit?id=${user.id}" class="tablelink">修改</a>
								<a href="javascript:void(0);" onclick="onDelete('${user.id}',21)" class="tablelink">停用</a> 
							</c:if>
							<c:if test="${user.dataFlag==21 }">
								<a href="javascript:void(0);" onclick="onDelete('${user.id}',2)" class="tablelink">启用</a> 
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

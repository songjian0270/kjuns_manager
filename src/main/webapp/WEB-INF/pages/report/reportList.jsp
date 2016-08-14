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
			location.href = "${basePath}/report/list?pageNumber=" + pageNumber;
		}
		
		function onDelete(id,contentId,contentType){
			if(confirm("确定要下架吗？")){
				location.href = "${basePath}/report/delete?id="+id+"&contentId="+contentId+"&contentType="+contentType;
			}
		}
	
	</script>


</head>


<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li>用户反馈列表</li>
		</ul>
	</div>

	<div class="rightinfo">
		
		<form action="report/list" id="myForm">
			<input type="hidden" name="pageNumber" id="pageNumber" value="${page.pageNumber }"/>
			
			<div class="ftarea">
				<table class="functable">
					<tr>
						<td class="w100">举报类型</td>
						<td>
							<select name="contentType">
								<option value="99" <c:if test="${report.contentType==99 }">selected=selected</c:if>>请选择</option>
								<option value="0" <c:if test="${report.contentType==0}">selected=selected</c:if>>内容</option>
								<option value="1" <c:if test="${report.contentType==1}">selected=selected</c:if>>评论</option>
								<option value="2" <c:if test="${report.contentType==2}">selected=selected</c:if>>阵营内容</option>
								<option value="3" <c:if test="${report.contentType==3}">selected=selected</c:if>>阵营评论</option>
							</select>
						</td>
						<td class="w100">状态</td>
						<td>
							<select name="dataFlag">
								<option value="" <c:if test="${report.dataFlag=='' }">selected=selected</c:if>>请选择</option>
								<option value="0" <c:if test="${report.dataFlag==0}">selected=selected</c:if>>已处理</option>
								<option value="1" <c:if test="${report.dataFlag==1}">selected=selected</c:if>>未处理</option>
							</select>
						</td>
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
					<th>举报类型</th>
					<th>举报内容</th>
					<th>举报人</th>
					<th>举报时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="report">
					<tr>
						<td>
							<c:if test="${report.contentType==0}">内容</c:if>
							<c:if test="${report.contentType==1}">评论</c:if>
						</td>
						<td>${report.content}</td>
						<td>${report.createName}</td>
						<td>${report.createDate}</td>
						<td>
							<c:if test="${report.dataFlag == 1}">
								<a href="javascript:void(0);" onclick="onDelete('${report.id}','${report.contentId}','${report.contentType}')" class="tablelink">下架</a> 
							</c:if>
							<c:if test="${report.dataFlag == 0}">
								<span>已处理</span>
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

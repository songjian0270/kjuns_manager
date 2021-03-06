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
			location.href = "${basePath}/content/contentList?pageNumber=" + pageNumber;
		}
		
		function onDelete(id){
			if(confirm("确定要删除吗？")){
				location.href = "${basePath}/content/delete?id="+id;
			}
		}
	</script>


</head>


<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li>栏目列表</li>
		</ul>
	</div>

	<div class="rightinfo">

		<div class="tools">
			<div class="toolbar">
				<input type="button" value="＋新增" class="tbtn click"
					onclick="location.href='${basePath}/content/toEdit?sectionId=${sectionId}'" />
			</div>
		</div>

		<form action="content/list" id="myForm">
			<input type="hidden" name="pageNumber" id="pageNumber" value="${page.pageNumber }"/>
			
			<div class="ftarea">
				<table class="functable">
					<tr>
						<td class="w100">标题</td>
						<td><input name="title" type="text" class="ftinput w200" value="${title}"/></td>
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
					<th>标题</th>
					<th>类别</th>
					<th>摘要</th>
					<th>来源</th>
					<th>缩略图</th>
					<th>导图</th>
					<th>创建时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="content">
					<tr>
						<td>${content.title}</td>
						<td><!--  0热点1冲突2解放军3美军4图集-->
							<c:forEach items="${typeList}" var="type">
								<c:if test="${content.type==type.id}">${ type.name}</c:if>
							</c:forEach>
						</td>
						<td>${content.summary}</td>
						<td>${content.source}</td>
						<td><img src="${content.thumbnail}" height="20px" width="20px"/></td>
						<td><img src="${content.mindMap}" height="20px" width="20px"/></td>
						<td>${content.createDate}</td>
						<td>
							<a href="content/toEdit?id=${content.id}&sectionId=${sectionId}" class="tablelink">修改</a>
							<a href="javascript:void(0);" onclick="onDelete('${content.id}')" class="tablelink">删除</a> 
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

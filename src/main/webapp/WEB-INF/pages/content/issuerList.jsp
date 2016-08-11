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
			location.href = "${basePath}/content/issuer/list?pageNumber=" + pageNumber;
		}
		
		function confirm(){
			var chk_value =[];
			var chk_name = [];
			$('input[name="checkbox"]:checked').each(function(){
				var nameVal = $('#'+$(this).val()).val();
				chk_value.push($(this).val() + "&#%&"+ nameVal); 
				chk_name.push(nameVal); 
			}); 
			if(chk_value.length==0 ){
				alert('你还没有选择任何内容!');
				return;
			}else{
				if(chk_value.length>1){
					alert('目前只能选择1项!');
					return;
				}
			}
			parent.closeIssuers(chk_value.toString(), chk_name.toString());
		}
		
		function cancel(){
			parent.closeCancel();
		}
	</script>


</head>


<body>

	<div class="rightinfo">
		<form action="content/issuer/list" id="myForm">
			<input type="hidden" name="pageNumber" id="pageNumber" value="${page.pageNumber }"/>
			<div class="ftarea">
				<table class="functable">
					<tr>
						<td class="w100">昵称</td>
						<td><input name="nickName" type="text" class="ftinput w200" value="${nickName}"/></td>
						<td><input type="submit" class="ftbtn" value="搜索" /></td>
					</tr>
				</table>
			</div>
		</form>

		<table class="tablelist">
			<thead>
				<tr>
					<th>操作</th>
					<th>昵称</th>
					<th>创建时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="userInfo">
					<tr>
						<th>
							<input type="checkbox" name="checkbox" value="${userInfo.id}"/>
							<input type="hidden" id="${userInfo.id}" value="${userInfo.nickName}"/>
						</th>
						<td>${userInfo.nickName}</td>
						<td>${userInfo.createDate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div class="pagin" style="height: 5px">
			<jsp:include page="../page.jsp" />
		</div>
		<div class="ftarea">
			<table class="functable">
				<tr>
					<td align="center"><input type="button" class="ftbtn" value="确定" onclick="confirm()"/></td>
					<td align="center"><input type="button" class="ftbtn" value="取消" onclick="cancel()"/></td>
				</tr>
			</table>
		</div>
	</div>

	<script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>

</body>

</html>

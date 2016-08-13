<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
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
<script language="JavaScript" src="js/jquery.js"></script>

<script type="text/javascript">
	$(function() {
		//导航切换
		$(".menuson li").click(function() {
			$(".menuson li.active").removeClass("active")
			$(this).addClass("active");
		});

		$('.title').click(function() {
			var $ul = $(this).next('ul');
			$('dd').find('ul').slideUp();
			if ($ul.is(':visible')) {
				$(this).next('ul').slideUp();
			} else {
				$(this).next('ul').slideDown();
			}
		});
	})
</script>


</head>

<body style="background: #f0f9fd;">
	<div class="lefttop">
		<span></span>功能
	</div>

	<dl class="leftmenu">

		<shiro:hasPermission name="帐号管理">
			<dd>
				<div class="title">
					<span><img src="images/leftico01.png" /></span>帐号管理
				</div>
				<ul class="menuson">
					<li><cite></cite><a href="admin/adminList?pageNumber=1"
						target="rightFrame">帐号管理</a><i></i></li>
					<li><cite></cite><a href="admin/roleList?pageNumber=1"
						target="rightFrame">角色管理</a><i></i></li>
					<li><cite></cite><a href="admin/authList?pageNumber=1"
						target="rightFrame">权限管理</a><i></i></li>
				</ul>
			</dd>
		</shiro:hasPermission>
		<shiro:hasPermission name="内容管理">
			<dd>
				<div class="title">
					<span><img src="images/leftico01.png" /></span>内容管理
				</div>
				<ul class="menuson">
					<li><cite></cite><a href="tag/list?pageNumber=1"
						target="rightFrame">标签管理</a><i></i></li>
					<li><cite></cite><a href="type/list?pageNumber=1"
						target="rightFrame">类别管理</a><i></i></li>
					<li><cite></cite><a href="section/list?pageNumber=1"
						target="rightFrame">专栏管理</a><i></i></li>
					<li><cite></cite><a href="content/list?pageNumber=1"
						target="rightFrame">内容管理</a><i></i></li>
					<li><cite></cite><a href="banner/list?pageNumber=1"
						target="rightFrame">banner管理</a><i></i></li>
					<li><cite></cite><a href="camp/list?pageNumber=1"
						target="rightFrame">阵营管理</a><i></i></li>
				</ul>
			</dd>
		</shiro:hasPermission>
		<shiro:hasPermission name="用户管理">
			<dd>
				<div class="title">
					<span><img src="images/leftico01.png" /></span>用户管理
				</div>
				<ul class="menuson">
					<li><cite></cite><a href="user/info/list?pageNumber=1"
						target="rightFrame">解封管理</a><i></i></li>
					<li><cite></cite><a href="faq/list?pageNumber=1"
						target="rightFrame">反馈管理</a><i></i></li>
					<li><cite></cite><a href="issuer/list?pageNumber=1"
						target="rightFrame">发布人管理</a><i></i></li>
					<li><cite></cite><a href="report/list?pageNumber=1"
						target="rightFrame">举报管理</a><i></i></li>
				</ul>
			</dd>
		</shiro:hasPermission>
		
	</dl>

</body>
</html>

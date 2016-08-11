<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
		<div class="message">
			共<i class="blue">${page.totalCount}</i>条记录，当前显示第&nbsp;<i class="blue">${page.pageNumber}&nbsp;</i>页，&nbsp;共<i
				class="blue">&nbsp;${page.pageCount}&nbsp;</i>页
		</div>
		<ul class="paginList">
			<c:if test="${page.pageNumber == 1 }">
				<a class="nolink">上一页</a>&nbsp;
				</c:if>
			<c:if test="${page.pageNumber > 1 }">
				<a href="javascript:gotoPage(${page.upPage});">上一页</a>&nbsp;
				</c:if>
			<c:if test="${page.pageNumber < page.pageCount }">
				<a href="javascript:gotoPage(${page.nextPage});">下一页</a>
			</c:if>
			<c:if test="${page.pageNumber == page.pageCount or page.pageNumber > page.pageCount}">
				<a class="nolink">下一页</a>
			</c:if>
		</ul>

</body>
</html>
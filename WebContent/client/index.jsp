<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>前端的首页</title>
<!-- 引入共有的css -->
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/style.css"/>
</head>
<body>
	<div id="container">
		<!-- 遍历集合中的数据 -->
		<c:forEach items="${pg.stusList }" var="stu">
			<dl>
				<dt><a href="${pageContext.request.contextPath}/FindByNo?stuNo=${stu.stuNo}"><img src="${pageContext.request.contextPath}/statics/img/${stu.headImg}"/></a></dt>
				<dd>${stu.stuName }</dd>
				<dd>
					<c:if test="${stu.gender eq 0}" var="isGirl">女</c:if>
					<c:if test="${!isGirl }">男</c:if>
				</dd>
			</dl>
		</c:forEach>
		<p><center><a href="ClientView?pageIndex=1">首页</a>
		<a href="ClientView?pageIndex=${pg.currpage-1 }">上一页</a>
		<a href="ClientView?pageIndex=${pg.currpage+1 }">下一页</a>
		<a href="ClientView?pageIndex=${pg.totalPage }">末页</a>
		&nbsp;&nbsp;&nbsp;&nbsp;[当前第${pg.currpage }页/共${pg.totalPage}页]</center><p>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生详情页面</title>
</head>
<body>
	<div>
		<c:if test="${!empty stu }">
			<img src="${pageContext.request.contextPath}/statics/img/${stu.headImg}" align="left" hspace="40"/>
			<div>
				<h3>学号：${stu.stuNo }</h3>
				<h3>姓名：${stu.stuName }</h3>
				<h3>性别：${stu.gender }</h3>
				<h3>年龄：${stu.age }</h3>
				<h3>邮箱：${stu.email }</h3>
			</div>
		</c:if>
	</div>
</body>
</html>
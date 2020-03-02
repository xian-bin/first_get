<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>增加用户信息</title>
</head>
<body>
	<form action="Add" method="post">
		用户名:<input type="text" name="name" value=""/><br/>
		年龄:<input type="text" name="age" value=""/><br/>
		<input type="submit" value="保存"/>
	</form>
</body>
</html>
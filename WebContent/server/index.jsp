<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>服务器端的首页</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery-3.4.1.js"></script>
<script type="text/javascript">
	$(function(){
		$(".del").click(function(){
			// 获得要删除的学号
			var stuNo =$(this).parents("tr").children().first().text();
			$.post("${pageContext.request.contextPath}/Delete","stuNo="+stuNo,
					function(data){
				debugger;
				console.log(data);
				alert(data.result);
				if(data.result=="true"){
					alert("删除成功！！！");
					location.href="${pageContext.request.contextPath}/ServerView";
				}else{
					alert("删除失败！！！");
					location.href="${pageContext.request.contextPath}/ServerView";
				}
			});
			
		});
	});
</script>
</head>
<body>
	<div id="container">
		<table>
			<tr>
				<th>学号</th>
				<th>姓名</th>
				<th>性别</th>
				<th>年龄</th>
				<th>Email</th>
				<th>头像地址</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${pg.stusList }" var="stu">
				<tr>
					<td>${stu.stuNo }</td>
					<td>${stu.stuName }</td>
					<td>${stu.gender }</td>
					<td>${stu.age }</td>
					<td>${stu.email }</td>
					<td>${stu.headImg }</td>
					<td><a href="FindNo?stuNo=${stu.stuNo}">修改</a><a href="javascript:void(0);" class="del">删除</a></td>
				</tr>
			</c:forEach>
		</table>
		<p><a href="${pageContext.request.contextPath}/server/addStu.jsp">增加学生信息</a></p>
		<p><center><a href="ServerView?pageIndex=1">首页</a>
		<a href="ServerView?pageIndex=${pg.currpage-1 }">上一页</a>
		<a href="ServerView?pageIndex=${pg.currpage+1 }">下一页</a>
		<a href="ServerView?pageIndex=${pg.totalPage }">末页</a>
		&nbsp;&nbsp;&nbsp;&nbsp;[当前第${pg.currpage }页/共${pg.totalPage}页]</center><p>
	</div>
</body>
</html>
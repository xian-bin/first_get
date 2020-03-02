<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>增加学生信息的页面</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery-3.4.1.js"></script>
<script type="text/javascript">
	// 书写Jquery的加载函数
	$(function(){
		changeImg();
		// 当姓名的表单元素失去焦点后，需要验证该用户名是否存在，并即时的给响应
		$("input[name='stuName']").blur(function(){
			// 使用Ajax来进行异步的验证
			// $.post("验证的Servlet名称",传递的参数,回调函数);
			$.post("${pageContext.request.contextPath}/CheckName",
					"stuName="+$(this).val(),
					function(data){
				// 判断后台响应的结果
				// data 就是一个Json对象
				// result 是在servlet中书写的{"result":"true"}中书写的key值
				debugger;
				console.log(data.result);
				$("input[name='stuName']").next().remove();
				if(data.result=="true"){
					$("input[name='stuName']").after("<span>此用户名已存在<span>");
				}else{
					$("input[name='stuName']").after("<span>此用户名可以注册<span>");
				}
			});
		});
		
	});
	
	// 定义一个当src发生改变时就到后台去获得一个随机的验证码
	function changeImg() {
		// 获得验证码图片元素 使用JavaScript获得
		var img = document.getElementById("codeImg");
		// 当src的路径发生改变，都会到后台去请求一次
		// new Date().getTime() 避免浏览器不去后台请求数据，因为有缓存
		img.src="${pageContext.request.contextPath}/CodeImg?"+new Date().getTime();
	}
</script>
</head>
<body>
	<p><center style="color:red;">${msg }</center></p>
	<form action="${pageContext.request.contextPath}/Add" method="post" enctype="multipart/form-data">
		<table>
			<input type="hidden" name="op" value="${empty stu ? 'add':'update' }"/>
			<input type="hidden" name="stuNo" value="${empty stu ? '',stu.stuNo }"/>
			<tr>
				<td>姓名:</td>
				<td>
				<c:if test="${empty stu }" var="isNull"><input type="text" name="stuName" value=""/></c:if>
				<c:if test="${!isNull }"><input type="text" name="stuName" value="${stu.stuName }"/></c:if></td>
			</tr>
			<tr>
				<td>性别:</td>
				<td><input type="radio" name="gender" value="0"/>女
				&nbsp;&nbsp;<input type="radio" name="gender" value="1"/>男</td>
			</tr>
			<tr>
				<td>年龄:</td>
				<td>
					<c:if test="${empty stu }" var="isNull"><input type="text" name="age" value=""/></c:if>
					<c:if test="${!isNull }"><input type="text" name="age" value="${stu.age }"/></c:if>
				</td>
			</tr>
			<tr>
				<td>Email:</td>
				<td>
					<c:if test="${empty stu }" var="isNull"><input type="text" name="email" value=""/></c:if>
					<c:if test="${!isNull }"><input type="text" name="email" value="${stu.email }"/></c:if>
				</td>
			</tr>
			<tr>
				<td>头像:</td>
				<td>
				<c:if test="${empty stu }" var="isNull"><input type="file" name="headImg"/></c:if>
					<c:if test="${!isNull }"><img src="${pageContext.request.contextPath}/statics/img/${stu.headImg}"/><input type="file" name="headImg"/></c:if>	
				</td>
			</tr>
			<tr>
				<td>验证码:</td>
				<td><input type="text" name="code" value=""/>
					<img id="codeImg" src=""/>
					<a href="javascript:changeImg();">看不清</a>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>
					<c:if test="${empty stu }" var="isNull"><input type="submit" value="增加"/></c:if>
					<c:if test="${!isNull }"><input type="submit" value="提交"/></c:if>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
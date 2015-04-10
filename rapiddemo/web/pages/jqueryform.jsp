<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'jqueryform.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${root}/scripts/jquery.js"></script>
	<script type="text/javascript" src="${root}/scripts/jquery.form.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		$(function(){
			var options = {
				resetForm:true,
				beforeSubmit:validForm,
				dataType:'json',
				success:function(){
					alert('Thanks for your comment!!!');
				}
			};
			$('form').submit(function() {
				$(this).ajaxSubmit(options);
				 // 为了防止普通浏览器进行表单提交和产生页面导航（防止页面刷新？）返回false
				return false;
			});
		});
		
		function validForm() {
			var name = $("input[name='name']").val().trim();
			if (name.length == 0) {
				alert('please enter your name');
				return false;
			};
			return true;
		}
	</script>
  </head>
  
  <body>
    <form action="${root}/jqueryform/test">
    	姓名：<input type="text" name="name"><br>
    	年龄：<input type="number" name="age"><br>
    	性别：<input type="radio" name="sex" value="0" checked>男&nbsp;<input type="radio" name="sex" value="1">女<br>
    	
    	<h3>张三的个人信息：</h3>
    	姓名：<input type="userList[0].username"><br>
    	年龄：<input type="userList[0].age"><br>
    	
    	<br><br>
    	<h3>李四的个人信息：</h3>
    	姓名：<input type="userList[1].username"><br>
    	年龄：<input type="userList[1].age"><br><br>
    	<input type="submit" value="提交">
    </form>
  </body>
</html>

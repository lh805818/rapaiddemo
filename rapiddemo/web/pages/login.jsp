<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    <meta charset='gbk'>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		input {
			width:180px;
			height:30px;
		}
		
		.submit {
			text-align:center;
		}
	</style>
  </head>
  
  <body>
  	<form action="manage/login" method="post">
	    用户名：<input type="text" name="userid"><br>
	    密码：<input type="password" name="password"><br>
   	   <input type="submit" class="submit" value="登入">
    </form>
  </body>
</html>

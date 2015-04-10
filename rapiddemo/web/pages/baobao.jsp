<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'baobao.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
	宝宝个数：<input type="text" id="baobaoNumber"><br>
	微信号：<input type="text" id="wxno"><br>
	<input type="button" value="抽奖" onclick="choujiang();">    
	<script type="text/javascript" src="${root}/scripts/jquery.js"></script>
	<script type="text/javascript">
		var baobaoNumber = $.trim($("#baobaoNumber").val());
		var wxno = $.trim($("#wxno").val());
		var url = "${root}/hallowmas/list/13/" + wxno + "/1" + baobaoNumber;
		
		$.ajax({
			url:url,
			success:function() {
				alert("done");
			}
		});
	</script>
  </body>
</html>

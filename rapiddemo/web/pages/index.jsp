<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    
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
  	<div><a href="manage/logout">�˳���¼</a></div>
    ��ã���ĸ�����Ϣ���£�
    <p>
    	�û�id:${user.id}<br>
    	������${user.name}<br>
    	�ֻ���${user.mobile}
    </p>
    <p>
    	��ɫ��Ϣ��
    	<br>
    	<c:forEach items="${colors}" var="item">
    		${item}:<div style="width:200px;height:22px;background-color:${item}"></div>
    	</c:forEach>
    </p>
    
  </body>
</html>

<jsp:include page="segment.jsp"></jsp:include>
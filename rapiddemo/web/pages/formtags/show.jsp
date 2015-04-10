<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@include file="base.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'show.jsp' starting page</title>
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
  	<div>
  		<p>简单数据类型绑定：</p>
  		<div>
		    username:<c:out value="${username }"/><br>
		    age:<c:out value="${age }"/>
  		</div>
  	</div>
  	<hr/>
  	<div>
  		<p>简单对象绑定：</p>
  		<div>
  			username:<c:out value="${user.username}"/><br>
  			age:<c:out value="${user.age}"/>
  		</div>
  	</div>
  	
  	<hr>
  	<div>
  		<p>List</p>
  		<div>
  			<c:forEach items="${club.userList}" var="user">
  				username:${user.username } <br>
  				age:${user.age }<br>
  			</c:forEach>
  		</div>
  	</div>
  	
  	<hr>
  	<div>
  		<p>Map</p>
  		<div>
  			<c:forEach items="${club.userMap}" var="map">
  				${map.key }的信息：<br>
  				username:${map.value.username }<br>
  				age:${map.value.age }<br><br>
  			</c:forEach>
  		</div>
  	</div>
  	
  </body>
</html>

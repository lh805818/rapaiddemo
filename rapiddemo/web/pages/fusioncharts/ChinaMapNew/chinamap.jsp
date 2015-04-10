<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" style="height:100%;" lang="ch">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" href="Style/Style.css" rel="stylesheet"/>
<title>Demo</title>
<script type="text/javascript" src="${root}/rapiddemo/pages/fusioncharts/ChinaMapNew/swfobject.js"></script>
<script type="text/javascript">
	function eventHandler(e) {
		alert(e.value);
	}
</script>
</head>

<body id="player">
	<!--容器end-->
	<div id="container">Get the Flash Player to see this player</div>
	<!--容器end-->
	<!--init code-->
	<script type="text/javascript">
		var s1 = new SWFObject("${root}/rapiddemo/pages/fusioncharts/ChinaMapNew/ChinaMap.swf","ply","600","500","10","#FFFFFF");
		s1.addParam("allowfullscreen","true");
		s1.addParam("allownetworking","all");
		s1.addParam("allowscriptaccess","always");
		s1.addParam("wmode","transparent");
		s1.addVariable("title","中国地图");
		s1.addVariable("xmlurl","${root}/rapiddemo/pages/fusioncharts/ChinaMapNew/data/d.xml");
		s1.addVariable("jsHandler","eventHandler");
		s1.write("container");
	</script>
	<!--/init code-->
	
</body>
</html>


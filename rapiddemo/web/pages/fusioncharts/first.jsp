<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'first.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${root}/rapiddemo/scripts/jquery.js"></script>
	<script type="text/javascript" src="${root}/rapiddemo/scripts/fusioncharts/fusioncharts.js"></script>
	<script type="text/javascript" src="${root}/rapiddemo/scripts/fusioncharts/themes/fusioncharts.theme.ocean.js"></script>
	<script type="text/javascript">
		FusionCharts.ready(function(){
			$("input:first").click(function() {
				var revenueChart = new FusionCharts({
					type: "column2d",
					renderAt: "chartContainer",
					width: "700",
					height: "400",
					dataFormat: "jsonurl",
					dataSource: "${root}/rapiddemo/pages/fusioncharts/data/1.txt" 
				});
				revenueChart.render("chartContainer");
			});
			$("input:eq(1)").click(function() {
				var revenueChart = new FusionCharts({
					type: "column3d",
					renderAt: "chartContainer",
					width: "700",
					height: "400",
					dataFormat: "jsonurl",
					dataSource: "${root}/rapiddemo/pages/fusioncharts/data/2.txt" 
				});
				revenueChart.render("chartContainer");
			});
			
			$("input:eq(2)").click(function() {
				var revenueChart = new FusionCharts({
					type: "doughnut2d",
					renderAt: "chartContainer",
					width: "700",
					height: "400",
					dataFormat: "jsonurl",
					dataSource: "${root}/rapiddemo/pages/fusioncharts/data/3.txt" 
				});
				revenueChart.render("chartContainer");
			});
			$("input:eq(3)").click(function() {
				var revenueChart = new FusionCharts({
					type: "doughnut3d",
					renderAt: "chartContainer",
					width: "700",
					height: "400",
					dataFormat: "jsonurl",
					dataSource: "${root}/rapiddemo/pages/fusioncharts/data/3.txt" 
				});
				revenueChart.render("chartContainer");
			});
			$("input:eq(4)").click(function() {
				var revenueChart = new FusionCharts({
					type: "line",
					renderAt: "chartContainer",
					width: "700",
					height: "400",
					dataFormat: "jsonurl",
					dataSource: "${root}/rapiddemo/pages/fusioncharts/data/4.txt" 
				});
				revenueChart.render("chartContainer");
			});
			$("input:eq(5)").click(function() {
				var revenueChart = new FusionCharts({
					type: "mscolumn3d",
					renderAt: "chartContainer",
					width: "700",
					height: "400",
					dataFormat: "jsonurl",
					dataSource: "${root}/rapiddemo/pages/fusioncharts/data/5.txt" 
				});
				revenueChart.render("chartContainer");
			});
			$("input:eq(6)").click(function() {
				var revenueChart = new FusionCharts({
					type: "msline",
					renderAt: "chartContainer",
					width: "700",
					height: "400",
					dataFormat: "jsonurl",
					dataSource: "${root}/rapiddemo/pages/fusioncharts/data/6.txt" 
				});
				revenueChart.render("chartContainer");
			});
			$("input:eq(7)").click(function() {
				var revenueChart = new FusionCharts({
					type: "zoomline",
					renderAt: "chartContainer",
					width: "700",
					height: "400",
					dataFormat: "jsonurl",
					dataSource: "${root}/rapiddemo/pages/fusioncharts/data/7.txt" 
				});
				revenueChart.render("chartContainer");
			});
		});  
	</script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <div id="chartContainer">FusionCharts XT will load here!</div>
    <div>
    	<input type="button" value="column2d">
    	<input type="button" value="column3d">
    	<input type="button" value="doughnut2d">
    	<input type="button" value="doughnut3d">
    	<input type="button" value="line">
    	<input type="button" value="mscolumn3d">
    	<input type="button" value="msline">
    	<input type="button" value="zoomline">
    </div>
  </body>
</html>

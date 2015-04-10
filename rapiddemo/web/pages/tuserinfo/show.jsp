<%@page import="com.company.project.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title><%=TuserInfo.TABLE_ALIAS%>信息</title>
</rapid:override>

<rapid:override name="content">
	<form:form modelAttribute="tuserinfo"  >
		<input type="button" value="返回列表" onclick="window.location='${root}/tuserinfo'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<input type="hidden" id="userId" name="userId" value="${tuserInfo.userId}"/>
	
		<table class="formTable">
			<tr>	
				<td class="tdLabel"><%=TuserInfo.ALIAS_USERNAME%></td>	
				<td><c:out value='${tuserInfo.username}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=TuserInfo.ALIAS_PASSWORD%></td>	
				<td><c:out value='${tuserInfo.password}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=TuserInfo.ALIAS_BIRTH_DATE%></td>	
				<td><c:out value='${tuserInfo.birthDateString}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=TuserInfo.ALIAS_SEX%></td>	
				<td><c:out value='${tuserInfo.sex}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel"><%=TuserInfo.ALIAS_AGE%></td>	
				<td><c:out value='${tuserInfo.age}'/></td>
			</tr>
		</table>
	</form:form>
</rapid:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>
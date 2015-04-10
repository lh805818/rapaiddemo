<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@include file="base.jsp" %>

	<title>Spring Form Tags</title>
	<script type="text/javascript" src="${root}/scripts/jquery.form.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#saveButton").bind("click",function() {
				$("#myForm").ajaxSubmit({
					dataType: "json",
					async:false,
					success:function(data) {
						if (data.flag == "1") {
							alert("数据保存成功");
						}
						else {
							alert("数据保存失败");
						}
					},
					error:function() {
						alert("数据出错");
					}
				});
			
			});
		});
	</script>

	<form id="myForm" action="${root}/manage/spring/form/tags/binding" method="post">
		<table>
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="username"></td>
			</tr>
			<tr>
				<td>年龄：</td>
				<td><input type="text" name="age"></td>
			<tr>
			<tr>
				<td><input type="button" id="saveButton" value="提交"></td>
			</tr>
		</table>
	</form>
	
	<div>
		<p>简单数据绑定</p>
		<form action="${root}/manage/spring/form/tags/binding/simple">
			<table>
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="username"></td>
			</tr>
			<tr>
				<td>年龄：</td>
				<td><input type="text" name="age"></td>
			<tr>
			<tr>
				<td><input type="submit" id="saveButton" value="提交"></td>
			</tr>
		</table>
		</form>
	</div>
	<hr>
	<div>
		<p>简单对象绑定</p>
		<form action="${root}/manage/spring/form/tags/binding/simpleObject">
			<table>
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="username"></td>
			</tr>
			<tr>
				<td>年龄：</td>
				<td><input type="text" name="age"></td>
			<tr>
			<tr>
				<td><input type="submit" id="saveButton" value="提交"></td>
			</tr>
		</table>
		</form>
	</div>
	<hr>
	
	<div>
		<p>List数据类型绑定</p>
		<form action="${root}/manage/spring/form/tags/binding/list" method="post">
			<div>
				<p>输入用户1的信息</p>
				<div>
					<table>
						<tr>
							<td>用户名：</td>
							<td><input type="text" name="userList[0].username"></td>
						</tr>
						<tr>
							<td>年龄：</td>
							<td><input type="text" name="userList[0].age"></td>
						<tr>
					</table>
				</div>
			</div>
			<div>
				<p>输入用户2的信息</p>
				<div>
					<table>
						<tr>
							<td>用户名：</td>
							<td><input type="text" name="userList[1].username"></td>
						</tr>
						<tr>
							<td>年龄：</td>
							<td><input type="text" name="userList[1].age"></td>
						<tr>
					</table>
				</div>
			</div>
			<input type="submit" id="saveButton" value="提交">
		</form>
	</div>
	
	<form action="${root}/manage/spring/form/tags/binding/map" method="post">
	<div>
		<p>Map数据绑定：</p>
		<div>
			<p>admin</p>
			<table>
				<tr>
					<td>用户名：</td>
					<td><input type="text" name="userMap['admin'].username" value="admin"></td>
				</tr>
				<tr>
					<td>年龄：</td>
					<td><input type="text" name="userMap['admin'].age" value="22"></td>
				</tr>
			</table>
		</div>
		<div>
			<p>system</p>
			<table>
				<tr>
					<td>用户名：</td>
					<td><input type="text" name="userMap['system'].username" value="system"></td>
				</tr>
				<tr>
					<td>年龄：</td>
					<td><input type="text" name="userMap['system'].age" value="33"></td>
				</tr>
			</table>
		</div>
	</div>
	<input type="submit" id="saveButton" value="提交">
	</form>

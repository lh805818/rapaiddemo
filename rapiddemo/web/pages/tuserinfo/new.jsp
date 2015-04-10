<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title><%=TuserInfo.TABLE_ALIAS%>新增</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/widgets/artDialog/default.css"/>" />
	<script type="text/javascript" src="${root}/widgets/artDialog/artDialog.js"></script>
	<script type="text/javascript">
		$(function() {
			/**页面正中间显示图片*/
			$("#artDialog").bind("click",function() {
				artDialog({
					title:"图片查看",
					content:"<img src='http://pic.baomihua.com/photos/201109/m_6_634516970681718750_11187211.jpg'>",
					fixed:true,
					lock:true,
					esc:true
				});
			});
			
			$("#artDialog2").bind("click",function() {
				artDialog({
					title:"系统提示",
					style:"succeed noClose",
					content:"确定要删除吗？",
					fixed:true,
					lock:true,
					esc:true
				},function() {
				},function() {
					alert("你选择了取消");
				},function() {
					alert("你选择了关闭");
				});
			});
			
			/**右下角提示*/
			$("#artDialog3").bind("click",function() {
				artDialog({
					title:"系统提示",
					content:"<span>您有1条短消息</span><br><img src='http://pic.baomihua.com/photos/201109/m_6_634516970681718750_11187211.jpg' width=200 height=120>",
					width:220,
					height:140,
					left:'100%',
					top:'100%',
					drag:false,
					resize:false,
					fixed:true,
					lock:true,
					esc:true
				});
			});
			
			$("#input").bind("blur",function() {
				var d = artDialog({
					quickClose: true,
					content: '输入错误，请重新输入',
					onclose: function () {
						$('#input').focus();
						console.log('对话框执行close()操作，如果开发者对焦点操作，那么将使用开发者的设置');
					},
					follow: $('#input')[0]
				});
		
				d.show();
				console.log('对话框执行show()方法，焦点应该在对话框上');
			});
		
			/*window.alert = function(msg) {
				artDialog({
					content:msg,
					fixed:true,
					lock:true,
					esc:true
				});
			}*/
		});
	</script>
</rapid:override>

<rapid:override name="content">
	<form:form method="post" action="${root}/tuserinfo" modelAttribute="tuserInfo" >
		<input id="submitButton" name="submitButton" type="submit" value="提交" />
		<input type="button" value="返回列表" onclick="window.location='${root}/tuserinfo'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		<input type="button" value="artDialog" id="artDialog"/>
		<input type="button" value="artDialog2" id="artDialog2"/>
		<input type="button" value="artDialog3" id="artDialog3"/>
		<input type="text" id="input"/>
		
		<table class="formTable">
		<%@ include file="form_include.jsp" %>
		</table>
	</form:form>
	
	<script>
		
		new Validation(document.forms[0],{onSubmit:true,onFormValidate : function(result,form) {
			var finalResult = result;
			
			//在这里添加自定义验证
			
			return disableSubmit(finalResult,'submitButton');
		}});
	</script>
</rapid:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>

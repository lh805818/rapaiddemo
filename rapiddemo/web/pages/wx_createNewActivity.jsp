<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<rapid:override name="head">
	<title>使用UEditor上传文件</title>
	<script type="text/javascript" charset="utf-8">
		window.UEDITOR_HOME_URL = "${ctx}/widgets/ueditor/";
		console.log('window.UEDITOR_HOME_URL:' + window.UEDITOR_HOME_URL);
	</script>
    <!-- 配置文件 -->
    <script type="text/javascript" src="${ctx }/widgets/ueditor/editor_config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="${ctx }/widgets/ueditor/editor_all.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx}/widgets/ueditor/_examples/editor_api.js"></script>
   
</rapid:override>

<rapid:override name="content">
	<form:form method="post" action="${ctx}/tuserinfo" modelAttribute="tuserInfo" >
		<div>
			<span>标题</span>
			<span><input type="text" name="title"></span>
		</div>
		<div>
			<span>正文</span>
			<span>
				<textarea id="contents"></textarea>
			</span>
		</div>
		<input type="submit" value="保存" id="submitButton">
	</form:form>
	
	<script>
		
		new Validation(document.forms[0],{onSubmit:true,onFormValidate : function(result,form) {
			var finalResult = result;
			
			//在这里添加自定义验证
			
			return disableSubmit(finalResult,'submitButton');
		}});
		
		 <!-- 实例化编辑器 -->
        var ue = UE.getEditor('contents',{
	       toolbars:[
	            ['fullscreen', 'undo', 'redo', '|',
	                'bold', 'underline', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch','autotypeset', '|', 'forecolor', 'backcolor', 'cleardoc', '|',
	                'rowspacingtop', 'rowspacingbottom','lineheight','|',
	                'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|',
	                'imagenone', 'imageleft', 'imageright','imagecenter', '|',
	                'customstyle', 'paragraph', 'fontsize', '|',
	                'emotion','date', 'time',
					'spechars', '|',
	                'searchreplace', 'insertimage', 'wordimage', 'link']
	        ],
	        initialFrameHeight:320,
	        initialFrameWidth:1000});
	</script>
</rapid:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>

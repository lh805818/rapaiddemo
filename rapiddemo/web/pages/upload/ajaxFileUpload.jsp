<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/taglibs.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<title>AjaxFileUpload Demo</title>
	<script type="text/javascript" src="${root}/scripts/jquery.js"></script>	
	<script type="text/javascript" src="${root}/scripts/ajaxfileupload.js"></script>	
	<script type="text/javascript">
		$(function(){
			$('#loading').ajaxStart(function(){
				$(this).show();
			}).ajaxComplete(function(){
				$(this).hide();
			});
			
			$('#uploadBtn').click(function(){
				if ($.trim($('#file1').val()).length > 0) {
					$.ajaxFileUpload(
					{
						url:'${root}/ajaxFileUpload/upload',
						secureuri:false,
						fileElementId:'file1',
						dataType:'json', // 返回的结果被包装在<pre>标签中，报语法错误。
						data:{ // 额外的数据
							ts:new Date().getTime()
						},
						success:function(data,status){
							if(typeof(data.error) != 'undefined') {
								 if(data.error != '') {
								  alert('erorr:' + data.error);
								 }
								 else {
								  alert(data.msg);
								  $('#preview').attr('src',data.savePath).show();									  
								 }
							}
						},
						error:function(data,status,e) {
							alert(e);
						}
					}
					);
				}
				else {
					alert("请选择文件");
				}
				
				return false;
			});
		});
	</script>
</head>
<body>
	<div id="loading" style="display:none;">文件正在上传，请稍后...</div>
	<div>
		<input type="file" id="file1" name="file"><input type="button" value="Upload" id="uploadBtn"><br>
		<img id='preview' src='' style='display:none;width:50%;height:50%;'>
	</div>
</body>
</html>
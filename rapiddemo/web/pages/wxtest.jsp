<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath ="";
	if("80".equals(request.getServerPort())){
		 basePath = request.getScheme() + "://" + request.getServerName()  + path + "/";
	}else{
		 basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	}
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh_cn">
	<head>
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="Cache-Control" content="no-cache" />
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <title>玫瑰人生</title>
    <style type="text/css">
    	 body,h1,h2,h3,h4,h5,h6,hr,p,blockquote,dl,dt,dd,ul,ol,li,pre,form,fieldset,legend,button,input,textarea,th,td{margin:0;padding:0;}
		html{ height:100%; width:100%; margin:0 auto;overflow:hidden;}
    </style>
    <script type="text/javascript" src="${root}/scripts/jquery.js"></script>
    <script src="${root}/scripts/viewport.min.js"></script>
	<script type="text/javascript" src="${root}/scripts/WXUtil.js?version=20141103"></script>
	<script type="text/javascript" src="${root}/scripts/checkUser.js?version=20141103"></script>
	<script type="text/javascript" src="${root}/scripts/wx_share.js?version=20141103"></script>
	<script type="text/javascript">
		function is_weixin(){
			var ua = navigator.userAgent.toLowerCase();
			if(ua.match(/MicroMessenger/i)=="micromessenger") {
				return true;
		 	} else {
				return false;
			}
		}
		
		/*if (!is_weixin()) {
			window.location.href = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxdf3f22ebfe96b912&redirect_uri=xxx&response_type=code&scope=snsapi_base&state=hyxt#wechat_redirect';
		}*/
	var dataForWeixin = {
				appId:"", 
				img:"<%=basePath%>upload/${tactivity.shopid}/${tactivity.smpic}",
				url:"wmgrs/pages/wxmgrsredpack/test.html", 
				title:"${tactivity.activityname}", 
				desc:"亲，玫瑰人生10周年，感恩红包大派送，赶紧来抢吧！",
				fakeid:"", callback:function () {}
			};
		// 弹出分享提示层
		function showShareTip(){
			$("#tipInf").show();
		}
		 $(document).ready(function(){
		 	$("#tipInf").click(function(){
				$("#tipInf").hide();
			});
		 	
		 	setOpenidForShopid2('${root}','1');
		 });
	</script>
	</head>
	<body>
		<div>
			<form action="http://qincdtest.ematong.com/wmgrs/wxmgrsredpack/show" method="post">
			  	<input type="text" name="img" value='http://wx.qlogo.cn/mmopen/UsKSSLY7V2SnOtXRvfgzOQvcn8y1CsBaJiaOTSXd9cIvqQjodRiaVic8UojbqaOW5IiaMSNYicg2kibpnFqRkoFOtMAgWcKhZGNwtg/0'/> 图片<br>
			  	<input type="text" name="openId" value='oR41fuF7OhmbJRtYW6spYHJgIxXA'/>微信号<br>
			  	<input type="text" name="nickname" value='一键还原'/>昵称<br>
				<input type="text" name="sharewxno" value='ovqhpuJYpTO_3-0x1-48dEVv7v70'/>分享微信号<br>
			  	<input  type="submit" value='2222222222'/>
			  </form>
		</div>
		<div class="bar-b"><a href="javascript:showShareTip();">分享红包给朋友</a></div>
		<div id="tipInf"  style="width:100%;display:none;height:100%;position: fixed; float: left;background:rgba(0,0,0,0.85);top:0;left:0; z-index: 99999;font-size:16px;text-align:center;">
   			<img src="${root}/styles/images/shareTip.png" style="float:left;width:100%;"/>
		</div>
	</body>
	</html>
/*******************************
 * Author:Mr.Think
 * Description:微信分享通用代码
 * 使用方法：mecom_WXShare({'分享显示的LOGO','LOGO宽度','LOGO高度','分享标题','分享描述','分享链接','微信APPID(一般不用填)'});
 *******************************/
function mecom_WXShare (option){
    //初始化参数
   var o = {
		   img:'',
		   width:100,
		   height:100,
		   title:document.title,
		   desc:document.title,
		   url:document.location.href.replace(/(^|&)code=([^&]*)/g, ""),
		   appid:'',
		   callback:function(){}
		   
   } ;
   

   $.extend(o,option);//传入参数

   this.setOption = function(args){
       if (args.url){
    	   args.url = args.url.replace(/(^|&)code=([^&]*)/g, "");
       }
	   $.extend(o,args);//传入参数
   };

   this.setOption({url:o.url.replace(/(^|&)code=([^&]*)/g, "")});

    //微信内置方法
    function _ShareFriend() {
        WeixinJSBridge.invoke('sendAppMessage',{
              'appid': o.appid,
              'img_url': o.img,
              'img_width': o.width,
              'img_height': o.height,
              'link': o.url,
              'desc': o.desc,
              'title': o.title
              }, function(res){
            	  o.callback();
                 _report('send_msg', res.err_msg);
          });
    }
    function _ShareTL() {
        WeixinJSBridge.invoke('shareTimeline',{
              'img_url': o.img,
              'img_width': o.width,
              'img_height': o.height,
              'link': o.url,
              'desc': o.desc,
              'title': o.title ? (o.title + ' ' + o.desc) : o.desc
              }, function(res) {
            	  o.callback();
                  _report('timeline', res.err_msg);
              });
    }
    function _ShareWB() {
        WeixinJSBridge.invoke('shareWeibo',{
              'content': o.desc,
              'url': o.url,
              "img_url":o.img,
              "title":o.title,
              'img_width': o.width,
              'img_height': o.height,
              }, function(res) {
            	  o.callback();
                 _report('weibo', res.err_msg);
              });
    }
    

    // 当微信内置浏览器初始化后会触发WeixinJSBridgeReady事件。
    document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
            // 发送给好友
            WeixinJSBridge.on('menu:share:appmessage', function(argv){
                _ShareFriend();
          });

            // 分享到朋友圈
            WeixinJSBridge.on('menu:share:timeline', function(argv){
                _ShareTL();
                });

            // 分享到微博
            WeixinJSBridge.on('menu:share:weibo', function(argv){
                _ShareWB();
           });

    }, false);
};



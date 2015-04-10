var WXUtil = {
     
      root:"",
     
     setPrefix :function(root){
        this.root = root;
     },
     
     /**
       *获取url上面的参数值
       *name 参数名
       *return 参数值
      */  
      getUrlParam:function(name){
          var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
          var r = window.location.search.substr(1).match(reg);  //匹配目标参数
          if (r!=null) return unescape(r[2]); return null; //返回参数值
      },
      
      //隐藏网页右上角按钮
      hideOptionMenu:function(){
        document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
		    WeixinJSBridge.call('hideOptionMenu');
		});
      },
       //显示网页右上角按钮
      showOptionMenu:function(){
        document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
		    WeixinJSBridge.call('showOptionMenu');
		});
      },
      
      //隐藏网页底部导航栏
      hideToolbar:function(){
         document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
			   WeixinJSBridge.call('hideToolbar');
		  });
      },
      //显示网页底部导航栏
      showToolbar:function(){
         document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
			   WeixinJSBridge.call('showToolbar');
		  });
      },
      
      //获取网络状态
      /**
        *network_type:wifi wifi网络
		*network_type:edge 非wifi,包含3G/2G
	    *network_type:fail 网络断开连接
	    *network_type:wwan（2g或者3g）
        *
       */
      getNetworkType:function(){
           WeixinJSBridge.invoke('getNetworkType',{},
              function(e){
                return e.err_msg;
	      });
        
      },
      
      /**
       * 通过公众号的id获取设置的Appid
       * generalid 公众号的id
       * return 公众号设置的APPID
       *  eg: 有数据是返回{appid:wxb37de2311affafd2}
       *      出错则：{errcode："01",msg:"msg"}
       */
      getAppid:function(shopid,redirect){
           $.ajax({
             url:this.root+"/wxutil/getAppid",
             type:"POST",
             data:{shopid:shopid,ts:(new Date()).getTime()},
             dataType:"json",
             async:false,
             success:function(data){
                var appid  = data.appid;
                 if(!appid){
                    return data;
                  } 
                  redirect(appid);
             },
             error:function(jqXHR,textStatus){
                return {errcode:"01",errmsg:"获取公众号的appid失败!"};
             }          
           });
      },
      
	  	/** 转向获取code页面 */
	  	toGetCode:function(shopid){
	  		var redirect_uri = window.location.href;
	  		this.getAppid(shopid, function(appid){
	  			//微信授权的固定url
	            var wx_oauth2_url = "https://open.weixin.qq.com/connect/oauth2/authorize";
	            var param = "?appid=" + appid + "&redirect_uri=" + encodeURIComponent(redirect_uri) + "&response_type=code&scope=snsapi_base&state=hyxt#wechat_redirect";
	            var url = wx_oauth2_url + param;
	            window.location.href = url;
	  		});
	  	},
	


	  	/** 转向通过网页授权的方式获取 */
	  	toGetUserinfoCode:function(shopid){
	  		var redirect_uri = window.location.href.replace(/(^|&)code=([^&]*)/g, "");
	  		//微信授权的固定url
	  		this.getAppid(shopid, function(appid){
		        var wx_oauth2_url = "https://open.weixin.qq.com/connect/oauth2/authorize";
		        var param = "?appid=" + appid + "&redirect_uri=" + encodeURIComponent(redirect_uri) + "&response_type=code&scope=snsapi_userinfo&state=hyxt#wechat_redirect";
		        var url = wx_oauth2_url + param;
	            //set_Storage("s_expires_in",new Date().getTime());
		        window.location.href = url;
	  		});
	  	
	  	},
	  	/** 获取用户openid */
	  	getOpenidByCode:function(shopid, callback){
	      	var code = this.getUrlParam("code");
	          $.ajax({
	  			url:this.root + "/wxutil/getOpenidByCode",
	  			type:"POST",
	              data:{shopid:shopid, code:code, ts:(new Date()).getTime()},
	              dataType:"json",
	              success:function(data){
	              	   callback(data);
	              },
	              error:function(){
	              	return {errcode:"01", errmsg:"获取公众号的appid失败!"};
	              }          
	  		});
	  	},
      
      checkUser:function(generalid,memberid,openid,callback){
            $.ajax({
	             url:this.root+"/wxutil/checkUser",
	             type:"POST",
	             data:{generalid:generalid,memberid:memberid,openid:openid,ts:(new Date()).getTime()},
	             dataType:"json",
	             success:function(data){
	                callback(data);
	             },
	             error:function(){
	                return {errcode:"03",errmsg:"校验用户失败!"}
	             }          
           });
      },
      
      getMember:function(generalid,openid,callback){
            $.ajax({
	             url:this.root+"/wxutil/getMember",
	             type:"POST",
	             data:{generalid:generalid,openid:openid,ts:(new Date()).getTime()},
	             dataType:"json",
	             success:function(data){
	                callback(data);
	             },
	             error:function(){
	                return {errcode:"03",errmsg:"获取用户失败!"}
	             }          
           });
      },
     
     /**
     *判断是否是微信浏览器
     * @author Bill
	 * @version 1.0
	 * @Since  2013-12-18
     */ 
    isWeixin:function(){
		var ua = navigator.userAgent.toLowerCase();
		if(ua.match(/MicroMessenger/i)=="micromessenger") {
			return true;
	 	} else {
			return false;
		}
	},
	
	/**
     *判断是否是iPhone手机
     * @author Bill
	 * @version 1.0
	 * @Since  2013-12-18
     */ 
    isIphone:function(){
		var ua = navigator.userAgent.toLowerCase();
		if(ua.match(/iPhone/i)=="iphone") {
			return true;
	 	} else {
			return false;
		}
	},
	
	/**
     *判断是否是Android手机
     * @author Bill
	 * @version 1.0
	 * @Since  2013-12-18
     */ 
    isAndroid:function(){
		var ua = navigator.userAgent.toLowerCase();
		if(ua.match(/Android/i)=="android") {
			return true;
	 	} else {
			return false;
		}
	},
	/**
	 *判断浏览器是否支持本地存储
	 */
	 isStorage:function(){
	   if(window.localStorage)return true;
	   return false; 
	 },
	 
	 /**
	  *设置本地存储值
	  *
	  */
	  setStorage:function(key,value){
	    localStorage[key]=value;
	    return localStorage;
	  },
	  /**
	  * 设置本地存储值
	  */
	  getStorage:function(key){
	    return localStorage[key];
	  },
	  /**
	   *检测key对应的值是否和被检测值相同
	  */
	  testStorage:function(key,testValue){
	       var key_value = localStorage[key];
	       if(checkMParam(testValue) && key_value===testValue)return true;
	       return false;
	  },
	  
	  /**
	  *监测参数
	  */
	 checkMParam:function(value){
		if(value != null && value != undefined && value != "" && value != "null" && value != "NULL" && value != "undefined" && value != "UNDEFINED"){
			return true;
		}
	   return false;
	},
	
	/**
	 * add by wayne 2014-04-22 开始
	 * sessionStorage方法 
	 * 针对一个session进行数据存储。当用户关闭浏览器窗口后，数据会被删除。
	 *判断浏览器是否支持本地存储
	 */
	 isSessionStorage:function(){
	   if(window.sessionStorage)
		   return true;
	   else
		   return false; 
	 },
	 
	 /**
	  *设置本地存储值
	  *
	  */
	  setSessionStorage:function(key,value){
	    sessionStorage.setItem(key,value);
	    return sessionStorage;
	  },
	  /**
	  * 设置本地存储值
	  */
	  getSessionStorage:function(key){
	    return sessionStorage.getItem(key);
	  },
	  /**
	   *检测key对应的值是否和被检测值相同
	  */
	  testSessionStorage:function(key,testValue){
	       var key_value = sessionStorage.getItem(key);
	       if(checkMParam(testValue) && key_value===testValue)return true;
	       return false;
	  }
	  /**add by wayne 2014-04-22  结束 */

}


 
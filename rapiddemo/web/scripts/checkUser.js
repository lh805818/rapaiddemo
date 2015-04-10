

function checkUser(_generalid,_memberid){
	var vilidResult = checkMCookie("vilidCodeP"+_generalid, _generalid+"P"+_memberid);
	if(vilidResult == false){
		var code = WXUtil.getUrlParam("code");
		if(code == null || code==undefined){
			WXUtil.toGetCode(_generalid);
		}else{
	    	WXUtil.getOpenidByCode(_generalid,function(data){
		        var errcode =  data.errcode; 
		        if(errcode){
		        	//alert(data.errmsg);
		        }else{
		            var openid = data.openid;
		            WXUtil.checkUser(_generalid,_memberid,openid,function(data){
		            	if(data.ispassed == 1){
		                	setMCookie("vilidCodeP"+_generalid, _generalid+"P" + _memberid, null);
		                }else{
		                	if(data.ismember == 1){
		                    	setMCookie("vilidCodeP"+_generalid, _generalid+"P" + data.memberid, null);
		                        //定向到首页
		                        window.location.href = WXUtil.root+"/product/index/"+data.memberid+"/"+data.secret;
		                	}else{
								var tip = '<div id="tip" style="width:100%;height:100%;position: absolute; float: left;color:#FFF; top:0;left:0; z-index: 99999;font-size:16px;text-align:center;"><img src=\''+WXUtil.root+'/skins/imageswap/box-bg.png\' style="float:left;width:100%;"/></div>';
								$(tip).appendTo($("body"));
		                    }
		                }
					}); 
				}
				WXUtil.hideToolbar();
			});
		} 
	}else{
		WXUtil.hideToolbar();
	}
}

/** 鉴权,增加回调 */
function checkUserRes(_generalid,_memberid,callback){
	var code = WXUtil.getUrlParam("code");
	
	if(code == null || code==undefined){
		WXUtil.toGetCode(_generalid);
	}else{
		WXUtil.getOpenidByCode(_generalid,function(data){
        	var errcode =  data.errcode; 
            if(errcode){
            	callback(data);
            }else{
                var openid = data.openid;
                WXUtil.checkUser(_generalid,_memberid,openid,function(data){
                	callback(data);
                }); 
            }
			WXUtil.hideToolbar();
		});
	}
}

/** 2014-04-17 add by wayne
 * 获取openid 
 * 优先获取本地的sessionStorage的key，value
 * */
function getOpenidForShopid(_shopid, callback){

	var openid =get_Storage("openid_"+_shopid);
    if(openid){
    	if (get_Storage("userionfo_s") == "true"){
			 set_Storage("userionfo_s","") ; //清空获取用户信息的标识
			 var code = WXUtil.getUrlParam("code");
		     if(code == null || code == undefined){
		     	return;
		     }
             var hes = get_Storage("headpath_s");
	         $.post(hes + "/wxutil/getWxUserInfo",{code:code,shopid:_shopid, ts:(new Date()).getTime()},
	        	  function(){});
        	 if (typeof checkCallback == "function"){
         	      checkCallback();
             }
		}else{
			callback({openid:openid});
		}
     
    }else{
		var code = WXUtil.getUrlParam("code");
		if(code == null || code == undefined){
			WXUtil.toGetCode(_shopid);
		}else{
			WXUtil.getOpenidByCode(_shopid, function(data){
				openid = data.openid;
	            if(!data.errcode){
	            	set_Storage("openid_"+_shopid,openid);
	            }
	            callback(data);
			});
			

		}
    }
}

/** 2014-04-17 add by wayne
 * 微信服务号url
 * 获取openid 
 * */
function getOpenidForUrlWxfwh(_shopid,urlWxfwh){
	var redirect_uri = window.location.href;
	//微信服务号url :/mecom/{shopid} 参数redirect_url
    var param = "/mecom/" + _shopid + "?ts="+new Date().getTime()+"&redirect_url="+ encodeURIComponent(redirect_uri);
    var url = urlWxfwh + param;
    window.location.href = url;
}


/**
 * add by wayne 2014-04-22 开始
 * sessionStorage方法 
 * 针对一个session进行数据存储。当用户关闭浏览器窗口后，数据会被删除。
 *判断浏览器是否支持本地存储
 */
/**设置key，value到本地sessionStorage，自动监测浏览器的支持情况，优先选择本地存储**/
function set_Storage(key,value){
    if(WXUtil.isSessionStorage()){
        WXUtil.setSessionStorage(key,value);
    }
}
/**获取key，value到本地sessionStorage，自动监测浏览器的支持情况，优先选择本地存储**/
function get_Storage(key){
    if(WXUtil.isSessionStorage()){
        return WXUtil.getSessionStorage(key);
    }
}

/**检测key对应的value和本地sessionStorage存储的value是否相同，自动监测浏览器的支持情况，优先选择本地存储**/
function test_Storage(key,testvalue){
    if(WXUtil.isStorage()){
        return WXUtil.testSessionStorage(key,testvalue);
    }
}
/**add by wayne 2014-04-22  结束 */

/**
 * add by wayne 2014-05-15 开始
 * 设置打开链接,去掉连接中的服务号返回的链接参数openid
 *
 */
function set_Location_delOpenid(redirect_url){
	var openidUrl = WXUtil.getUrlParam("openid");
	if(openidUrl){
		//服务号返回的链接参数中的openid=openidUrl 替换为1=1
		redirect_url=redirect_url.replace("openid="+openidUrl,"");
	}
	
	window.location.href = redirect_url;
}

//微信浏览器登陆  获取wxno
function setOpenidForShopid(headpath,shopid,urlWxfwh){
	var isWxBrowser = WXUtil.isWeixin();
	if(isWxBrowser){
		WXUtil.setPrefix(headpath);
		//微信浏览器登陆
		//隐藏网页底部导航栏
		//WXUtil.hideToolbar();
	    var openid =get_Storage("openid_"+shopid);
	    
	    //微信首次进入 
	    if(!openid){
	    	var openidUrl = WXUtil.getUrlParam("openid");
	    	if(openidUrl){
	    		//服务号返回的链接参数中的openid 保存缓存
	    		set_Storage("openid_"+shopid,openidUrl);
	    		//通过访问微信号 生成访问客户
	    		setVerifyWxNo(headpath,shopid,openidUrl);
	    		
	    		var redirect_url = window.location.href;
		    	set_Location_delOpenid(redirect_url);
	    	}else
	    	{
	    		//通过服务号请求openid
	    		getOpenidForUrlWxfwh(shopid,urlWxfwh); 		
	    	}
	    	return;
	    }

	  	//保存缓存后  直接读取缓存中的openid
	    if(get_Storage("openid_"+shopid))
	    {
	    	//setVerifyWxNo(headpath,shopid,get_Storage("openid_"+shopid));
		}else
		{
			window.location.href = headpath+"/whome/error";
		}
	    
	}
}

//验证当前微信号是否为电商会员
function setVerifyWxNo(headpath,shopid,wxno){
	var buyerid=get_Storage("buyerid");
	if(!buyerid || buyerid==0){
		 $.ajax({
	         url:headpath+"/wxutil/verifyWxNoForIndex",
	         type:"POST",
	         data:{shopid:shopid,wxno:wxno},
	         dataType:"json",
	         async:false,
	         success:function(data){
	              if(data.flag == 0){//验证成功
                 
                 	 var buyerid = data.buyerid;
	          		 if(buyerid){
					     set_Storage("buyerid", buyerid);
					     bid = buyerid; //验证后重新赋值bid,因为bid在footer页面做初始化。
					     
					 }
	           	    if (data.refreshToken && data.refreshToken == 1){
	           	    	set_Storage("userionfo_s","true"); //需要采用网页授权获取用户信息
	           	    	WXUtil.setPrefix(headpath);
	           	    	WXUtil.toGetUserinfoCode(shopid);
	           	    	return;
	           	    }
	           	    if (typeof checkCallback == "function"){
	          	        checkCallback();
	          	    }
		          }
	         }          
	     });
	}
}
/**add by wayne 2014-05-15  结束 */

//微信浏览器登陆  获取wxno
function setOpenidForShopid2(headpath,shopid){
	var isWxBrowser = WXUtil.isWeixin();
	
	if(isWxBrowser){
		WXUtil.setPrefix(headpath);
		set_Storage("headpath_s" ,headpath);
		getOpenidForShopid(shopid,function(data){
	         if(!data.errcode){             
	             var gdOpenid=data.openid;
			     //通过访问微信号 生成访问客户
			     setVerifyWxNo(headpath,shopid,gdOpenid);
	         }else{
			    //window.location.href = headpath+"/whome/error"; 
	        	 var ec = get_Storage("errorcode_count");
	        	 if (ec && ec == 2){
	        		 window.location.href = headpath+"/whome/error"; //两次错误，就不再获取
	        		 return;
	        	 }else{
	        		 if (!ec){
	        			 set_Storage("errorcode_count",1);
	        		 }else{
	        			 set_Storage("errorcode_count",parseInt(ec) + 1);
	        		 }
	        		 window.location.href =  window.location.href.replace(/(^|&)code=([^&]*)/g, "").replace(/(^|\?)code=([^&]*)/,"?hyxt=hyxt"); //去掉code参数，重新开始
	        	 }
		     }
		});
	}
}


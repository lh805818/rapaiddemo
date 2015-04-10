package com.company.project.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.company.project.service.WeixinService;
import com.company.project.util.Util;

import javacommon.base.BaseRestSpringController;

@Controller
@RequestMapping("/wxapi")
public class WeixinApiController extends BaseRestSpringController<Object, java.lang.Long>{
	public static final Logger log = Logger.getLogger(WeixinApiController.class);
	public static final String WX_TOKEN = "weixin";
	@Autowired
	private WeixinService wxService;
	/**
	 * 微信回调地址
	 *
	 * @author qincd
	 * @throws IOException 
	 * @date Nov 3, 2014 4:01:42 PM
	 */
	@RequestMapping(method=RequestMethod.GET)
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
		// 微信会在配置的回调地址上加上signature,nonce,timestamp,echostr4个参数
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		
		log.info("微信传递的参数：");
		log.info("signature："+signature);
		log.info("timestamp："+timestamp);
		log.info("nonce："+nonce);
		log.info("echostr："+echostr);
		
		if (StringUtils.isEmpty(signature)) {
			return;
		}
		// 1).排序
		String sortString = sort(WX_TOKEN, timestamp, nonce);
		// 2).加密
		String mytoken = Util.sha1(sortString);
		// 3).校验签名
		if (StringUtils.isNotEmpty(mytoken) && mytoken.equals(signature)) {
			log.info("签名校验通过。");
			response.getWriter().println(echostr);
		}
		else {
			log.warn("签名校验失败。");
		}
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
		// 处理请求、响应
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String message = wxService.processRequest(request);
		response.getWriter().println(message);
	}
	
	/**
	 * 将token,timestamp,nonce按字典序排序，并返回拼接的字符串
	 *
	 * @author qincd
	 * @date Nov 3, 2014 4:09:43 PM
	 */
	public static String sort(String token,String timestamp,String nonce) {
		String[] strArray = {token,timestamp,nonce};
		Arrays.sort(strArray);
		
		StringBuilder sbuilder = new StringBuilder();
		for (String str : strArray) {
			sbuilder.append(str);
		}
		
		return sbuilder.toString();
	}
	
	
}

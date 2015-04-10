package com.company.project.controller;

import java.util.List;

import javacommon.base.BaseRestSpringController;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.project.cache.ehcache.model.Operator;
import com.company.project.cache.ehcache.util.CacheCenter;

@Controller
@RequestMapping(value="/manage")
public class LoginController extends BaseRestSpringController<Object, java.lang.Long>{
	public final static Log log = LogFactory.getLog(LoginController.class);
	
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request) {
		Operator operator = (Operator) request.getSession().getAttribute("user");
		if (operator == null) {
			return "/login";
		}
		log.info("进入首页。");
		return "/index";
	}
	
	@RequestMapping(value="/login")
	public String login(Model m,HttpServletRequest request) {
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		
		if (StringUtils.isEmpty(userid) || StringUtils.isEmpty(password)) {
			log.error("userid and password can't be null!");
			return "/login";
		}
		
		log.info("login info:\nuserid=" + userid + ",password=" + password);
		
		Operator operator = CacheCenter.getOperator(userid);
		if (operator == null) {
			log.error("user " + userid + " doesn't exist!");
			return "/login";
		}
		request.getSession().setAttribute("user", operator);
		m.addAttribute("user", operator);
		
		List<String> myColors = CacheCenter.getColors();
		m.addAttribute("colors", myColors);
		
		return "forward:/manage/index";
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "/login";
	}
}

package com.company.project.controller;

import javacommon.base.BaseRestSpringController;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.project.model.Club;
import com.company.project.model.TuserInfo;

@Controller
@RequestMapping("/jqueryform")
public class JQueryFormController extends BaseRestSpringController<Object, java.lang.Integer>{
	private final static Log log = LogFactory.getLog(JQueryFormController.class);
	
	@RequestMapping(value="/test")
	@ResponseBody
	public void test(HttpServletRequest request,Club club) {
		String username = request.getParameter("name");
		String age = request.getParameter("age");
		log.info("username:" + username + ",age=" + age);
		
		if (club != null && club.getUserList() != null && !club.getUserList().isEmpty()) {
			for (TuserInfo ti : club.getUserList()) {
				System.out.println(ti);
			}
		}
	}
}

package com.company.project.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javacommon.base.BaseRestSpringController;

@Controller
@RequestMapping(value="/jsonp/")
public class JsonpController extends BaseRestSpringController<Object, Long>{
	private static Logger logger = Logger.getLogger(JsonpController.class);
	
	@RequestMapping(value="tickets")
	public String tickets(@RequestParam("code") String code,@RequestParam("callback") String callback,HttpServletResponse response) throws IOException {
		logger.info("code:" + code);
		logger.info("callback:" + callback);
		
		String scs = ""+callback+"({'code':'SZ1988','price':842,'tickets':29})";
		response.getWriter().println(scs);
		
		logger.info("method called success...");
		
		return null;
	}
}

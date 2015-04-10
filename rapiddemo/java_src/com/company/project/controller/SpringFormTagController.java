/**
 *
 * @author 秦慈东
 * @date Oct 13, 2014 2:47:25 PM
 */
package com.company.project.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.org.rapid_framework.web.scope.Flash;

import com.company.project.model.Club;
import com.company.project.model.TuserInfo;

import javacommon.base.BaseRestSpringController;

/**
 * @author qince
 *
 */
@Controller
@RequestMapping(value="/manage/spring/form/tags")
public class SpringFormTagController extends BaseRestSpringController<TuserInfo,java.lang.Long> {

	@RequestMapping(value="/binding")
	@ResponseBody
	public Map<String,String> binding(@Valid TuserInfo user,BindingResult errors) {
		if (errors.hasErrors()) {
			Flash.current().error("error", "数据错误");
		}
		
		System.out.println(user);
		
		Map<String,String> result = new HashMap<String, String>();
		result.put("msg", "保存成功");
		result.put("flag", "1");
		
		return result;
	}
	
	/**
	 * 简单数据类型绑定
	 *
	 * @author 秦慈东
	 * @date Oct 14, 2014 2:10:52 PM
	 */
	@RequestMapping(value="/binding/simple")
	public String simpleBinding(Model m,String username,String age) {
		System.out.println("username:" + username);
		System.out.println("age:" + age);
		
		m.addAttribute("username", username);
		m.addAttribute("age", age);
		
		return "/formtags/show";
	}
	
	/**
	 * 简单对象绑定
	 *
	 * @author 秦慈东
	 * @date Oct 14, 2014 2:17:56 PM
	 */
	@RequestMapping(value="/binding/simpleObject")
	public String simpleObjectBinding(Model m,TuserInfo user) {
		System.out.println("username:" + user.getUsername());
		System.out.println("age:" + user.getAge());
		
		m.addAttribute("user", user);
		
		return "/formtags/show";
	}
	
	/**
	 * List数据类型绑定
	 *
	 * @author 秦慈东
	 * @date Oct 14, 2014 2:24:02 PM
	 */
	@RequestMapping(value="/binding/list")
	public String listBinding(Model m,Club club) {
		for (TuserInfo user : club.getUserList()) {
			System.out.println("username:" + user.getUsername());
			System.out.println("age:" + user.getAge());
		}
		
		m.addAttribute("club", club);
		return "/formtags/show";
	}
	
	/**
	 * Map数据类型绑定
	 *
	 * @author 秦慈东
	 * @date Oct 14, 2014 2:54:02 PM
	 */
	@RequestMapping(value="/binding/map")
	public String mapBinding(Model m,Club club) {
		Map<String,TuserInfo> userMap = club.getUserMap();
		for (Iterator<String> it = userMap.keySet().iterator();it.hasNext();) {
			String key = it.next();
			TuserInfo user = userMap.get(key);
			System.out.println(key + " 的信息：");
			System.out.println("username:" + user.getUsername());
			System.out.println("age:" + user.getAge());
			System.out.println();
		}
		
		m.addAttribute("club", club);
		return "/formtags/show";
	}
}

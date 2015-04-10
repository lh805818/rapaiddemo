package com.company.project.test;

@RequestMapping(value="/manage/login")
public class MyTestController {

	@RequestMapping(value="/login")
	public String login() {
		System.out.println(this.getClass().getName() + ".login().");
		return "/index";
	}
	
	@RequestMapping(value="/index")
	public String index() {
		System.out.println(this.getClass().getName() + ".index().");
		return "/index";
	}
}

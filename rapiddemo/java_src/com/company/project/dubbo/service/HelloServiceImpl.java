package com.company.project.dubbo.service;

import java.util.ArrayList;
import java.util.List;

public class HelloServiceImpl implements HelloService{

	@Override
	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		users.add(new User("test1",29));
		users.add(new User("test2",22));
		users.add(new User("test3",18));
		return users;
	}

	@Override
	public String sayHello(String name) {
		return "hello," + name;
	}

}

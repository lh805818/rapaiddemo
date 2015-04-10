package com.company.project.dubbo.service;

import java.util.List;

public interface HelloService {

	public String sayHello(String name);
	
	public List<User> getUsers();
}

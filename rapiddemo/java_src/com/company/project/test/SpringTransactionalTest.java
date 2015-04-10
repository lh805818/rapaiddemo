package com.company.project.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.project.model.TuserInfo;
import com.company.project.service.TuserInfoManager;

public class SpringTransactionalTest {

	/**
	 *
	 * @author qincd
	 * @email qincd@hyxt.com
	 * @date Dec 26, 2014 2:16:39 PM
	 */
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/*.xml");
		TuserInfoManager manager = ac.getBean(TuserInfoManager.class);
		TuserInfo u = new TuserInfo();
		u.setUsername("tests");
		u.setPassword("123456");
		try {
			manager.testSave(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

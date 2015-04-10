package com.company.project.cache.ehcache.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.company.project.cache.ehcache.model.Operator;

public class CacheDatabase {
	public final static Logger log = Logger.getLogger(CacheDatabase.class);
	/**
	 * 模拟数据库，假定opeartorMap存放都是数据库t_operator表的数据
	 * operatorMap的key是Operator的id
	 */
	public final static Map<String, Operator> operatorMap = new HashMap<String, Operator>();
	public final static List<String> colors = new ArrayList<String>();
	
	static {
		log.info("init operator data...");
		
		// 这里放2个操作员的信息
		Operator o1 = new Operator("10001","admin","13585745515");
		Operator o2 = new Operator("10002","system","18258525522");
		Operator o3 = new Operator("10003","test","15656555552");
		
		operatorMap.put("10001", o1);
		operatorMap.put("10002", o2);
		operatorMap.put("10003", o3);
		
		log.info("init color data...");
		colors.add("black");
		colors.add("green");
		colors.add("yellow");
		colors.add("blue");
		colors.add("red");
	}
	
	public static Operator getOperator(String id) {
		if (StringUtils.isEmpty(id)) return null;
		return operatorMap.get(id);
	}
	
}

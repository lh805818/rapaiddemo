package com.company.project.cache.ehcache.test;

import org.junit.Test;

import com.company.project.cache.ehcache.model.Operator;
import com.company.project.cache.ehcache.util.CacheCenter;


public class CacheTest {

	@Test
	public void testCache() {
		// 从缓存中取数据
		Operator t = CacheCenter.getOperator("10001");
		System.out.println(t);
		t = CacheCenter.getOperator("10005");
	}
}

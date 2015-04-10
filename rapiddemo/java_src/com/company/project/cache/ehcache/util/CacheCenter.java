package com.company.project.cache.ehcache.util;

import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.company.project.cache.ehcache.dao.CacheDatabase;
import com.company.project.cache.ehcache.model.Operator;

public class CacheCenter {
	public final static Logger log = Logger.getLogger(CacheCenter.class);
	public final static CacheManager cacheManager = new CacheManager();
	
	public static Operator getOperator(String id) {
		if (StringUtils.isEmpty(id)) throw new NullPointerException("操作员ID不能为空！");
		// 从缓存中读取
		log.info("从缓存中读取数据...");
		
		Operator t = null;
		Cache cache = cacheManager.getCache("operator");
		Element element = cache.get(id);
		
		if (element == null) {
			log.info("缓存中无数据，从数据库读取...");
			
			// 从数据库读取数据，并放入缓存
			t = CacheDatabase.getOperator(id);
			if (t == null) {
				log.info("数据库没有对应的记录！操作员id=" + id);
			}
			else {
				log.info("从数据库读取的数据是：" + t);
				log.info("将数据放入缓存...");
				
				Element ele = new Element(id,t);
				cache.put(ele);
			}
		}
		else {
			t = (Operator) element.getValue();
			log.info("从缓存中读取的数据是："+ t);
		}
		
		return t;
	}
	
	public static void removeCacheElement(Object key) {
		Cache cache = cacheManager.getCache("operator");
		Element ele = cache.get(key);
		if (ele != null) {
			cache.remove(key);
		}
	}
	public static void addCacheElement(Object key,Object value) {
		Cache cache = cacheManager.getCache("operator");
		cache.put(new Element(key,value));
		log.info("add element to cache.key=" + key + ",value=" + value);
	}
	
	public static Object getValue(Object key) {
		Cache cache = cacheManager.getCache("operator");
		Element ele = cache.get(key);
		return ele == null ? null : ele.getValue();
	}
	
	public static List<String> getColors() {
		// get colors from cache
		log.info("从缓存中取color");
		List<String> colors = (List<String>) getValue("colors");
		if (colors == null) {
			log.info("缓存中没有color");
			log.info("从数据库取color");
			
			colors = CacheDatabase.colors;
			
			log.info("添加color到缓存");
			addCacheElement("colors", colors);
		}
		
		return colors;
	}
}

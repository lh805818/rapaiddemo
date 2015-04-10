package com.company.project.service;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * Created by qince on 2015/4/7.
 */
public class CacheService {
    private final static CacheManager cacheManager = new CacheManager();
    private final static String cacheName = "operator";

    public synchronized static void putCache(String key,Object value) {
        Element element = new Element(key,value);
        Cache cache = cacheManager.getCache(cacheName);
        cache.put(element);
    }

    public static Object getCacheValue(String key) {
        Cache cache = cacheManager.getCache(cacheName);
        Element element = cache.get(key);
        if (null != element) {
            return element.getValue();
        }
        return null;
    }
}

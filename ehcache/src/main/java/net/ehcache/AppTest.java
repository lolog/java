package net.ehcache;

import net.ehcache.context.AppContext;
import net.ehcache.spring.JCache;

import org.junit.Test;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

public class AppTest {
	@Test
	public void springCache() {
		CacheManager cacheManager = AppContext.getBean("cacheManager", CacheManager.class);
		Cache cache = cacheManager.getCache("cache_test");
		for (int i=0; i<10; i++) {
			cache.put("key_" + i, "value_" + i);
		}
		for (int i=0; i<10; i++) {
			System.out.println(cache.get("key_" + i).get());
		}
	}
	
	@Test
	public void jCache() {
		JCache jCache = new JCache();
		for (int i=0; i<10; i++) {
			jCache.put("key_" + i, "value_" + i);
		}
		
		for (int i=0; i<10; i++) {
			System.out.println(jCache.get("key_" + i));;
		}
	}
}

package net.ehcache.spring;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class JCache {
	private static final CacheManager cacheManager = new CacheManager();
	private Cache cache;

	public JCache() {
		this.cache = cacheManager.getCache("cache_test");
	}

	public Cache getCache() {
		return cache;
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	public Object get(String cacheKey) {
		Element e = cache.get(cacheKey);
		if (e == null) {
			return null;
		}
		return e.getObjectKey();
	}

	public void put(String cacheKey, Object result) {
		Element element = new Element(cacheKey, result);
		cache.put(element);
	}

}

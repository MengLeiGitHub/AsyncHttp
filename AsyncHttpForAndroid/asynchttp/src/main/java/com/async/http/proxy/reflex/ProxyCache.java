package com.async.http.proxy.reflex;

import com.async.http.proxy.entity.CreatorBeans;

import java.util.HashMap;

public class ProxyCache {

	private static HashMap<String, CreatorBeans> cache = new HashMap<String, CreatorBeans>();

	public static void cache(String key, CreatorBeans creatorBeans) {
		synchronized (cache) {
			if (!cache.containsKey(key)) {
				cache.put(key, creatorBeans);
			}

		}

	}

	public static CreatorBeans getCache(String key) {

		return cache.get(key);
	}

}

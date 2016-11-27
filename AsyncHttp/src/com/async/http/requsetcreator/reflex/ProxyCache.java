package com.async.http.requsetcreator.reflex;

import java.util.HashMap;

import com.async.http.requsetcreator.entity.CreatorBeans;

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

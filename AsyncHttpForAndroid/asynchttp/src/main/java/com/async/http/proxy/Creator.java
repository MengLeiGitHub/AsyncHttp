package com.async.http.proxy;

import com.async.http.proxy.entity.CreatorBeans;
import com.async.http.proxy.reflex.Analysis;
import com.async.http.proxy.reflex.AssemblyDevice;
import com.async.http.proxy.reflex.ProxyCache;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
 
/**
 * 创建者
 * @author ML
 *
 */

public class Creator {

	public static <T> T creator(final Class tclass)
			throws Exception {

		return (T) Proxy.newProxyInstance(tclass.getClassLoader(),
				new Class<?>[] { tclass }, new InvocationHandler() {

					public Object invoke(Object arg0, Method method,
							Object[] param) throws Throwable {
						// TODO Auto-generated method stub

						CreatorBeans creatorcache = ProxyCache.getCache(method
								.toGenericString());
						if (creatorcache != null) {

						} else {
							creatorcache = new Analysis().dealWith(method);
							ProxyCache.cache(method.toGenericString(),
									creatorcache);
						}
						CreatorBeans creatorcache2=	new  AssemblyDevice().startAssembly(creatorcache, param);
						ProxyRequester proxyRequester =new ProxyRequester();
						proxyRequester.setCreatorBeans(creatorcache2);
						return proxyRequester;
					}

				});

	 
	}

}

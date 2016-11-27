package com.async.http.requsetcreator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

 
import com.async.http.requsetcreator.entity.CreatorBeans;
import com.async.http.requsetcreator.reflex.Analysis;
import com.async.http.requsetcreator.reflex.AssemblyDevice;
import com.async.http.requsetcreator.reflex.ProxyCache;
 
/**
 * ´´½¨Õß
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
						RunA  runA=new RunA();
						runA.setCreatorBeans(creatorcache2);
						return runA;
					}

				});

	 
	}

}

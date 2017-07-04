package com.async.http.android;

import com.async.http.proxy.ProxyRequester;
import com.async.http.proxy.entity.CreatorBeans;
import com.async.http.proxy.reflex.Analysis;
import com.async.http.proxy.reflex.AssemblyDevice;
import com.async.http.proxy.reflex.ProxyCache;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by admin on 2016-11-27.
 */

public class ProxyCreater {


    public static <T> T creator(final Class tclass){

        return (T) Proxy.newProxyInstance(tclass.getClassLoader(),
                new Class<?>[]{tclass}, new InvocationHandler() {

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
                        CreatorBeans creatorcache2 = new AssemblyDevice().startAssembly(creatorcache, param);
                        ProxyRequester proxyRequester = new CProxyRequester();
                        proxyRequester.setCreatorBeans(creatorcache2);
                        return proxyRequester;
                    }

                });

    }
}
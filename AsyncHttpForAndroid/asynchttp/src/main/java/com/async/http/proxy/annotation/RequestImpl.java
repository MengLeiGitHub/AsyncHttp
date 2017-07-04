package com.async.http.proxy.annotation;


import com.async.http.request2.BaseRequest;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by admin on 2017-04-27.
 */
@Inherited
@Target(METHOD)
@Retention(RUNTIME)
public @interface RequestImpl {

    public Class<? extends BaseRequest> impl();

}

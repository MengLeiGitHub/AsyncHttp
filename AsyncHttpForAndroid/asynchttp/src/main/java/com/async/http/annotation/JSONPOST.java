package com.async.http.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/** Make a POST request. */
@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface JSONPOST {
  /**
   * A relative or absolute path, or full URL of the endpoint. 
   *  
   */
  String value() default "";
}

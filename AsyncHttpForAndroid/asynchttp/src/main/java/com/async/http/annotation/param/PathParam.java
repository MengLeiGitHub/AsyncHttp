package com.async.http.annotation.param;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/** Make a POST request. */
@Documented
@Target(PARAMETER)
@Retention(RUNTIME)
public @interface PathParam {
  /**
   * A relative or absolute path, or full URL of the endpoint. 
   *  
   */
  String value() default "";
}

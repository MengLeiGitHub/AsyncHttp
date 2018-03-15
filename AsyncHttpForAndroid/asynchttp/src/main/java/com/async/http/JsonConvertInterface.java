package com.async.http;

import java.lang.reflect.Type;

/**
 * Created by ML on 2018/3/15.
 */

public interface JsonConvertInterface {

    public  String serialize(Object object) ;

    public  <T> T deserialize(String json, Class<T> clz);
    public  <T> T deserialize(String json, Type type);



    }

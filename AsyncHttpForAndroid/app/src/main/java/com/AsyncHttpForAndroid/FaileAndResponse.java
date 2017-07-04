package com.AsyncHttpForAndroid;

import com.async.http.entity.ResponseBody;

/**
 * Created by admin on 2016-11-05.
 */
public class FaileAndResponse<T> {
    public  Exception e;
    public  ResponseBody<T> responseBody;

    public  FaileAndResponse(Exception e,ResponseBody<T> responseBody){
        this.e=e;
        this.responseBody=responseBody;
    }

}

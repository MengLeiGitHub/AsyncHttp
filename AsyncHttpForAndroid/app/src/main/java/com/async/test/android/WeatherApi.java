package com.async.test.android;


import com.async.http.android.CProxyRequester;
import com.async.http.annotation.GET;
import com.async.http.annotation.POST;
import com.async.http.annotation.param.Param;
import com.async.http.proxy.annotation.RequestImpl;
import com.async.test.android.NOkeyTest.NOkeyRequest;

/**
 * Created by admin on 2017-04-12.
 */

public interface WeatherApi {

    @RequestImpl(impl = NOkeyRequest.class)
    @POST("http://php.weather.sina.com.cn/xml.php?city=%E6%9D%AD%E5%B7%9E&password=DJOYnieT8234jlsK&day=0")
    CProxyRequester<String> getWeather(@Param String[] strings,@Param String[] s);

}

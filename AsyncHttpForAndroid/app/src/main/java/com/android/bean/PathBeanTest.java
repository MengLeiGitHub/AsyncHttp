package com.android.bean;

import com.AsyncHttpForAndroid.CProxyRequester;
import com.async.http.annotation.GET;
import com.async.http.annotation.JSONPOST;
import com.async.http.annotation.POST;
import com.async.http.annotation.param.Param;
import com.async.http.annotation.param.PathParam;

/**
 * Created by admin on 2016-11-27.
 */

public interface PathBeanTest {

    @JSONPOST("rest/common/user/login.do")
    public CProxyRequester test(@PathParam("ip") String ip, @Param("username") String username, @Param("password") String passwork);


    @GET("rest/common/allFactorys.do")
    public CProxyRequester getAll();


    @POST("http://120.24.249.69/parkhero/v0.1/billing/onlinepay/")
    public CProxyRequester testss(@Param("out_trade_no") String out_trade_no, @Param("paypasswd") String paypasswd, @Param("paytype") String paytype,
                                  @Param("serviceid") int serviceid, @Param("servicetype") String servicetype);
    @JSONPOST("http://120.24.249.69/parkhero/v0.1/billing/onlinepay/")
    public CProxyRequester testss(@Param("param") String out_trade_no);

}

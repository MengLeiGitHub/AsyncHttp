package com.android.bean;

import com.AsyncHttpForAndroid.CProxyRequester;
import com.async.http.annotation.JSONPOST;
import com.async.http.annotation.param.Param;

/**
 * Created by admin on 2016-12-12.
 */

public interface LoginApi {
    @JSONPOST("rest/common/user/login.do")
    CProxyRequester Login(@Param("username") String username, @Param("password") String pwd);



}

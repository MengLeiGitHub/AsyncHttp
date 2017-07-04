package com.android.bean;

import com.AsyncHttpForAndroid.CProxyRequester;
import com.async.http.annotation.JSONPOST;
import com.async.http.annotation.param.Param;

/**
 * Created by admin on 2016-11-05.
 */
public interface MySelftApi {
    @JSONPOST("rest/common/user/editUserInfo.do")
    CProxyRequester<ResonseEnty<String>> update(@Param("nickname") String realname, @Param("company") String company, @Param("companyAddress") String companyAddress, @Param("position") String position, @Param("info") String info, @Param("sex") String sex);
}

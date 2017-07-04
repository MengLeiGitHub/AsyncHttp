package com.android.bean;

import com.AsyncHttpForAndroid.CProxyRequester;
import com.async.http.annotation.UPLOAD;
import com.async.http.annotation.param.Param;

/**
 * Created by admin on 2016-11-27.
 */

public interface UploadTest {

    @UPLOAD("http://120.26.106.136:8080/rest/common/user/uploadFile.do")
    public CProxyRequester<UploadResultBean> upload(@Param("file") String filepath);
}

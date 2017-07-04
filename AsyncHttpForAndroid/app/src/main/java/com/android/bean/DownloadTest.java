package com.android.bean;


import com.AsyncHttpForAndroid.CProxyRequester;
import com.async.http.annotation.DOWNLOAD;
import com.async.http.annotation.param.Param;
import com.async.http.annotation.param.PathParam;

import java.io.File;

/**
 * Created by admin on 2016-11-27.
 */

public interface DownloadTest {
    //http://ftp-apk.pconline.com.cn/06aa86b4ffad9e8ab6a134a243974899/pub/download/201010/Amap_V8.0.2.2072_android_C021100011457_(Build1703061618)_170307.apk
   //web/factory/attendance/{factoryId}/viewInOnline.do?month=2017-03
    //  @DOWNLOAD("http://ftp-apk.pconline.com.cn/06aa86b4ffad9e8ab6a134a243974899/pub/download/201010/Amap_V8.0.2.2072_android_C021100011457_(Build1703061618)_170307.apk")
    @DOWNLOAD(value = "web/factory/attendance/{factoryId}/viewInOnline.do?month=2017-03")
    public CProxyRequester<File> down(@Param("filepath") String path, @PathParam("factoryId") int factoryId);

}

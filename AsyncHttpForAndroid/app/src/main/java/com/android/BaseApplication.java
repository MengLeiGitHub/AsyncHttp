package com.android;

import android.app.Application;

import com.async.http.AsyncHttp;
import com.async.http.Interceptor2.RequestInterceptorActionInterface;
import com.async.http.request2.BaseRequest;
import com.async.http.request2.RequestConfig;
import com.async.http.request2.entity.Header;
import com.async.http.utils.LogUtils;
import com.squareup.leakcanary.LeakCanary;

import java.util.ArrayList;

/**
 * Created by admin on 2016/10/22.
 */
public class BaseApplication  extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);

        RequestConfig requestConfig = new RequestConfig();
        requestConfig.setConnectTimeout(10000);
        requestConfig.setSocketTimeout(30000);

        requestConfig.setBaseUrl("http://120.26.106.136:8080/");
        ArrayList<Header> headerlist = new ArrayList<Header>();
        headerlist.add(new Header("connection", "Keep-Alive"));
        headerlist.add(new Header("user-agent", "AsyHttp/1.0 ml"));
        headerlist.add(new Header("Accept-Charset", "ISO-8859-1"));
        requestConfig.setHeadList(headerlist);
        //  requestConfig.setBaseUrl("http://120.26.106.136:8080/");
        AsyncHttp.instance().setConfig(requestConfig);

        AsyncHttp.instance().addRequestInterceptor(new RequestInterceptorActionInterface() {
            @Override
            public <T> BaseRequest<T> interceptorAction(BaseRequest<T> baserequest) throws Exception {
                baserequest.addHead(new Header("version", "1.0"));
                baserequest.addHead(new Header("tokenId", "8FA24C888B39405FB46499C62E48A504"));
                baserequest.addHead(new Header("token", "B647A15C00A8407383D3C3016577B4FA"));
                //35C43D51E8C844B69B4AF149A82B40E7
                baserequest.addHead(new Header("appType", "3"));
                //       baserequest.addHead(new Header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
                baserequest.addHead(new Header("ostype", "1"));
                baserequest.addHead(new Header("deviceId", "1231232342342341"));
                baserequest.setBaseUrl("http://120.26.106.136:8080/");
                //         baserequest.addHead(new Header("X-CSRFToken","j9wrzZSrXEc8fVDKEhDV8vWfU8swK9S7bDkVDT9kFPS6WF2RJ6fEjh61eLlahJk2"));
                //       baserequest.addHead(new Header("Cookie","csrftoken=j9wrzZSrXEc8fVDKEhDV8vWfU8swK9S7bDkVDT9kFPS6WF2RJ6fEjh61eLlahJk2;sessionid=refx851le5nndhchqvy0cqffgmjpnglq"));

                return baserequest;
            }
        });
        LogUtils.setDebug(true);
        LogUtils.setIsOpenLogHeaders(true);
    }




}

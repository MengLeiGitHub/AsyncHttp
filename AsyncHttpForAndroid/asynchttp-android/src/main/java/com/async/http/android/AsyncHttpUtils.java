package com.async.http.android;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.async.http.AsyncHttp;
import com.async.http.Interceptor2.RequestInterceptorActionInterface;
import com.async.http.Interceptor2.ResponseInterceptorActionInterface;
import com.async.http.callback.DownProgrossCallback;
import com.async.http.client.HttpMethod;
import com.async.http.constant.Charsets;
import com.async.http.constant.Constents;
import com.async.http.entity.ResponseBody;
import com.async.http.handler.TaskHandler;
import com.async.http.request2.FileRequest;
import com.async.http.request2.JSONRequest;
import com.async.http.request2.RequestConfig;
import com.async.http.request2.StringRequest;
import com.async.http.request2.UploadRequest;
import com.async.http.request2.entity.Header;
import com.async.http.request2.part.FileParamPart;
import com.async.http.request2.part.StringParamPart;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**

 author :ml
 *
 * Created by ml on 2016-11-05.
 */
public class AsyncHttpUtils {
    private static boolean isInit = false;

    public static void init(RequestInterceptorActionInterface requestInterceptor2, ResponseInterceptorActionInterface responseInterceptor2) {
        RequestConfig requestConfig = new RequestConfig();
        requestConfig.setConnectTimeout(10000);
        requestConfig.setSocketTimeout(30000);
        requestConfig.setRequestMethod(HttpMethod.Post);
        ArrayList<Header> headerlist = new ArrayList<Header>();
        headerlist.add(new Header("connection", "Keep-Alive"));
        headerlist.add(new Header("user-agent", "AsyHttp/1.0 ml"));

        headerlist.add(new Header("Accept-Charset", "ISO-8859-1"));

        requestConfig.setHeadList(headerlist);
        AsyncHttp.instance().addRequestInterceptor(requestInterceptor2).addResponseInterceptor(responseInterceptor2);

        AsyncHttp.instance().setConfig(requestConfig);
        isInit = true;
    }



    private static void init(RequestConfig requestConfig ) {
        AsyncHttp.instance().setConfig(requestConfig);
        isInit = true;
    }

    private static void init() {
        RequestConfig requestConfig = new RequestConfig();
        requestConfig.setConnectTimeout(10000);
        requestConfig.setSocketTimeout(30000);
        requestConfig.setRequestMethod(HttpMethod.Post);
        ArrayList<Header> headerlist = new ArrayList<Header>();
        headerlist.add(new Header("connection", "Keep-Alive"));
        headerlist.add(new Header("user-agent", "AsyHttp/1.0 ml"));

      //  headerlist.add(new Header(Constents.CONTENT_TYPE, Constents.TYPE_FORM_DATA));
        requestConfig.setHeadList(headerlist);

        AsyncHttp.instance().setConfig(requestConfig);
        isInit = true;
    }

    private static int Index = 0;

    private static void initConfig(Context context) {

    }


    public static TaskHandler download(String url, final String path, String filename, final DownProgrossCallback<ResponseBody<File>> callback) {
        if (!isInit) init();

        String filepath = path + "/" + filename;

        final FileRequest resReques = new FileRequest(url);
        resReques.setFilepath(filepath);
        resReques.setRequestMethod(HttpMethod.Get);
        resReques.setRetry(true);
        final int indexs = getIndex();

        final Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void dispatchMessage(Message msg) {
                super.dispatchMessage(msg);
                if (msg.what == indexs) {
                    switch (msg.arg1) {
                        case RequestProgress.START:
                            callback.start();
                            break;
                        case RequestProgress.CURRENT:
                            ProgressBean progressBean = (ProgressBean) msg.obj;
                            callback.download_current(progressBean.current, progressBean.total);
                            break;
                        case RequestProgress.SUCCESS:
                            callback.success((ResponseBody<File>) msg.obj);
                            break;
                        case RequestProgress.FINISH:
                            callback.finish();
                            break;
                        case RequestProgress.FAIL:
                            FaileAndResponse<File> fileFaileAndResponse = (FaileAndResponse<File>) msg.obj;
                            callback.fail(fileFaileAndResponse.e, fileFaileAndResponse.responseBody);
                            break;
                    }


                }
            }
        };


        DownCallBack<File> downCallBack = new DownCallBack<>(handler, indexs);

        return AsyncHttp.instance().download(resReques, downCallBack);
    }


    public static  <T> TaskHandler upload(String url, String path, String fileName, final UploadRequestResultCallBack<T> uploadProgrossCallback) {
        if (!isInit) init();

       // String filepath = path;

        UploadRequest resReques = new UploadRequest(url, Charsets.UTF_8);
        resReques.addParam(new FileParamPart(fileName, new File(path), Constents.TYPE_IMAGE));
        resReques.addHead(new Header(Constents.CONTENT_TYPE,Constents.TYPE_FORM_DATA));
        resReques.setRequestMethod(HttpMethod.Post);

        final int indexs = getIndex();

        Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void dispatchMessage(Message msg) {
                super.dispatchMessage(msg);
                if (msg.what == indexs) {
                    switch (msg.arg1) {
                        case RequestProgress.START:
                            uploadProgrossCallback.start();
                            break;
                        case RequestProgress.CURRENT:
                            ProgressBean progressBean = (ProgressBean) msg.obj;
                            uploadProgrossCallback.upload_current(progressBean.current, progressBean.currentFileTotal, progressBean.total);
                            break;
                        case RequestProgress.SUCCESS:
                            ResponseBody<String> responseBody= (ResponseBody<String>) msg.obj;
                            uploadProgrossCallback.success(responseBody);
                            break;
                        case RequestProgress.FINISH:
                            uploadProgrossCallback.finish();
                            break;
                        case RequestProgress.FAIL:
                            FaileAndResponse fileFaileAndResponse = (FaileAndResponse) msg.obj;

                            uploadProgrossCallback.fail(fileFaileAndResponse.e, fileFaileAndResponse.responseBody);
                            break;
                    }


                }
            }
        };
        UploadCallBack uploadCallBack = new UploadCallBack<String>(handler, indexs);
      return   AsyncHttp.instance().UploadRequest(resReques, uploadCallBack);
    }



    public static <T> TaskHandler string(String url, HttpMethod httpMethod, HashMap<String, String> hashMap, final StringRequestResultCallBack<T> httpCallBack) {
      //  if (!isInit) init();


        StringRequest resReques = new StringRequest(url, Charsets.UTF_8);
        if (hashMap == null)
            resReques.setRequestMethod(HttpMethod.Get);
        else {
            resReques.setRequestMethod(httpMethod);
            Iterator<Map.Entry<String, String>> iter = hashMap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<String, String> entry = iter.next();
                String key = entry.getKey();
                String val = entry.getValue();
                resReques.addParam(new StringParamPart(key, val));
            }
        }

 
        final int indexs = getIndex();

        Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void dispatchMessage(Message msg) {
                super.dispatchMessage(msg);
                if (msg.what == indexs) {
                    switch (msg.arg1) {
                        case RequestProgress.START:
                            httpCallBack.start();
                            break;
                        case RequestProgress.SUCCESS:
                            httpCallBack.success((ResponseBody<String>) msg.obj);
                            break;
                        case RequestProgress.FINISH:
                            httpCallBack.finish();
                            break;
                        case RequestProgress.FAIL:
                            FaileAndResponse<String> fileFaileAndResponse = (FaileAndResponse<String>) msg.obj;
                            httpCallBack.fail(fileFaileAndResponse.e, fileFaileAndResponse.responseBody);
                            break;
                    }


                }
            }
        };
        UploadCallBack uploadCallBack = new UploadCallBack<String>(handler, indexs);
        return  AsyncHttp.instance().stringRequest(resReques, uploadCallBack);
    }


    private static synchronized int getIndex() {
        return Index++;
    }

    public static <T> TaskHandler json(String url, HttpMethod httpMethod, HashMap<String, String> hashMap, final StringRequestResultCallBack<T> httpCallBack) {
        if (!isInit) init();


        JSONRequest resReques = new JSONRequest(url, Charsets.UTF_8);

        resReques.addHead(new Header("Content-Type","application/json;charset=UTF-8"));

        if (hashMap == null)
            resReques.setRequestMethod(HttpMethod.Get);
        else {
            resReques.setRequestMethod(httpMethod);
            Iterator<Map.Entry<String, String>> iter = hashMap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<String, String> entry = iter.next();
                String key = entry.getKey();
                String val = entry.getValue();
                resReques.addParam(new StringParamPart(key, val));
            }

        }

        final int indexs = getIndex();

        Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void dispatchMessage(Message msg) {
                super.dispatchMessage(msg);
                if (msg.what == indexs) {
                    switch (msg.arg1) {
                        case RequestProgress.START:
                            httpCallBack.start();
                            break;
                        case RequestProgress.SUCCESS:
                            httpCallBack.success((ResponseBody<String>) msg.obj);
                            break;
                        case RequestProgress.FINISH:
                            httpCallBack.finish();
                            break;
                        case RequestProgress.FAIL:
                            FaileAndResponse<String> fileFaileAndResponse = (FaileAndResponse<String>) msg.obj;
                            httpCallBack.fail(fileFaileAndResponse.e, fileFaileAndResponse.responseBody);
                            break;
                    }


                }
            }
        };
        UploadCallBack uploadCallBack = new UploadCallBack<String>(handler, indexs);
       return AsyncHttp.instance().stringRequest(resReques, uploadCallBack);
    }


}

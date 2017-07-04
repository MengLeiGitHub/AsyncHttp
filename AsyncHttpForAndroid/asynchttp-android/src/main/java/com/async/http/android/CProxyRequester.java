package com.async.http.android;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.async.http.AsyncHttp;
import com.async.http.callback.DownProgrossCallback;
import com.async.http.callback.HttpCallBack;
import com.async.http.callback.UploadProgrossCallback;
import com.async.http.entity.ResponseBody;
import com.async.http.handler.TaskHandler;
import com.async.http.proxy.MIO;
import com.async.http.proxy.ProxyRequester;
import com.async.http.proxy.dao.RequesyTypeFactory;
import com.async.http.proxy.dao.RequesyTypeInterface;
import com.async.http.proxy.entity.CreatorBeans;
import com.async.http.request2.BaseRequest;
import com.async.http.request2.FileRequest;
import com.async.http.request2.StringRequest;
import com.async.http.request2.UploadRequest;

import java.io.File;

/**
 * Created by admin on 2016-11-27.
 */

public final class CProxyRequester<T> extends ProxyRequester {
    private static int Index = 0;
    Observer observer;
    CreatorBeans creatorBeansC;
    private MIO mio;
    BaseRequest baseHttpRequest;

    @Override
    public void setCreatorBeans(CreatorBeans creatorBeans) {
        super.setCreatorBeans(creatorBeans);
        this.creatorBeansC = creatorBeans;
    }

    private void holdRequests() {
        RequesyTypeInterface requesyTypeInterface = new RequesyTypeFactory().getRequestType(creatorBeansC);
        try {
            baseHttpRequest = requesyTypeInterface.holdRequest();


        } catch (Exception e) {

            e.printStackTrace();
        }

    }


    @Override
    public Observer ResultMonitor(MIO mio) {
        this.mio = mio;
        holdRequests();
        if (creatorBeansC.isJSONPOST()) {
            observer = this.new CStringObserver(this);
            return observer;

        }
        if (creatorBeansC.isDownload()) {
            observer = this.new DownloadObserver(this);
            return observer;

        }
        if (creatorBeansC.isUpload()) {
            observer = this.new UploadObserver(this);
            return observer;
        }
        if(creatorBeansC.getRequestImpl()!=null){
            return  observer=new CustomeObserver(this);
        }
        return observer = new CStringObserver(this);
    }
    private TaskHandler  customerStart(){
        if(mio==MIO.MainThread) {
            final int indexs = getIndex();
            final StringRequestResultCallBack<T> httpCallBack = (StringRequestResultCallBack<T>) observer.getCallBack();
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
            return AsyncHttp.instance().newRequest2(baseHttpRequest, uploadCallBack);
        }else {
            return AsyncHttp.instance().newRequest2(baseHttpRequest, observer.getCallBack());
        }
    }
    private TaskHandler stringStart() {
        if(mio==MIO.MainThread){
            final int indexs = getIndex();
            final StringRequestResultCallBack<T> httpCallBack= (StringRequestResultCallBack<T>) observer.getCallBack();
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
            return    AsyncHttp.instance().stringRequest((StringRequest) baseHttpRequest, uploadCallBack);

        }else {
            return    AsyncHttp.instance().stringRequest((StringRequest) baseHttpRequest, observer.getCallBack());

        }
    }

    private TaskHandler uploadstart() {
        if(MIO.MainThread==mio){
            final int indexs = getIndex();
            final UploadRequestResultCallBack<T> uploadProgrossCallback= (UploadRequestResultCallBack<T>) observer.getCallBack();
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
                                FaileAndResponse<String> fileFaileAndResponse = (FaileAndResponse<String>) msg.obj;
                                uploadProgrossCallback.fail(fileFaileAndResponse.e, fileFaileAndResponse.responseBody);
                                break;
                        }


                    }
                }
            };
            UploadCallBack<String> uploadCallBack = new UploadCallBack<String>(handler, indexs);
            return   AsyncHttp.instance().UploadRequest((UploadRequest) baseHttpRequest, uploadCallBack);
        }else {
            return   AsyncHttp.instance().UploadRequest((UploadRequest) baseHttpRequest, (UploadProgrossCallback) observer.getCallBack());

        }
    }

    private TaskHandler downloadStart() {
        if (mio == MIO.MainThread) {
            final int indexs = getIndex();
            final DownProgrossCallback<ResponseBody<File>> callback = (DownProgrossCallback<ResponseBody<File>>) observer.getCallBack();
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
            return AsyncHttp.instance().download((FileRequest) baseHttpRequest, downCallBack);


        } else

            return   AsyncHttp.instance().download((FileRequest) baseHttpRequest, (DownProgrossCallback<ResponseBody<File>>) observer.getCallBack());
    }



    public class CustomeObserver extends Observer<String>{
        HttpCallBack<ResponseBody<String>> callback;
        CProxyRequester proxyRequester;

        public CustomeObserver(CProxyRequester proxyRequester) {
            super(proxyRequester);
            this.proxyRequester=proxyRequester;
        }


        @Override
        public TaskHandler Observation(HttpCallBack<ResponseBody<String>> callback) {
            // TODO Auto-generated method stub
            this.callback = callback;
            return  proxyRequester.customerStart();
        }

        @Override
        public HttpCallBack<ResponseBody<String>> getCallBack() {
            // TODO Auto-generated method stub
            return callback;
        }

    }

    public class CStringObserver extends Observer<String> {


        HttpCallBack<ResponseBody<String>> callback;
        CProxyRequester proxyRequester;

        protected CStringObserver(CProxyRequester proxyRequester) {
            super(proxyRequester);
            this.proxyRequester = proxyRequester;
        }

        public TaskHandler Observation(HttpCallBack<ResponseBody<String>> callback) {
            this.callback=callback;
            return proxyRequester.stringStart();
        }

        @Override
        public HttpCallBack<ResponseBody<String>> getCallBack() {
            return callback;
        }
    }


    public class UploadObserver extends Observer<String> {


        UploadProgrossCallback<ResponseBody<String>> callback;
        CProxyRequester proxyRequester;

        protected UploadObserver(CProxyRequester proxyRequester) {
            super(proxyRequester);
            this.proxyRequester = proxyRequester;
        }

        @Override
        public TaskHandler Observation(HttpCallBack<ResponseBody<String>> callback) {

            this.callback = (UploadRequestResultCallBack<T>) callback;
            return   proxyRequester.uploadstart();
        }

        @Override
        public HttpCallBack<ResponseBody<String>> getCallBack() {
            return callback;
        }




    }

    public class DownloadObserver extends Observer<File> {
        DownProgrossCallback<ResponseBody<File>> callback;
        CProxyRequester proxyRequester;

        protected DownloadObserver(CProxyRequester proxyRequester) {
            super(proxyRequester);
            this.proxyRequester = proxyRequester;
        }

        @Override
        public TaskHandler Observation(HttpCallBack<ResponseBody<File>> callback) {
            this.callback = (DownProgrossCallback<ResponseBody<File>>) callback;
            return proxyRequester.downloadStart();
        }

        @Override
        public HttpCallBack<ResponseBody<File>> getCallBack() {
            return callback;
        }




    }


    private static synchronized int getIndex() {
        return Index++;
    }


}

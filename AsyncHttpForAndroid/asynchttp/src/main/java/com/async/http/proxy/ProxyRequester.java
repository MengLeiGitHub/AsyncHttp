package com.async.http.proxy;


import com.async.http.AsyncHttp;
import com.async.http.callback.DownProgrossCallback;
import com.async.http.callback.HttpCallBack;
import com.async.http.callback.UploadProgrossCallback;
import com.async.http.entity.ResponseBody;
import com.async.http.handler.TaskHandler;
import com.async.http.proxy.dao.RequesyTypeFactory;
import com.async.http.proxy.dao.RequesyTypeInterface;
import com.async.http.proxy.entity.CreatorBeans;
import com.async.http.request2.BaseRequest;
import com.async.http.request2.FileRequest;
import com.async.http.request2.StringRequest;
import com.async.http.request2.UploadRequest;
import com.async.http.request2.download;

import java.io.File;

public class ProxyRequester {

    private CreatorBeans creatorBeans;
    MIO mio;
    Observer observer;


    BaseRequest request;

    public void setCreatorBeans(CreatorBeans creatorBeans) {
        this.creatorBeans = creatorBeans;
    }

    private ProxyRequester runInIO() {

        RequesyTypeInterface requesyTypeInterface = new RequesyTypeFactory().getRequestType(creatorBeans);

        try {
            request = requesyTypeInterface.holdRequest();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return this;
    }

    /**
     * 用于区分时在主线程中回掉还是在io线程中回掉
     *
     * @param mio
     */

    public Observer ResultMonitor(MIO mio) {
        runInIO();
        this.mio = mio;
        if (creatorBeans.isJSONPOST()) {
            observer = this.new StringObserver(this);
            return observer;

        }
        if (creatorBeans.isDownload()) {
            observer = this.new DownloadObserver(this);
            return observer;

        }
        if (creatorBeans.isUpload()) {
            observer = this.new UploadObserver(this);
            return observer;
        }
        if(creatorBeans.getRequestImpl()!=null){
            observer=new CustomeObserver(this);
            return  observer;
        }

        return observer = new StringObserver(this);


    }


    private TaskHandler  customerStart(){
       return AsyncHttp.instance().newRequest2(request, observer.getCallBack());
    }

    private TaskHandler StringStart() {
      return   AsyncHttp.instance().stringRequest((StringRequest) request, observer.getCallBack());

    }

    private TaskHandler uploadStart() {
     return    AsyncHttp.instance().UploadRequest((UploadRequest) request, (UploadProgrossCallback) observer.getCallBack());

    }

    private TaskHandler downloadStart() {
        if(creatorBeans.MultitierDownload())
        return     AsyncHttp.instance().download((download) request, (DownProgrossCallback<ResponseBody<File>>) observer.getCallBack());
        else
        return     AsyncHttp.instance().download((FileRequest) request, (DownProgrossCallback<ResponseBody<File>>) observer.getCallBack());
    }

    public abstract class Observer<T> {
        ProxyRequester proxyRequester;

        protected Observer(ProxyRequester proxyRequester) {
            this.proxyRequester = proxyRequester;
        }

        HttpCallBack<ResponseBody<T>> callBack;

        public abstract TaskHandler Observation(HttpCallBack<ResponseBody<T>> callback);

        public abstract HttpCallBack<ResponseBody<T>> getCallBack();
    }

    public class CustomeObserver extends Observer<String>{
        HttpCallBack<ResponseBody<String>> callback;

        public CustomeObserver(ProxyRequester proxyRequester) {
            super(proxyRequester);
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

    public class StringObserver extends Observer<String> {


        HttpCallBack<ResponseBody<String>> callback;

        protected StringObserver(ProxyRequester proxyRequester) {
            super(proxyRequester);
        }

        public TaskHandler Observation(HttpCallBack<ResponseBody<String>> callback) {
            this.callback = callback;
           return proxyRequester.StringStart();
        }

        @Override
        public HttpCallBack<ResponseBody<String>> getCallBack() {
            return callback;
        }

        public HttpCallBack<ResponseBody<String>> getCallback() {
            return callback;
        }
    }


    public class UploadObserver extends Observer<String> {


        UploadProgrossCallback<ResponseBody<String>> callback;

        protected UploadObserver(ProxyRequester proxyRequester) {
            super(proxyRequester);
        }

        @Override
        public TaskHandler  Observation(HttpCallBack<ResponseBody<String>> callback) {
            this.callback = (UploadProgrossCallback<ResponseBody<String>>) callback;
            return proxyRequester.uploadStart();
        }

        @Override
        public HttpCallBack<ResponseBody<String>> getCallBack() {
            return callback;
        }


        public UploadProgrossCallback<ResponseBody<String>> getCallback() {
            return callback;
        }

    }

    public class DownloadObserver extends Observer<File> {
        DownProgrossCallback<ResponseBody<File>> callback;

        protected DownloadObserver(ProxyRequester proxyRequester) {
            super(proxyRequester);
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

      /*  public void Observation(DownProgrossCallback<ResponseBody<File>> callback) {
            this.callback = callback;
            proxyRequester.downloadStart();
        }*/

        public DownProgrossCallback<ResponseBody<File>> getCallback() {
            return callback;
        }


    }
}

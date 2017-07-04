package com.async.http.proxy.entity;

import com.async.http.annotation.DOWNLOAD;
import com.async.http.annotation.JSONPOST;
import com.async.http.annotation.UPLOAD;
import com.async.http.client.HttpMethod;
import com.async.http.request2.BaseRequest;

import java.lang.reflect.Method;
import java.util.List;

public class CreatorBeans {
    private String url;
    private List<ParamBean> list;
    private List<AnnotationKey> keys;
    private HttpMethod httpMethod;
    private boolean isUpload;
    private Class methodClass;
    private boolean isDownload;
    boolean MultitierDownload;
    private List<MultiPart> multiParts;

    private boolean isJSONPOST;
    private Class<? extends BaseRequest> requestImpl;


    public void setList(List<ParamBean> list) {
        this.list = list;
    }

    public List<ParamBean> getList() {
        return list;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setKeys(List<AnnotationKey> keys) {
        this.keys = keys;
    }

    public List<AnnotationKey> getKeys() {
        return keys;
    }

    public void setUpload(boolean isUpload) {
        this.isUpload = isUpload;
    }

    public boolean isUpload() {
        isUpload = UPLOAD.class.getName().equals(methodClass.getName());
        return isUpload;
    }

    public void setMethodClass(Class methodClass) {
        this.methodClass = methodClass;
    }

    public Class getMethodClass() {
        return methodClass;
    }

    public void setDownload(boolean isDownload) {
        this.isDownload = isDownload;
    }

    public boolean isDownload() {
        return isDownload;
    }

    public List<MultiPart> getMultiParts() {
        return multiParts;
    }

    public void setMultiParts(List<MultiPart> multiParts) {
        this.multiParts = multiParts;
    }

    public boolean isJSONPOST() {
        isJSONPOST = (methodClass.getName().equals(JSONPOST.class.getName()));
        return isJSONPOST;
    }

    public void setJSONPOST(boolean isJSONPOST) {
        this.isJSONPOST = isJSONPOST;
    }



    public boolean MultitierDownload() {
        return MultitierDownload;
    }

    public void setMultitierDownload(boolean multitierDownload) {
        MultitierDownload = multitierDownload;
    }

    public void setRequestImpl(Class<? extends BaseRequest> requestImpl) {
        this.requestImpl = requestImpl;
    }

    public Class<? extends BaseRequest> getRequestImpl() {
        return requestImpl;
    }
}

package com.async.http.android;

import android.os.Handler;
import android.os.Message;

import com.async.http.callback.DownProgrossCallback;
import com.async.http.entity.ResponseBody;


/**
 * Created by admin on 2016-11-05.
 */
 public   class DownCallBack<T> implements DownProgrossCallback<ResponseBody<T>> {

    Handler handler;
    int what;
    public DownCallBack(Handler handler, int what){
        this.handler=handler;
        this.what=what;
    }


    @Override
    public void start() {
        Message message=new Message();
        message.arg1= RequestProgress.START;
        message.what=what;
        handler.sendMessage(message);
    }

    @Override
    public void finish() {
        Message message=new Message();
         message.arg1=RequestProgress.FINISH;
        message.what=what;
        handler.sendMessage(message);
    }

    @Override
    public void success(ResponseBody<T> result) {
         Message message=new Message();
        message.obj=result;
        message.arg1=RequestProgress.SUCCESS;
        message.what=what;
        handler.sendMessage(message);
    }

    @Override
    public void fail(Exception e, ResponseBody<T> request) {
        FaileAndResponse<T> faileAndResponse=new FaileAndResponse<>(e,request);
        Message message=new Message();
        message.obj=faileAndResponse;
        message.what=what;
        message.arg1=RequestProgress.FAIL;
        handler.sendMessage(message);
    }

    @Override
    public void download_current(long current, long total) {
        Message message=new Message();
        message.obj=new ProgressBean(current,  0,  total);
        message.arg1=RequestProgress.CURRENT;
        message.what=what;
        handler.sendMessage(message);
    }
}

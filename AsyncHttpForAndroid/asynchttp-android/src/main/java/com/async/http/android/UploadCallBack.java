package com.async.http.android;

import android.os.Handler;
import android.os.Message;

import com.async.http.callback.UploadProgrossCallback;
import com.async.http.entity.ResponseBody;


/**
 * Created by admin on 2016-11-05.
 */
  class UploadCallBack<T>  extends DownCallBack<String>    implements UploadProgrossCallback<ResponseBody<String>> {

    Handler handler;
    int what;
    public UploadCallBack(Handler handler,int what){
        super(handler,what);
        this.handler=handler;
        this.what=what;
    }

    @Override
    public void success(ResponseBody<String> result) {
        super.success(result);
    }

    @Override
    public void upload_current(long current, long currentFileTotal, long total) {
        Message message=new Message();
        message.obj=new ProgressBean(current,  currentFileTotal,  total);
        message.what=what;
        message.arg1=RequestProgress.CURRENT;
        handler.sendMessage(message);
    }

}

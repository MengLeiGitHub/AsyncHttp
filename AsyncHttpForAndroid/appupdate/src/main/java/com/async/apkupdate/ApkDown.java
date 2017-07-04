package com.async.apkupdate;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.async.apkupdate.defaults.DefaultNotification;
import com.async.apkupdate.listener.NoticeListener;
import com.async.apkupdate.service.UpdateVersionService;
import com.async.apkupdate.utils.StorageUtils;

/**
 * Created by admin on 2016/10/19.
 */
public class ApkDown    {
    Application application;
    String url;
    NoticeListener noticeListener;
    MyBaodcast  baodcast=null;
    String  versionName;
    final  int START=10100,STARTPATCH=10101;

    Handler handler=new Handler(Looper.getMainLooper()){
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what){
                case START:
                    baodcast=new MyBaodcast();
                    deault();
                    String filepath=StorageUtils.getStoragePath(application)+"/"+versionName+".apk";
                     //必须设置存储路径
                    Intent intent=new Intent(application,UpdateVersionService.class);
                    intent.putExtra("url",url);
                    intent.putExtra("filepath",filepath);
                    application.startService(intent);
                    break;
                case STARTPATCH:
                    baodcast=new MyBaodcast();
                    deault();
                    filepath=StorageUtils.getStoragePath(application)+"/"+versionName+".patch";
                    //必须设置存储路径
                    intent=new Intent(application,UpdateVersionService.class);
                    intent.putExtra("url",url);
                    intent.putExtra("filepath",filepath);
                    String newfilepath=StorageUtils.getStoragePath(application)+"/"+versionName+".apk";
                    intent.putExtra("newFile",newfilepath);
                    intent.putExtra("increment",true);
                    application.startService(intent);
                    break;
            }



        }
    };


    protected   ApkDown(String url,Application application){
        this.url=url;
        this.application=application;

    }

    protected   ApkDown(String url,Application application,NoticeListener noticeListener){
        this.url=url;
        this.application=application;
        this.noticeListener=noticeListener;
    }

    //

    protected   ApkDown(String url,String versionName,Application application,NoticeListener noticeListener){
        this.url=url;
        this.versionName=versionName;
        this.application=application;
        this.noticeListener=noticeListener;
    }

    public  void  startDownload(){
        handler.sendEmptyMessage(START);
    }
    public  void  startPatchDownload(){
        handler.sendEmptyMessage(STARTPATCH);
    }
    private void deault() {
        if(noticeListener==null)noticeListener=new DefaultNotification(application);

            IntentFilter intentFilter=new IntentFilter();
            intentFilter.addAction(Action_CURRENTDOWN);
            intentFilter.addAction(Action_PROGRESS);
            intentFilter.addAction(Action_FINISH);
            intentFilter.addAction(Action_START);
            application.registerReceiver(baodcast,intentFilter);

     }
    public static final  String    Action_PROGRESS="ACTIONPROGRESS";
    public static final  String    Action_START="ACTIONSTART";
    public static final  String    Action_FINISH="ACTIONFINISH";
    public static final  String    Action_CURRENTDOWN="ACTIONCURRENTDOWN";




    private void jiebang(){
        if(baodcast!=null){
            application.unregisterReceiver(baodcast);
            baodcast=null;
        }
    }

    private class  MyBaodcast  extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {

          String acti=  intent.getAction();
            if(acti.equals(Action_PROGRESS)){
                int pros=intent.getIntExtra(Action_PROGRESS,0);
                noticeListener.progress(pros);
            }else if(acti.equals(Action_START)){
                 noticeListener.start();
            }else if(acti.equals(Action_FINISH)){
                noticeListener.finish();
                jiebang();
            }else if(acti.equals(Action_CURRENTDOWN)){
                long current=intent.getLongExtra("current", 0);
                long total=intent.getLongExtra("total", 0);
                noticeListener.currentDown(current,total);
            }
        }
    }





}

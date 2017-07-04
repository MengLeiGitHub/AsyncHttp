package com.async.apkupdate.notice;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import com.async.apkupdate.R;
import com.async.apkupdate.listener.NoticeListener;
import com.async.apkupdate.utils.Intall;

import java.io.File;

/**
 * Created by admin on 2016/10/19.
 */
public class NoticeManager implements NoticeListener{
    private NotificationManager notificationManager;
    private Notification downLoadNotification;
    Application application;

    public   NoticeManager (Application application){
        this.application=application;
    }



    public void  create(){
        notificationManager = (NotificationManager)application.getSystemService(Context.NOTIFICATION_SERVICE);
        downLoadNotification = new Notification();
        downLoadNotification.icon = R.drawable.ic_launcher;
        downLoadNotification.tickerText = "开始更新";
        downLoadNotification.flags = Notification.DEFAULT_SOUND;
        downLoadNotification.flags = Notification.FLAG_SHOW_LIGHTS;
        downLoadNotification.flags = Notification.FLAG_NO_CLEAR;
        downLoadNotification.contentView = new RemoteViews(application.getPackageName(), R.layout.view_notification_updateversion);
        notificationManager.notify(0, downLoadNotification);
    }

    @Override
    public void start() {
        create();
    }

    public  void  progress(int progress){

    }

    @Override
    public void currentDown(long current, long total) {
        int  progress= (int) (current*100f/total);

        downLoadNotification.contentView.setTextViewText(R.id.tv_progress, progress + "%");
        downLoadNotification.contentView.setProgressBar(R.id.pb_progress, 100, progress, false);
        notificationManager.notify(0, downLoadNotification);
    }

    public void  finish(){
     //   notificationManager.cancel(0);

    }

    @Override
    public void success(File file) {
        // TODO Auto-generated method stub
        Intall.install(application,file);
    }


}

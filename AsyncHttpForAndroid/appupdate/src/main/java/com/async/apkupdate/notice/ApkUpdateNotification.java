package com.async.apkupdate.notice;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import com.async.apkupdate.listener.NoticeListener;
import com.async.apkupdate.utils.Intall;

import java.io.File;

/**
 * Created by admin on 2016/10/22.
 */

public  abstract class ApkUpdateNotification implements NoticeListener {

    private NotificationManager notificationManager;
    private Notification downLoadNotification;
    Application application;

    public ApkUpdateNotification(Application application){
        this.application=application;
    }



    public void  create(){
        notificationManager = (NotificationManager)application.getSystemService(Context.NOTIFICATION_SERVICE);

        downLoadNotification = new Notification();
        downLoadNotification.flags = Notification.DEFAULT_SOUND;
        downLoadNotification.flags = Notification.FLAG_SHOW_LIGHTS;
        downLoadNotification.flags = Notification.FLAG_NO_CLEAR;

        downLoadNotification.icon = android.R.drawable.sym_def_app_icon;
        downLoadNotification.tickerText = "开始更新";


        Notification   downLoadNotification1= initNotification(downLoadNotification);
        if(downLoadNotification1!=null)downLoadNotification=downLoadNotification1;
        PendingIntent pendingintent = PendingIntent.getActivity(application, 0, new Intent(), PendingIntent.FLAG_CANCEL_CURRENT);
        downLoadNotification.contentView = new RemoteViews(application.getPackageName(),getLayoutId());
        downLoadNotification.contentIntent=pendingintent;
      //  R.layout.view_notification_updateversion
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
        updateRoteView(downLoadNotification.contentView,current,total);
        notificationManager.notify(0, downLoadNotification);
    }

    public void  finish(){
        notificationManager.cancel(0);
        onFinish();
    }

    protected abstract void onFinish();


    @Override
    public void success(File file) {
        // TODO Auto-generated method stub
        Intall.install(application,file);
    }

    protected abstract Notification initNotification(Notification downLoadNotification);

    public abstract int getLayoutId() ;

    public abstract  void   updateRoteView(RemoteViews remoteView,long current,long total);
}

package com.async.apkupdate.defaults;

import android.app.Application;
import android.app.Notification;
import android.widget.RemoteViews;

import com.async.apkupdate.R;
import com.async.apkupdate.notice.ApkUpdateNotification;

/**
 * Created by admin on 2016/10/22.
 */

public    class DefaultNotification extends ApkUpdateNotification {


    public DefaultNotification(Application application) {
        super(application);
    }



    @Override
    protected Notification initNotification(Notification downLoadNotification) {

        return  downLoadNotification;
    }

    @Override
    public int getLayoutId() {
        return R.layout.view_notification_updateversion;
    }

    @Override
    public void updateRoteView(RemoteViews remoteView, long current, long total) {
        int  progress= (int) (current*100f/total);
        remoteView.setTextViewText(R.id.tv_progress,""+progress+"%");
        remoteView.setProgressBar(R.id.pb_progress,100,progress,false);
    }


    @Override
    protected void onFinish() {

    }
}

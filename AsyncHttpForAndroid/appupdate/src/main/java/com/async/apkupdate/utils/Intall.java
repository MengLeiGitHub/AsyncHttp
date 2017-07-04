package com.async.apkupdate.utils;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;

import java.io.File;

/**
 * Created by admin on 2017-04-11.
 */

public class Intall {
    public static void install(Application application,File file){
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        application.startActivity(intent);
    }
}

package com.async.apkupdate;

import android.content.Context;
import android.content.pm.ApplicationInfo;

/**
 * Created by admin on 2017-04-19.
 */
//javah com.async.apkupdate.BsdiffUtils

public class BsdiffUtils {

    static {
        System.loadLibrary("apkupdateincrementlib");

    }

    public native int genDiff(String oldApkPath, String newApkPath, String patchPath);

    public native int patch(String oldApkPath, String newApkPath, String patchPath);

    public static String extract(Context context) {
        context = context.getApplicationContext();
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        String apkPath = applicationInfo.sourceDir;
        return apkPath;
    }


}

package com.async.apkupdate;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.alibaba.fastjson.JSON;
import com.async.apkupdate.defaults.DefaultDialog;
import com.async.apkupdate.dialog.ApkUpdateDialog;
import com.async.apkupdate.listener.AppVersionCheckCallBack;
import com.async.apkupdate.listener.NoticeListener;
import com.async.apkupdate.listener.OKListener;
import com.async.apkupdate.utils.FileUtils;
import com.async.apkupdate.utils.Intall;
import com.async.apkupdate.utils.NetWorkType.Constants;
import com.async.apkupdate.utils.NetWorkType.NetWorkUtils;
import com.async.apkupdate.utils.SpUtils;
import com.async.apkupdate.utils.StorageUtils;
import com.async.http.android.AsyncHttpUtils;
import com.async.http.android.StringRequestResultCallBack;
import com.async.http.client.HttpMethod;
import com.async.http.entity.ResponseBody;
import com.async.http.utils.LogUtils;
import com.async.http.utils.StringUtils;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;


/**
 * Created by ml on 2016/10/19.
 */
public abstract class ApkUpdateOnline<T> implements AppVersionCheckCallBack<T> {
    private String checkurl;
    private AppVersionCheckCallBack appVersionCheckCallBack;
    private ApkUpdateDialog apkUpdateDialog;
    private ApkUpdateDialog ackDialog;

    private NoticeListener noticeListener;


    private Activity activity;
    private final int NOW_START_DOWNLOAD = 1;
    private final int SHOW_ASK_DIALOG = 2;
    private final int NETWORK_DIALOG = 3;
    private final int NETWORK_DIALOG_INCREMENT = 4;
    boolean isIncrement;
    T t;
    private Handler handler = new Handler(Looper.getMainLooper()) {


        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what) {
                case NOW_START_DOWNLOAD:

                    break;
                case SHOW_ASK_DIALOG:

                    break;
                case NETWORK_DIALOG:
                    showNetWorkDialog(false);
                    break;
                case NETWORK_DIALOG_INCREMENT:
                    showNetWorkDialog(true);
                    break;

            }


        }
    };


    public ApkUpdateOnline setActivity(Activity activity, ApkUpdateDialog apkUpdateDialog) {
        this.activity = activity;
        this.apkUpdateDialog = apkUpdateDialog;
        return this;
    }


    public void setAckDialog(ApkUpdateDialog ackDialog) {
        this.ackDialog = ackDialog;
    }

    /**
     * 自定义所有的
     *
     * @param
     * @param url
     */
    public ApkUpdateOnline(Activity activity, String url) {
        checkurl = url;
        this.appVersionCheckCallBack = this;
        this.activity = activity;
    }

    public void setAppVersionCheckCallBack(AppVersionCheckCallBack appVersionCheckCallBack) {
        this.appVersionCheckCallBack = appVersionCheckCallBack;
    }

    public ApkUpdateOnline(Activity activity, String url, ApkUpdateDialog apkUpdateDialog, NoticeListener noticeListener) {
        checkurl = url;
        this.appVersionCheckCallBack = this;
        this.apkUpdateDialog = apkUpdateDialog;
        this.noticeListener = noticeListener;
        this.activity = activity;

    }

    public void CheckByGet() {
        AsyncHttpUtils.string(checkurl, HttpMethod.Get, null, new StringRequestResultCallBack<String>() {
            @Override
            public void requestFail(Exception e, ResponseBody<String> request) {
                e.printStackTrace();
            }

            @Override
            public void requestSuccess(String s) {
                try {
                    convertResult(s);
                } catch (Exception e) {
                    requestFail(e, new ResponseBody<String>().setResult(s));
                }

            }

            @Override
            public void requsetFinish() {

            }

            @Override
            public void requsetStart() {

            }
        });
    }


    public void CheckByStringPost(HashMap<String, String> param) {
        stringpost(param);
    }

    private void stringpost(HashMap<String, String> param) {
        AsyncHttpUtils.string(checkurl, HttpMethod.Post, param, new StringRequestResultCallBack<String>() {
            @Override
            public void requestFail(Exception e, ResponseBody<String> request) {
                e.printStackTrace();
            }

            @Override
            public void requestSuccess(String s) {
                try {
                    convertResult(s);
                } catch (Exception e) {
                    requestFail(e, new ResponseBody<String>().setResult(s));
                }
            }

            @Override
            public void requsetFinish() {

            }

            @Override
            public void requsetStart() {

            }
        });
    }

    public void CheckByJSONPost(HashMap<String, String> param) {
        postjson(param);

    }

    private void postjson(HashMap<String, String> param) {
        AsyncHttpUtils.json(checkurl, HttpMethod.Post, param, new StringRequestResultCallBack<String>() {
            @Override
            public void requestFail(Exception e, ResponseBody<String> request) {
                e.printStackTrace();
            }

            @Override
            public void requestSuccess(String s) {
                LogUtils.e(s);
                try {
                    convertResult(s);
                } catch (Exception e) {
                    requestFail(e, new ResponseBody<String>().setResult(s));
                }
            }

            @Override
            public void requsetFinish() {

            }

            @Override
            public void requsetStart() {

            }
        });
    }


    private void convertResult(String result) throws Exception {
        String resultDesConstants = appVersionCheckCallBack.getResultDesConstants(result);
        if (resultDesConstants != null) result = resultDesConstants;

        Class c = getClass();
        Type t = null;
        Type genType = c.getGenericSuperclass();
        if (genType instanceof ParameterizedType) {
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            t = params[0];
        }

        T t1 = JSON.parseObject(result, t);
        check(t1);
    }


    public void check(T t1) {
        this.t = t1;
        final boolean showdialog = appVersionCheckCallBack.isShowDialog(t1);

        if (showdialog) {
            final String content = appVersionCheckCallBack.getContent(t1);
            final String title = appVersionCheckCallBack.getTitle(t1);
            final String downURL = appVersionCheckCallBack.getDownURL(t1);
            final String incrementDownUrl = appVersionCheckCallBack.getIncrementDownURL(t1);
            final boolean isMustUpdate = appVersionCheckCallBack.isMustUpdate(t1);
            //   final String  version=appVersionCheckCallBack.getDownApkVersionName(t1);

            if (apkUpdateDialog == null) apkUpdateDialog = new DefaultDialog(activity);
            apkUpdateDialog.setDialogTitle(title);
            apkUpdateDialog.setContentText(content);
            apkUpdateDialog.setIsMustUpdate(isMustUpdate);
            apkUpdateDialog.setCanceledOnTouchOutside(!isMustUpdate);
            apkUpdateDialog.setOkListener(new OKListener() {
                @Override
                public void OK() {

                    if (Constants.NETWORK_WIFI == NetWorkUtils.getNetWorkStatus(activity.getApplication()))
                        new ApkDown(downURL, "CacheAPk", activity.getApplication(), noticeListener).startDownload();
                    else {
                        if (ackDialog != null)
                            handler.sendEmptyMessage(NETWORK_DIALOG);
                        else {
                            new ApkDown(downURL, "CacheAPk", activity.getApplication(), noticeListener).startDownload();
                        }
                    }
                }

                @Override
                public void incrementOk() {
                    if (Constants.NETWORK_WIFI == NetWorkUtils.getNetWorkStatus(activity.getApplication())) {
                        new ApkDown(incrementDownUrl, "CacheAPk", activity.getApplication(), noticeListener).startPatchDownload();
                    } else {
                        if (ackDialog != null)
                            handler.sendEmptyMessage(NETWORK_DIALOG_INCREMENT);
                        else {
                            new ApkDown(incrementDownUrl, "CacheAPk", activity.getApplication(), noticeListener).startPatchDownload();
                        }
                    }
                }
            });
            apkUpdateDialog.show();
        }
    }

    private void showNetWorkDialog(final boolean isIncrement) {
        ackDialog.setIsMustUpdate(appVersionCheckCallBack.isMustUpdate(t));
        ackDialog.setOkListener(new OKListener() {
            @Override
            public void OK() {
                if (isIncrement) {
                    new ApkDown(appVersionCheckCallBack.getIncrementDownURL(t), "CacheAPk", activity.getApplication(), noticeListener).startPatchDownload();
                } else
                    new ApkDown(appVersionCheckCallBack.getDownURL(t), "CacheAPk", activity.getApplication(), noticeListener).startDownload();
            }

            @Override
            public void incrementOk() {
            }
        });
        ackDialog.show();

    }

}





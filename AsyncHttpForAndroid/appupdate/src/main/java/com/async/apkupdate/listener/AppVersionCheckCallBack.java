package com.async.apkupdate.listener;

/**
 * Created by admin on 2016/10/19.
 */
public interface AppVersionCheckCallBack<T> {
    public String getTitle(T t);

    public String getContent(T t);

    public String getDownURL(T t);
    public String getIncrementDownURL(T t);

    public boolean isMustUpdate(T t);

    public boolean isShowDialog(T t1);

    public String getResultDesConstants(String str);

    public String  getDownApkVersionName(T t);


}

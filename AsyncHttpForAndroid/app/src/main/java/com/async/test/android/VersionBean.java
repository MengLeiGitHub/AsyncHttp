package com.async.test.android;

/**
 * Created by Administrator on 2016/5/26.
 */
/**
 * Created by Administrator on 2016/5/26.
 */
public class VersionBean  {

    /**

     * version : 1.0.1
     * name :appupdate
     * size : 32M
     * isForce : false
     * onlineTime : 1484814504000
     * address : http://www.u-yin.cn
     * updateLog : 新版本
     */


    private String version;
    private String name;
    private String size;
    private boolean isForce;
    private long onlineTime;
    private String address;
    private String updateLog;
    private String patchAddress;
    private String patchSize;
    public void setPatchSize(String patchSize) {
        this.patchSize = patchSize;
    }
    public String getPatchSize() {
        return patchSize;
    }
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean isIsForce() {
        return isForce;
    }

    public void setIsForce(boolean isForce) {
        this.isForce = isForce;
    }

    public long getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(long onlineTime) {
        this.onlineTime = onlineTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUpdateLog() {
        return updateLog;
    }

    public void setUpdateLog(String updateLog) {
        this.updateLog = updateLog;
    }
    public void setPatchAddress(String patchAddress) {
        this.patchAddress = patchAddress;
    }
    public String getPatchAddress() {
        return patchAddress;
    }
}

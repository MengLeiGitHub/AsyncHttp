package com.android;

/**
 * Created by Administrator on 2016/5/26.
 */
public class VersionBean  {

    /**
     * id : 1
     * osType : 1
     * appType : 1
     * version : 1.0.1
     * name : 优印用户端
     * size : 32M
     * isForce : false
     * onlineTime : 1484814504000
     * address : http://www.u-yin.cn
     * updateLog : 新版本
     */

    private int id;
    private int osType;
    private int appType;
    private String version;
    private String name;
    private String size;
    private boolean isForce;
    private long onlineTime;
    private String address;
    private String updateLog;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOsType() {
        return osType;
    }

    public void setOsType(int osType) {
        this.osType = osType;
    }

    public int getAppType() {
        return appType;
    }

    public void setAppType(int appType) {
        this.appType = appType;
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
}

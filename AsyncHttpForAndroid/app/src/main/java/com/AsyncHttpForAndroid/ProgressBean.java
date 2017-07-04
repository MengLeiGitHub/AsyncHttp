package com.AsyncHttpForAndroid;

/**
 * Created by admin on 2016-11-05.
 */
 class ProgressBean {

    public long current;
    public long currentFileTotal;
    public long total;





    public ProgressBean(long current, long currentFileTotal, long total) {
        this.current=current;
        this.currentFileTotal=currentFileTotal;
        this.total=total;
    }
}

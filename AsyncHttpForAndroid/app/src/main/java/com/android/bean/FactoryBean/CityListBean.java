package com.android.bean.FactoryBean;

import java.util.List;

/**
 * Created by admin on 2016-11-08.
 */
public class CityListBean {
    private int cityId;
    private String cityName;
    /**
     * factoryId : 23
     * factoryName : 海通印务
     */

    private List<FactoryListBean> factoryList;

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<FactoryListBean> getFactoryList() {
        return factoryList;
    }

    public void setFactoryList(List<FactoryListBean> factoryList) {
        this.factoryList = factoryList;
    }


}

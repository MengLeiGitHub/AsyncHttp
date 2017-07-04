package com.android.bean.FactoryBean;

import java.util.List;

/**
 * Created by admin on 2016-12-04.
 */

public class Province<T> {


    /**
     * cityId : 1
     * cityName : 北京市
     * factoryList : [{"factoryId":3,"factoryName":"haoren","factoryDepts":[],"permissions":null,"roleId":0,"hxGroupId":null,"userId":null,"realname":null}]
     */

    private int provinceId;
    private String provinceName;
    /**
     * factoryId : 3
     * factoryName : haoren
     * factoryDepts : []
     * permissions : null
     * roleId : 0
     * hxGroupId : null
     * userId : null
     * realname : null
     */

    private List<T> cityList;

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public List<T> getCityList() {
        return cityList;
    }

    public void setCityList(List<T> cityList) {
        this.cityList = cityList;
    }
}

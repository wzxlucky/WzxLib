package com.one.wsy.wzxlib.city.bean;

/**
 * 描述：全部城市实体类
 * 名称: CityListBean
 *
 * @author: wsy
 * 版本: 1.0
 * 日期: 2018/10/29 14:43
 */
public class CityListBean {

    private String sortLetters;
    private String cityName;

    public String getSortLetters() {
        return sortLetters == null ? "" : sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    public String getCityName() {
        return cityName == null ? "" : cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}

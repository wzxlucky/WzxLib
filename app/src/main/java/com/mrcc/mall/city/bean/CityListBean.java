package com.mrcc.mall.city.bean;

/**
 * @author: wsy
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

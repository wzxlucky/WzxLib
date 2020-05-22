package com.mrcc.mall.city.bean;

/**
 * @author: wsy
 */
public class HotCityBean {

    private String hotCitName;

    public String getHotCitName() {
        return hotCitName == null ? "" : hotCitName;
    }

    public void setHotCitName(String hotCitName) {
        this.hotCitName = hotCitName;
    }
}

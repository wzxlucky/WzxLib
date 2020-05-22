package com.mrcc.mall.city;

import com.one.wsy.wzxlib.city.bean.CityListBean;

import java.util.Comparator;

/**
 * 描述：集合排序
 * 名称: PinyinComparator
 *
 * @author: wsy
 * 版本: 1.0
 * 日期: 2018/10/30 9:23
 */
public class PinyinComparator implements Comparator<CityListBean> {
    @Override
    public int compare(CityListBean o1, CityListBean o2) {
        return o1.getSortLetters().compareTo(o2.getSortLetters());
    }
}

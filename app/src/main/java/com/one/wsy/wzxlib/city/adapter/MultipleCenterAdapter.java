package com.one.wsy.wzxlib.city.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.one.wsy.wzxlib.R;
import com.one.wsy.wzxlib.city.bean.HotCityBean;

import java.util.List;

/**
 * 描述：
 * 名称: MultipleCenterAdapter
 *
 * @author: wsy
 * 版本: 1.0
 * 日期: 2018/10/29 14:27
 */
public class MultipleCenterAdapter extends BaseQuickAdapter<HotCityBean, BaseViewHolder> {

    public MultipleCenterAdapter(int layoutResId, @Nullable List<HotCityBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HotCityBean item) {
        helper.setText(R.id.tvHotCityName, item.getHotCitName());
    }
}

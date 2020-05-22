package com.mrcc.mall.city.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mrcc.mall.R;
import com.mrcc.mall.city.bean.HotCityBean;

import java.util.List;

/**
 * @author: wsy
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

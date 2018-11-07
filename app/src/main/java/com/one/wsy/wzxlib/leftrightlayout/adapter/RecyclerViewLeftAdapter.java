package com.one.wsy.wzxlib.leftrightlayout.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.one.wsy.wzxlib.R;
import com.one.wsy.wzxlib.leftrightlayout.bean.LeftBean;

import java.util.List;

/**
 * @author wsy
 */
public class RecyclerViewLeftAdapter extends BaseQuickAdapter<LeftBean, BaseViewHolder> {

    private int clickPosition;

    public RecyclerViewLeftAdapter(int layoutResId, @Nullable List<LeftBean> data) {
        super(layoutResId, data);
    }

    public void setClickPosition(int clickPosition) {
        this.clickPosition = clickPosition;
    }

    @Override
    protected void convert(BaseViewHolder helper, LeftBean item) {
        helper.setText(R.id.tvLeft, item.getContentLeft());
        if (helper.getLayoutPosition() == clickPosition) {
            helper.setBackgroundColor(R.id.rvLeft, Color.parseColor("white"));
        } else {
            helper.setBackgroundColor(R.id.rvLeft, Color.parseColor("#E0E0E0"));
        }
    }
}

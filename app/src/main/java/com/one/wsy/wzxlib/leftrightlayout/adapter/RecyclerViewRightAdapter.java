package com.one.wsy.wzxlib.leftrightlayout.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.one.wsy.wzxlib.R;
import com.one.wsy.wzxlib.leftrightlayout.bean.RightBean;

import java.util.List;

/**
 * @author wsy
 */
public class RecyclerViewRightAdapter extends BaseQuickAdapter<RightBean, BaseViewHolder> {


    public RecyclerViewRightAdapter(int layoutResId, @Nullable List<RightBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RightBean item) {
        helper.setText(R.id.tvRight, item.getContentRight());
    }

    @Override
    public void setNewData(@Nullable List<RightBean> data) {
        super.setNewData(data);
    }
}

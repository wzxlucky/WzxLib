package com.one.wsy.wzxlib.lazyfragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.one.wsy.wzxlib.R;
import com.one.wsy.wzxlib.base.BaseLazyFragment;

/**
 * 描述：
 * 名称: Fragment1
 *
 * @author: wsy
 * 版本: 1.0
 * 日期: 2018/11/6 16:27
 */
public class Fragment3 extends BaseLazyFragment {

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fm_layout3, container, false);
        return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClickEvent(View v) {

    }

    @Override
    protected void lazyLoad() {
    }

    @Override
    protected void stopLoad() {
    }
}

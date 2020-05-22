package com.mrcc.mall.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mrcc.mall.utils.widget.ToastUtils;

/**
 * @author wsy
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Activity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        initData();
        mActivity = this;
    }

    protected abstract void setContentView();

    protected abstract void initData();

    /**
     * @param msg 文本
     */
    public void showToast(String msg) {
        ToastUtils.getInstance().showToast(msg, 500, mActivity);
    }

    /**
     * @param msg  文本
     * @param time 时间
     */
    public void showToast(String msg, int time) {
        ToastUtils.getInstance().showToast(msg, time, mActivity);
    }
}

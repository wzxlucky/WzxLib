package com.one.wsy.wzxlib.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;

/**
 * 描述：Activity基类
 * 名称: BaseActivity
 * 作者: wsy
 * 版本: 1.0
 * 日期: 2018/4/17 9:19
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 是否沉浸状态栏
     **/
    private boolean isSetStatusBar = true;

    protected final String TAG = this.getClass().getSimpleName();

    private ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isSetStatusBar) {
            steepStatusBar();
        }
        setContentView();
        initView();
        initData();
    }

    /**
     * 沉浸状态栏
     */
    private void steepStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            mImmersionBar = ImmersionBar.with(this);
            mImmersionBar.keyboardEnable(true).init();

        }
    }

    /**
     * 初始化布局
     */
    public abstract void setContentView();

    /**
     * 初始化控件
     */
    public abstract void initView();

    /**
     * 业务操作
     */
    public abstract void initData();

    /**
     * View点击
     **/
    public abstract void onClickEvent(View v);


    @Override
    public void onClick(View v) {
        if (fastClick()) {
            onClickEvent(v);
        }
    }

    public <T extends View> T findView(int resId) {
        return (T) super.findViewById(resId);
    }


    /**
     * 是否设置沉浸状态栏
     *
     * @param isSetStatusBar
     */
    public void setSteepStatusBar(boolean isSetStatusBar) {
        this.isSetStatusBar = isSetStatusBar;
    }

    /**
     * 防止快速点击
     *
     * @return
     */

    private static final int MIN_DELAY_TIME = 1500;  // 两次点击间隔不能少于1500ms
    private static long lastClickTime;

    private boolean fastClick() {
        boolean flag = false;
        long currentClickTime = System.currentTimeMillis();
        if ((currentClickTime - lastClickTime) >= MIN_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = currentClickTime;
        return flag;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null) {
            //必须调用该方法，防止内存泄漏，不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
            mImmersionBar.destroy();
        }
    }

}

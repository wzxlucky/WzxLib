package com.mrcc.mall.utils.widget;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * @author wsy
 */
public class ToastUtils {
    /**
     * toast样式
     */
    private Toast mToast = null;
    /**
     * 上一次弹出的内容
     */
    private String mMsg = null;
    /**
     * 自定义view
     */
    private ToastView mToastView = null;
    /**
     * 位置
     */
    private int mToastGravity = -1;

    private ToastUtils() {
    }

    public static ToastUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final ToastUtils INSTANCE = new ToastUtils();
    }

    /**
     * 弹出提示
     *
     * @param msg  提示信息
     * @param time 显示时间
     */
    public void showToast(String msg, int time, Context context) {
        if (mMsg != null && msg != mMsg) {
            mToast = Toast.makeText(context, msg, time);
            if (mToastView != null) {
                mToast.setView(mToastView);
                mToastView.setText(msg);
            } else {
                setToastView(context);
                mToast.setView(mToastView);
                mToastView.setText(msg);
            }
        } else if (mToast == null) {
            mToast = Toast.makeText(context, msg, time);
            if (mToastView != null) {
                mToast.setView(mToastView);
                mToastView.setText(msg);
            } else {
                setToastView(context);
                mToast.setView(mToastView);
                mToastView.setText(msg);
            }
        } else {
            if (mToastView != null && mToast.getView() != mToastView) {
                mToast.setView(mToastView);
            }
            if (mToastView != null) {
                mToastView.setText(msg);
            } else {
                mToast.setText(msg);
            }
            mToast.setDuration(time);
        }
        if (mToastGravity != -1) {
            mToast.setGravity(mToastGravity, 0, 0);
        }
        //不设置的话，最高显示到状态栏下面
        mToast.getView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        mToast.show();
    }

    /**
     * 关闭当前Toast
     */
    private void cancelCurrentToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }

    public void setToastView(Context context) {
        mToastView = new ToastView(context);
    }

    private void setToastGravity(int gravity) {
        mToastGravity = gravity;
    }

    /**
     * 重置toast 信息
     */
    private void resetToast() {
        mToastView = null;
        mToastGravity = -1;
        mToast = null;
    }
}

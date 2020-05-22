package com.mrcc.mall.utils.widget;

import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;

import com.mrcc.mall.R;
import com.timmy.tdialog.TDialog;

/**
 * @author wsy
 */
public class DialogUtils {

    protected TDialog mTDialog;

    public static DialogUtils getInstance() {
        return DialogUtils.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final DialogUtils INSTANCE = new DialogUtils();
    }

    public void showLoading(AppCompatActivity appCompatActivity) {
        mTDialog = new TDialog.Builder(appCompatActivity.getSupportFragmentManager()).setLayoutRes(R.layout.dialog_loading)
                .setWidth(300)
                .setHeight(300)
                .setDimAmount(0.6f)
                .setCancelableOutside(false)
                .setGravity(Gravity.CENTER)
                .create()
                .show();
    }

    public void dissLoading() {
        if (mTDialog != null && mTDialog.isVisible()) {
            mTDialog.dismiss();
            mTDialog = null;
        }
    }
}

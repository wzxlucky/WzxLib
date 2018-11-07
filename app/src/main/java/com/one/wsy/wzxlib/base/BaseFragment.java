package com.one.wsy.wzxlib.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cazaea.sweetalert.SweetAlertDialog;

/**
 * 描述：Fragment基类
 * 名称: BaseFragment
 * 作者: wsy
 * 版本: 1.0
 * 日期: 2018/10/11 15:11
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    protected final String TAG = this.getClass().getSimpleName();

    private boolean isDoubleClick = true;

    protected SweetAlertDialog sweetAlertDialog;

    private Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = initView(inflater, container);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (sweetAlertDialog != null) {
            if (sweetAlertDialog.isShowing()) {
                sweetAlertDialog.dismiss();
            }
            sweetAlertDialog = null;
        }
    }

    @Override
    public void onClick(View v) {
        if (isDoubleClick) {
            if (fastClick()) {
                onClickEvent(v);
            }
        } else {
            onClickEvent(v);
        }
    }

    /**
     * 初始化控件
     */
    public abstract View initView(LayoutInflater inflater, ViewGroup container);

    /**
     * 业务操作
     */
    public abstract void initData();

    /**
     * View点击
     **/
    public abstract void onClickEvent(View v);


    /**
     * 防止快速点击
     * 两次点击间隔不能少于1500ms
     *
     * @return
     */
    private static final int MIN_DELAY_TIME = 1500;

    private static long lastClickTime;

    public void setDoubleClick(boolean isDoubleClick) {
        this.isDoubleClick = isDoubleClick;
    }

    private boolean fastClick() {
        boolean flag = false;
        long currentClickTime = System.currentTimeMillis();
        if ((currentClickTime - lastClickTime) >= MIN_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = currentClickTime;
        return flag;
    }

    /**
     * 单个按钮警告对话框
     *
     * @param msg
     * @param onSweetClickListener
     */
    protected void getWarmDialog(String msg, SweetAlertDialog.OnSweetClickListener onSweetClickListener) {
        sweetAlertDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE);
        sweetAlertDialog.setTitleText(msg);
        sweetAlertDialog.setConfirmText("确定");
        sweetAlertDialog.setConfirmClickListener(onSweetClickListener);
        sweetAlertDialog.show();
    }

    /**
     * 两个按钮警告对话框
     *
     * @param msg
     * @param onSweetClickListener
     * @param onCancelListener
     */
    protected void getWarmDialogTwoBtn(String msg, SweetAlertDialog.OnSweetClickListener onSweetClickListener, SweetAlertDialog.OnSweetClickListener onCancelListener) {
        sweetAlertDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE);
        sweetAlertDialog.setTitleText(msg);
        sweetAlertDialog.setConfirmText("确定");
        sweetAlertDialog.setCancelText("取消");
        sweetAlertDialog.setConfirmClickListener(onSweetClickListener);
        sweetAlertDialog.setCancelClickListener(onCancelListener);
        sweetAlertDialog.show();
    }

    /**
     * 成功对话框
     *
     * @param msg
     * @param onSweetClickListener
     */
    protected void getSuccessDialog(String msg, SweetAlertDialog.OnSweetClickListener onSweetClickListener) {
        sweetAlertDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE);
        sweetAlertDialog.setTitleText(msg);
        sweetAlertDialog.setConfirmText("确定");
        sweetAlertDialog.setConfirmClickListener(onSweetClickListener);
        sweetAlertDialog.show();
    }


    /**
     * 失败对话框
     *
     * @param msg
     * @param onSweetClickListener
     */
    protected void getErrorDialog(String msg, SweetAlertDialog.OnSweetClickListener onSweetClickListener) {
        sweetAlertDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE);
        sweetAlertDialog.setTitleText(msg);
        sweetAlertDialog.setConfirmText("确定");
        sweetAlertDialog.setConfirmClickListener(onSweetClickListener);
        sweetAlertDialog.show();
    }

    /**
     * 加载对话框
     *
     * @param msg
     */
    protected void getProgressDialog(String msg) {
        sweetAlertDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText(msg);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.show();
    }

    /**
     * 关闭加载对话框
     */
    protected void dismissProgressDialog() {
        if (sweetAlertDialog != null) {
            sweetAlertDialog.dismiss();
        }
    }

    public Context getMContext() {
        return mContext;
    }

}

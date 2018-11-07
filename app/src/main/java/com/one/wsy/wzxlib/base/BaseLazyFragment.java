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
 * 描述：
 * 名称: BaseLazyFragment
 *
 * @author: wsy
 * 版本: 1.0
 * 日期: 2018/11/7 9:30
 */
public abstract class BaseLazyFragment extends Fragment implements View.OnClickListener {

    protected final String TAG = this.getClass().getSimpleName();

    private boolean isDoubleClick = true;

    protected SweetAlertDialog sweetAlertDialog;

    private Context mContext;

    /**
     * 视图是否已经初初始化
     */
    protected boolean isInit = false;
    protected boolean isLoad = false;

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
        isInit = true;
        /**初始化的时候去加载数据**/
        isCanLoadData();
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


    /**
     * 视图是否已经对用户可见，系统的方法
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isCanLoadData();
    }

    /**
     * 是否可以加载数据
     * 可以加载数据的条件：
     * 1.视图已经初始化
     * 2.视图对用户可见
     */
    private void isCanLoadData() {
        if (!isInit) {
            return;
        }

        if (getUserVisibleHint()) {
            lazyLoad();
            isLoad = true;
        } else {
            if (isLoad) {
                stopLoad();
            }
        }
    }


    /**
     * 视图销毁的时候讲Fragment是否初始化的状态变为false
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isInit = false;
        isLoad = false;
    }


    /**
     * 当视图初始化并且对用户可见的时候去真正的加载数据
     */
    protected abstract void lazyLoad();

    /**
     * 当视图已经对用户不可见并且加载过数据，如果需要在切换到其他页面时停止加载数据，可以调用此方法
     */
    protected void stopLoad() {
    }
}

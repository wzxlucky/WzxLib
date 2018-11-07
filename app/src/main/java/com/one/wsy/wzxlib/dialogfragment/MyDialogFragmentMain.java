package com.one.wsy.wzxlib.dialogfragment;

import android.view.View;
import android.widget.Button;

import com.one.wsy.wzxlib.R;
import com.one.wsy.wzxlib.base.BaseActivity;


/**
 * 描述：DialogFragment对话框
 * 名称: MyDialogFragmentMain
 * 版本: 1.0
 * 日期: 2018/5/4 15:45
 *
 * @author wsy
 */
public class MyDialogFragmentMain extends BaseActivity {

    private Button btn;

    @Override
    public void setContentView() {
        setContentView(R.layout.dialog_fragment_main);
    }

    @Override
    public void initView() {
        btn = findView(R.id.btn);
    }

    @Override
    public void initData() {
        btn.setOnClickListener(this);
    }

    @Override
    public void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.btn:
                MyDialogFragment myDialogFragment = new MyDialogFragment();
                myDialogFragment.show(getSupportFragmentManager(), "");
                break;
            default:
        }
    }
}

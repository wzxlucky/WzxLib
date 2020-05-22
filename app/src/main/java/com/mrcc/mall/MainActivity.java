package com.mrcc.mall;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.mrcc.mall.base.BaseActivity;
import com.mrcc.mall.databinding.ActivityMainBinding;
import com.mrcc.mall.utils.widget.DialogUtils;

/**
 * @author wsy
 */
public class MainActivity extends BaseActivity {

    private ActivityMainBinding mBinding;

    @Override
    protected void setContentView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    protected void initData() {

        mBinding.tvLoading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtils.getInstance().showLoading(MainActivity.this);
            }
        });
    }

}

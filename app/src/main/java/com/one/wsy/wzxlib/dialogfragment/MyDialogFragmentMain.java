package com.one.wsy.wzxlib.dialogfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.one.wsy.wzxlib.R;


/**
 * 描述：
 * 名称: MyDialogFragmentMain
 * 作者: wsy
 * 版本: 1.0
 * 日期: 2018/5/4 15:45
 */
public class MyDialogFragmentMain extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_fragment_main);
        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialogFragment myDialogFragment = new MyDialogFragment();
                myDialogFragment.show(getSupportFragmentManager(), "");
            }
        });
    }
}

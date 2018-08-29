package com.one.wsy.wzxlib.flylayout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.one.wsy.wzxlib.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class FlyLayoutFragment extends Fragment {


    private static final String TAG = "FlyLayoutFragment";
    private View rootView;
    private String title;
    private TextView tv;

    public static FlyLayoutFragment getInstance(String title) {
        FlyLayoutFragment flyLayoutFragment = new FlyLayoutFragment();
        flyLayoutFragment.title = title;
        return flyLayoutFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fly_layout_fr, container, false);
        tv = (TextView) rootView.findViewById(R.id.tv);
        return rootView;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        switch (Integer.valueOf(messageEvent.getMessage())) {
            case 0:
                tv.setText("这是星期一");
                break;
            case 1:
                tv.setText("这是星期二");
                break;
            case 2:
                tv.setText("这是星期三");
                break;
            case 3:
                tv.setText("这是星期四");
                break;
            case 4:
                tv.setText("这是星期五");
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

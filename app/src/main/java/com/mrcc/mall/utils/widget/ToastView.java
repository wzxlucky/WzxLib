package com.mrcc.mall.utils.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.mrcc.mall.R;

/**
 * @author wsy
 */
public class ToastView extends FrameLayout {

    private TextView toastText = null;

    public ToastView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public ToastView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ToastView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public ToastView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        addView(View.inflate(context, R.layout.toast_view, null));
        toastText = findViewById(R.id.tvToast);
    }

    public void setText(String text) {
        toastText.setText(text);
    }

    public void setTextSize(int size) {
        toastText.setTextSize(size);
    }

    public void setTextColor(int color) {
        toastText.setTextColor(color);
    }
}

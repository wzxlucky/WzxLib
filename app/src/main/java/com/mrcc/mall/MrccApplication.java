package com.mrcc.mall;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.orhanobut.hawk.Hawk;

/**
 * @author wsy
 */
public class MrccApplication extends Application {

    private static MrccApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        Hawk.init(this).build();
    }

    public static MrccApplication getInstance() {
        return application;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}

package com.one.wsy.wzxlib.util.http;

import android.accounts.NetworkErrorException;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 描述：
 * 名称: HttpObserverArray
 * 作者: wsy
 * 版本: 1.0
 * 日期: 2018/6/6 15:47
 */
public abstract class HttpObserverArray<T> implements Observer<HttpResultArray<T>> {

    public HttpObserverArray() {
    }


    @Override
    public void onSubscribe(Disposable d) {
        onHttpStart(d);
    }

    @Override
    public void onNext(HttpResultArray<T> tHttpResultArray) {
        try {
            onSuccees(tHttpResultArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onError(Throwable e) {
        try {
            if (e instanceof ConnectException
                    || e instanceof TimeoutException
                    || e instanceof NetworkErrorException
                    || e instanceof UnknownHostException) {
                onFailure(e, true);
            } else {
                onFailure(e, false);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }


    @Override
    public void onComplete() {
        onHttpEnd();
    }

    protected abstract void onSuccees(HttpResultArray<T> t) throws Exception;

    protected abstract void onFailure(Throwable e, boolean isNetError) throws Exception;

    protected abstract void onHttpStart(Disposable d);

    protected abstract void onHttpEnd();
}

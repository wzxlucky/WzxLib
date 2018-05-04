package com.one.wsy.wzxlib.util.http;

import android.accounts.NetworkErrorException;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 描述：
 * 名称: HttpObserver
 * 作者: wsy
 * 版本: 1.0
 * 日期: 2018/4/18 10:02
 */
public abstract class HttpObserver<T> implements Observer<HttpResult<T>> {

    public HttpObserver() {
    }

    @Override
    public void onSubscribe(Disposable d) {
        onHttpStart(d);
    }


    @Override
    public void onNext(HttpResult<T> tHttpResult) {
        try {
            onSuccees(tHttpResult);
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

    protected abstract void onSuccees(HttpResult<T> t) throws Exception;

    protected abstract void onFailure(Throwable e, boolean isNetError) throws Exception;

    protected abstract void onHttpStart(Disposable d);

    protected abstract void onHttpEnd();
}

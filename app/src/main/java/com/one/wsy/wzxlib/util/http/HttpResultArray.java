package com.one.wsy.wzxlib.util.http;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 * 名称: HttpResultArray
 * 作者: wsy
 * 版本: 1.0
 * 日期: 2018/6/6 15:46
 */
public class HttpResultArray<T> {

    private int code;
    private String msg;
    private List<T> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg == null ? "" : msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getData() {
        if (data == null) {
            return new ArrayList<>();
        }
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}

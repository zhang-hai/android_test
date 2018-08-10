package com.sxw.unittestdemo.listener;

/**
 * Created by zhanghai on 2018/7/3.
 * function：
 */

public interface IDataCallBack {
    void onSuccess(String result);
    void onFailed(String error);
}

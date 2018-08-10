package com.sxw.unittestdemo.mvp.model;

import android.support.annotation.NonNull;

import com.sxw.unittestdemo.listener.IDataCallBack;
import com.sxw.unittestdemo.mvp.base.IBaseModel;

/**
 * Created by zhanghai on 2018/7/3.
 * function：
 */

public interface ILoginModel extends IBaseModel {
    void login(@NonNull String userName,@NonNull String password,@NonNull IDataCallBack callBack);
}

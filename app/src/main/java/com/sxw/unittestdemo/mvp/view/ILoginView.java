package com.sxw.unittestdemo.mvp.view;

import com.sxw.unittestdemo.mvp.base.IBaseView;

/**
 * Created by zhanghai on 2018/7/3.
 * function：
 */

public interface ILoginView extends IBaseView {
    void setUserNameEditText(String userName);
    String getUserNameEditText();
    void setPasswordEditText(String password);
    String getPasswordEditText();

    void loginResult(boolean result,String data);
}

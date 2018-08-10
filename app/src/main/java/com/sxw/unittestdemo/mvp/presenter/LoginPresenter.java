package com.sxw.unittestdemo.mvp.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.sxw.unittestdemo.listener.IDataCallBack;
import com.sxw.unittestdemo.mvp.base.BasePresenter;
import com.sxw.unittestdemo.mvp.model.ILoginModel;
import com.sxw.unittestdemo.mvp.model.LoginModelImpl;
import com.sxw.unittestdemo.mvp.view.ILoginView;

import javax.inject.Inject;

/**
 * Created by zhanghai on 2018/7/3.
 * function：
 */

public class LoginPresenter extends BasePresenter<ILoginModel,ILoginView> {

    @Inject
    public LoginPresenter(ILoginModel model, ILoginView view) {
        super(model, view);
    }


    public void login(String userName,String password){
        if(TextUtils.isEmpty(userName) || userName.length() != 11){
            Log.i("login","手机号不正确");
            getView().showToast("手机号不正确");
            return;
        }
        if(TextUtils.isEmpty(password) || password.length() != 6){
            Log.i("login","密码不正确");
            getView().showToast("密码不正确");
            return;
        }

        getView().showLoading();
        model.login(userName,password,new IDataCallBack() {
            @Override
            public void onSuccess(String result) {
                if (getView() != null){
                    getView().hideLoading();
                    getView().loginResult(true,result);
                }
            }

            @Override
            public void onFailed(String error) {
                if (getView() != null){
                    getView().hideLoading();
                    getView().loginResult(false,error);
                }
            }
        });
    }
}

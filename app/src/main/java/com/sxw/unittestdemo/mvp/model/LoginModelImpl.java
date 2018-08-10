package com.sxw.unittestdemo.mvp.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.sxw.unittestdemo.App;
import com.sxw.unittestdemo.listener.IDataCallBack;
import com.sxw.unittestdemo.mvp.base.BaseModel;
import com.sxw.unittestdemo.mvp.bean.RefreshToken;
import com.sxw.unittestdemo.mvp.bean.UserBean;
import com.sxw.unittestdemo.utils.AESUtils;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhanghai on 2018/7/3.
 * function：
 */

public class LoginModelImpl extends BaseModel implements ILoginModel {

    @Inject
    public LoginModelImpl(){

    }

    /**
     * 直接使用 Retrofit2 访问网络
     *
     * @param userName
     * @param password
     * @param callBack
     */
    @Override
    public void login(@NonNull final String userName, @NonNull final String password, @NonNull final IDataCallBack callBack) {
        UserBean userBean = new UserBean();
        userBean.setAccount("511101201703290011");
        userBean.setAccountType(1);
        userBean.setPlatform("ANDROID");
        userBean.setClient("TEACHER");
        userBean.setApp("XWKW");//课外
        try {
            userBean.setPassword(AESUtils.Encrypt("111111"));
            final Call<ResponseBody> reqCall = App.getApp().getApiHttpService().requestLogin(userBean);
            Log.i("Http-->>",reqCall.request().url().toString());
            reqCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    if(response.isSuccessful() && response.code() == 200){
                        try {
                            String body = response.body().string();
                            callBack.onSuccess(body);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                    callBack.onFailed(t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * retrofit + rxjava + rxandroid 访问网络
     * @param userName
     * @param password
     * @param callBack
     */
    @Override
    public void loginRxJava(@NonNull final String userName, @NonNull final String password, @NonNull final IDataCallBack callBack){
        UserBean userBean = new UserBean();
        userBean.setAccount("511101201703290011");
        userBean.setAccountType(1);
        userBean.setPlatform("ANDROID");
        userBean.setClient("TEACHER");
        userBean.setApp("XWKW");//课外
        try {
            userBean.setPassword(AESUtils.Encrypt("111111"));
            App.getApp().getApiHttpService()
                    .requestLogin2(userBean)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<RefreshToken>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(RefreshToken responseBody) {
                            Log.d("LoginModelImpl",responseBody.getData().toString());
                            callBack.onSuccess(responseBody.getData().toString());
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d("LoginModelImpl", e.toString());
                            callBack.onFailed(e.toString());
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        }catch (Exception ex){

        }
    }
}

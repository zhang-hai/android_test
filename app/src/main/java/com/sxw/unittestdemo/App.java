package com.sxw.unittestdemo;

import android.app.Application;

import com.sxw.unittestdemo.di.component.DaggerAppComponent;
import com.sxw.unittestdemo.di.module.HttpModule;
import com.sxw.unittestdemo.network.ApiHttpService;

import javax.inject.Inject;

/**
 * Created by zhanghai on 2018/7/5.
 * function：
 */

public class App extends Application {

    private static App mApp;

    @Inject
    ApiHttpService apiHttpService;

    @Override
    public void onCreate() {
        super.onCreate();

        mApp = mApp == null ? this : mApp;
        DaggerAppComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }

    public static App getApp(){
        return mApp;
    }


    public ApiHttpService getApiHttpService(){
        return apiHttpService;
    }

}

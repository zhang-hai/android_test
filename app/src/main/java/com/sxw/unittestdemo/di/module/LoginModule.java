package com.sxw.unittestdemo.di.module;

import com.sxw.unittestdemo.mvp.model.ILoginModel;
import com.sxw.unittestdemo.mvp.view.ILoginView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhanghai on 2018/7/5.
 * function：
 */
@Module
public class LoginModule {
    private ILoginView view;
    private ILoginModel model;

    public LoginModule(ILoginModel model,ILoginView view){
        this.model = model;
        this.view = view;
    }

    @Provides
    ILoginModel provideLoginModel(){return this.model;}

    @Provides
    ILoginView provideLoginView(){return this.view;}
}

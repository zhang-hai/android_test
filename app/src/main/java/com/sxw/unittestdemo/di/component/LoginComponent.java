package com.sxw.unittestdemo.di.component;

import com.sxw.unittestdemo.di.module.HttpModule;
import com.sxw.unittestdemo.di.module.LoginModule;
import com.sxw.unittestdemo.mvp.ui.DaggerLoginActivity;

import dagger.Component;

/**
 * Created by zhanghai on 2018/7/5.
 * function：
 */
@Component(modules = {LoginModule.class})
public interface LoginComponent {
    void inject(DaggerLoginActivity activity);
}

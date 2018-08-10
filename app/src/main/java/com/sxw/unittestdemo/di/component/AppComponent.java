package com.sxw.unittestdemo.di.component;

import com.sxw.unittestdemo.App;
import com.sxw.unittestdemo.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by zhanghai on 2018/7/5.
 * function：
 */
@Singleton
@Component(modules = HttpModule.class)
public interface AppComponent {
    void inject(App app);
}

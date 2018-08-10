package com.sxw.unittestdemo.di.module;

import com.sxw.unittestdemo.network.ApiHttpService;
import com.sxw.unittestdemo.network.GsonAESConverterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhanghai on 2018/7/5.
 * function：
 */
@Module
public class HttpModule {

    @Singleton
    @Provides
    ApiHttpService provideApiHttpService(){
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)       //设置连接超时
                .readTimeout(10000L,TimeUnit.MILLISECONDS)          //设置读取超时
                .writeTimeout(10000L,TimeUnit.MILLISECONDS)         //设置写入超时
                .build();

        return new Retrofit.Builder()
                .baseUrl("http://api2.test.sxw.cn/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiHttpService.class);
    }

}

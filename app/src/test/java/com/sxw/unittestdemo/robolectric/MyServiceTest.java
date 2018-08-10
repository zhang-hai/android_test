package com.sxw.unittestdemo.robolectric;

import com.sxw.unittestdemo.MyRule;
import com.sxw.unittestdemo.MyService;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ServiceController;
import org.robolectric.shadows.ShadowLog;

/**
 * Created by zhanghai on 2018/7/3.
 * function：
 */
@RunWith(RobolectricTestRunner.class)
public class MyServiceTest {
    @Rule
    public MyRule myRule = new MyRule();

    private ServiceController<MyService> controller;
    private MyService mService;

    @Before
    public void setup(){
        ShadowLog.stream = System.out;
        //在测试之前，获取Service的一个controller
        controller = Robolectric.buildService(MyService.class);
        //通过controller的get方法获取到MyService对象
        mService = controller.get();
    }

    /**
     * 简单验证service的生命周期方法
     * @throws Exception
     */
    @Test
    public void testServiceLifecycle() throws Exception{
        controller.create();

        controller.startCommand(0,0);
        controller.bind();
        controller.unbind();
        controller.destroy();

    }
}

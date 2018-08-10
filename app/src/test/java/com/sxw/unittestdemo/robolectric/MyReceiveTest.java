package com.sxw.unittestdemo.robolectric;

import android.content.Intent;
import android.content.pm.PackageManager;

import com.sxw.unittestdemo.MyBroadcastReceive;
import com.sxw.unittestdemo.MyRule;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.ShadowPackageManager;

/**
 * Created by zhanghai on 2018/7/3.
 * function：测试广播
 */
@RunWith(RobolectricTestRunner.class)
public class MyReceiveTest {
    @Rule
    public MyRule myRule = new MyRule();

    private final String action = "com.sxw.testdemo.androidut";

    /**
     * 测试是否注册相应的广播接收器
     */
    @Test
    public void testRegisterReceive(){
        ShadowApplication shadowApplication = ShadowApplication.getInstance();

        Intent intent = new Intent(action);
        //新版本使用这种方式查询对应的receiver
//        RuntimeEnvironment.application.getPackageManager().queryBroadcastReceivers(intent,0x0001);

        Assert.assertTrue(shadowApplication.hasReceiverForIntent(intent));

    }


    /**
     * 模拟发送广播，并验证广播处理结果
     */
    @Test
    public void testBroadcast(){
        //发送广播
        Intent intent = new Intent(action);
        MyBroadcastReceive receive = new MyBroadcastReceive();
        receive.onReceive(RuntimeEnvironment.application,intent);

        //验证广播 如有逻辑处理 可以在此处进行处理
    }

}

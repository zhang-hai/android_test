package com.sxw.unittestdemo.robolectric;

import android.app.Application;

import com.sxw.unittestdemo.MyRule;
import com.sxw.unittestdemo.R;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

/**
 * Created by zhanghai on 2018/7/3.
 * function：访问APP资源文件
 */
@RunWith(RobolectricTestRunner.class)
public class ResourceTest {
    private final static String TAG = ResourceTest.class.getName();
    @Rule
    public MyRule myRule = new MyRule();

    /**
     * 访问资源文件
     */
    @Test
    public void testResource(){
        //使用RuntimeEnvironment.application可以获取到Application
        Application application = RuntimeEnvironment.application;

        String app_name = application.getResources().getString(R.string.app_name);

        Assert.assertEquals("UnitTestDemo",app_name);
    }
}

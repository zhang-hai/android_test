package com.sxw.unittestdemo.robolectric;

import android.app.AlertDialog;
import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;

import com.sxw.unittestdemo.MainActivity;
import com.sxw.unittestdemo.MainActivity_;
import com.sxw.unittestdemo.MyRule;
import com.sxw.unittestdemo.R;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowAlertDialog;
import org.robolectric.shadows.ShadowToast;

/**
 * Created by zhanghai on 2018/7/2.
 * function：robolectric测试
 */
@RunWith(RobolectricTestRunner.class)
public class MainActivityRobolectricTest {
    private MainActivity mainActivity;
    private Button btnLogin;

    @Rule
    public MyRule myRule = new MyRule();

    @Before
    public void initup(){
        //此处创建activity，并执行生命周期方法onCreate、onStart、onResume
        mainActivity = Robolectric.setupActivity(MainActivity_.class);
        btnLogin = (Button) mainActivity.findViewById(R.id.btn_login);
    }

    @Test
    public void testMainActivity(){
        Assert.assertNotNull(mainActivity);
    }

    /**
     * 验证点击按钮，并验证toast
     */
    @Test
    public void testLoginClick(){
        //模拟点击登录按钮
        btnLogin.performClick();

    }

    /**
     * 验证Toast
     */
    @Test
    public void testToast(){
        //获取toast，并验证是null，此时还未弹出
        Toast toast = ShadowToast.getLatestToast();
        Assert.assertNull(toast);

        //模拟点击登录按钮
        btnLogin.performClick();

        //再次获取最新的toast，检查不为null
        toast = ShadowToast.getLatestToast();
        Assert.assertNotNull(toast);

        //验证是否是对应的toast
        ShadowToast shadowToast = Shadows.shadowOf(toast);
        Assert.assertEquals(Toast.LENGTH_SHORT,shadowToast.getDuration());
        Assert.assertEquals("登录成功",ShadowToast.getTextOfLatestToast());
    }

    /**
     * 验证页面跳转
     */
    @Test
    public void testJump(){
        //先点击
        btnLogin.performClick();

        //先获取Shadow类
        ShadowActivity shadowActivity = Shadows.shadowOf(mainActivity);
        //借助shadow类获取下一个要启动的Activity的Intent
        Intent intent = shadowActivity.getNextStartedActivity();
        //验证intent的正确性
        Assert.assertEquals(intent.getComponent().getClassName(),MainActivity_.class.getName());
    }

    /**
     * 验证dialog
     */
    @Test
    public void testDialog(){
        //先获取最新的一个alertDialog
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        //验证为null
        Assert.assertNull(dialog);

        //由于调用对话框是通过按钮点击调用，所以此处需要模拟按钮点击事件
        btnLogin.performClick();

        //重新获取最新的dialog，并进行非空判断
        dialog = ShadowAlertDialog.getLatestAlertDialog();
        Assert.assertNotNull(dialog);

        //根据使用shadowof获取dialog对应的ShadowAlertDialog
        ShadowAlertDialog shadowAlertDialog = Shadows.shadowOf(dialog);
        Assert.assertEquals("Robolectric验证对话框！",shadowAlertDialog.getMessage());
        //模拟点击对话框的ok按钮
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).performClick();
        //模拟点击对话框的cancel按钮
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).performClick();
    }


    /**
     * Activity生命周期测试
     */
    @Test
    public void testLifecycle(){
        //创建一个MainActivity的一个控制器
        ActivityController<MainActivity_> controller = Robolectric.buildActivity(MainActivity_.class);
        MainActivity mActivity = controller.get();

        //调用Activity的performCreate方法验证onCreate
        controller.create();
        org.junit.Assert.assertEquals("onCreate",mActivity.getLifecycleState());

        //Activity的performStart方法验证onStart
        controller.start();
        org.junit.Assert.assertEquals("onStart",mActivity.getLifecycleState());

        //调用Activity的performResume方法验证onResume
        controller.resume();
        org.junit.Assert.assertEquals("onResume",mActivity.getLifecycleState());

        //调用Activity的performPause方法验证onPause
        controller.pause();
        org.junit.Assert.assertEquals("onPause",mActivity.getLifecycleState());

        //调用Activity的performStop方法验证onStop
        controller.stop();
        org.junit.Assert.assertEquals("onStop",mActivity.getLifecycleState());

        //调用Activity的performRestart方法验证onRestart
        controller.restart();
        //此处需要使用onStart，因为调用Activity的PerformRestart后会调用performStart
        org.junit.Assert.assertEquals("onStart",mActivity.getLifecycleState());

        //调用Activity的performDestroy方法验证onDestroy
        controller.destroy();
        org.junit.Assert.assertEquals("onDestroy",mActivity.getLifecycleState());
    }
}

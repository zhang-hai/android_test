package com.sxw.unittestdemo.daggermvp;

import android.content.Intent;
import android.widget.Button;

import com.sxw.unittestdemo.R;
import com.sxw.unittestdemo.mvp.BaseTest;
import com.sxw.unittestdemo.mvp.ui.DaggerLoginActivity;
import com.sxw.unittestdemo.mvp.ui.DaggerLoginActivity_;
import com.sxw.unittestdemo.mvp.ui.LoginActivity;
import com.sxw.unittestdemo.mvp.ui.LoginActivity_;
import com.sxw.unittestdemo.mvp.ui.MainActivity2_;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowProgressDialog;
import org.robolectric.shadows.ShadowToast;


/**
 * Created by zhanghai on 2018/7/4.
 * function：测试DaggerLoginActivity
 */
@RunWith(RobolectricTestRunner.class)
public class DaggerLoginActivityTest extends BaseTest{

    private DaggerLoginActivity mActivity;
    private Button btnLogin;

    @Before
    public void setup(){
//        ShadowLog.stream = System.out;

        mActivity = Robolectric.setupActivity(DaggerLoginActivity_.class);
        btnLogin = mActivity.findViewById(R.id.btn_login);
    }

    @Test
    public void testLogin(){
        //验证输入手机号
        mActivity.setUserNameEditText("123456789");
        mActivity.setPasswordEditText("11235");
        btnLogin.performClick();
        Assert.assertEquals("手机号不正确", ShadowToast.getTextOfLatestToast());

        //验证密码是否正确
        mActivity.setUserNameEditText("12345678900");
        mActivity.setPasswordEditText("1234");
        btnLogin.performClick();
        Assert.assertEquals("密码不正确",ShadowToast.getTextOfLatestToast());

        //验证登录progressDialog
        mActivity.setUserNameEditText("19900000000");
        mActivity.setPasswordEditText("111111");
        btnLogin.performClick();
        Assert.assertNotNull(ShadowProgressDialog.getLatestDialog());

        //验证activity跳转
        //先获取Shadow类
//        ShadowActivity shadowActivity = Shadows.shadowOf(mActivity);
//        Intent intent = shadowActivity.getNextStartedActivity();
//        Assert.assertEquals(MainActivity2_.class.getName(),intent.getComponent().getClassName());
    }

}

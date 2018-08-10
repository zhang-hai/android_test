package com.sxw.unittestdemo.mvp.presenter;

import com.sxw.unittestdemo.listener.IDataCallBack;
import com.sxw.unittestdemo.mvp.BaseTest;
import com.sxw.unittestdemo.mvp.bean.UserBean;
import com.sxw.unittestdemo.mvp.model.LoginModelImpl;
import com.sxw.unittestdemo.mvp.view.ILoginView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowLog;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhanghai on 2018/7/4.
 * function：
 */
@RunWith(RobolectricTestRunner.class)
public class LoginPresenterTest extends BaseTest{

    private LoginPresenter mPresenter;

    @Mock
    private ILoginView loginView;

    @Mock
    private LoginModelImpl loginModel;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();//使用MockitoRule

    @Before
    public void setup(){
        ShadowLog.stream = System.out;
        mPresenter = new LoginPresenter(loginModel,loginView);
    }

    @Test
    public void testLogin() throws Exception{
        //验证手机号不正确
        mPresenter.login("1334123","1234");
        Mockito.verify(loginView).showToast("手机号不正确");

        //验证密码
        mPresenter.login("13300000001","1111");
        Mockito.verify(loginView).showToast("密码不正确");

        //验证正常数据
        mPresenter.login("13300000001","123456");
        //验证方法验证否调用了showLoading();
        Mockito.verify(loginView).showLoading();

//        Mockito.verify(loginView).hideLoading();
//
//        Mockito.verify(loginView).loginResult(true,"{\"name\":\"13300000001\",\"password\":\"123456\"}");
    }

}

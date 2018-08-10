package com.sxw.unittestdemo.mvp.presenter;

import com.sxw.unittestdemo.mvp.BaseTest;
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

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zhanghai on 2018/7/4.
 * function：p层单元测试
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
//        loginModel = new LoginModelImpl();
        mPresenter = new LoginPresenter(loginModel,loginView);
        //异步同步转换
//        intRxJava();
    }

    /**
     * 测试调用p层中login方法
     * @throws Exception
     */
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

        ///////////////网络访问之后异步回调执行的操作/////////////////
//        Mockito.verify(loginView).hideLoading();
        //此处会报错误，因为调用了loginResult但是验证参数的时候不一致，故会报错
//        Mockito.verify(loginView).loginResult(true,"{\"name\":\"13300000001\",\"password\":\"123456\"}");
    }

    /**
     * 为了测试网络返回后的操作，需要进行异步与同步转换
     *
     * 主要通过将异步请求转成同步，达到测试网络的数据目的
     */
//    private void intRxJava(){
//        RxJavaPlugins.reset();
//        RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
//            @Override
//            public Scheduler apply(Scheduler scheduler) throws Exception {
//                return Schedulers.trampoline();
//            }
//        });
//        RxAndroidPlugins.reset();
//        RxAndroidPlugins.setMainThreadSchedulerHandler(new Function<Scheduler, Scheduler>() {
//            @Override
//            public Scheduler apply(Scheduler scheduler) throws Exception {
//                return Schedulers.trampoline();
//            }
//        });
//    }
}

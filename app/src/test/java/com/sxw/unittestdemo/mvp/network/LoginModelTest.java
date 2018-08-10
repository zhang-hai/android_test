package com.sxw.unittestdemo.mvp.network;

import com.sxw.unittestdemo.App;
import com.sxw.unittestdemo.BuildConfig;
import com.sxw.unittestdemo.MyRule;
import com.sxw.unittestdemo.listener.IDataCallBack;
import com.sxw.unittestdemo.mvp.bean.UserBean;
import com.sxw.unittestdemo.mvp.model.LoginModelImpl;
import com.sxw.unittestdemo.network.ApiHttpService;
import com.sxw.unittestdemo.utils.AESUtils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhanghai on 2018/7/6.
 * function：
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class LoginModelTest {

    @Rule
    public MyRule myRule = new MyRule();

    private ApiHttpService apiHttpService;

    LoginModelImpl loginModel;

    @Mock
    IDataCallBack callBack;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setup(){
        apiHttpService = App.getApp().getApiHttpService();
        loginModel = new LoginModelImpl();
        ShadowLog.stream = System.out;
        intRxJava();
    }

    @Test
    public void loginTest(){
        UserBean userBean = new UserBean();
        userBean.setAccount("511101201703290011");
        userBean.setAccountType(1);
        userBean.setPlatform("ANDROID");
        userBean.setClient("TEACHER");
        userBean.setApp("XWKW");//课外

        try {
            userBean.setPassword(AESUtils.Encrypt("111111"));
            Response<ResponseBody> response = apiHttpService.requestLogin(userBean).execute();
            System.out.println(response.body().string());
        } catch (Exception e) {
            e.printStackTrace();
        }

//        final CountDownLatch mLatch = new CountDownLatch(1);
//
//        apiHttpService.requestLogin(userBean).enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                mLatch.countDown();
//                if(response.isSuccessful() && response.code() == 200){
//                    try {
//                        String body = response.body().string();
//                        callBack.onSuccess(body);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                mLatch.countDown();
//            }
//        });
//
//        try {
//            mLatch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * Retrofit2 + RxJava访问网络，主要通过将异步请求转成同步，达到测试网络的数据目的，
     * 因为异步时时拿不到网络数据
     */
    @Test
    public void loginTest2(){
        Assert.assertNotNull(loginModel);
        Assert.assertNotNull(callBack);
        loginModel.loginRxJava("username","password",callBack);
    }

    /**
     * 主要通过将异步请求转成同步，达到测试网络的数据目的
     */
    private void intRxJava(){
        RxJavaPlugins.reset();
        RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(Scheduler scheduler) throws Exception {
                return Schedulers.trampoline();
            }
        });
        RxAndroidPlugins.reset();
        RxAndroidPlugins.setMainThreadSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(Scheduler scheduler) throws Exception {
                return Schedulers.trampoline();
            }
        });
    }
}

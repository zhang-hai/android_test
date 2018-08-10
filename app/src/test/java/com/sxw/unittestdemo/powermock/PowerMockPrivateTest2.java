package com.sxw.unittestdemo.powermock;

import com.sxw.unittestdemo.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Created by zhanghai on 2018/7/2.
 * function：带@Runwith运行器的方式
 * 这种方式时，gradle中无需引入以下两个依赖：
 *  testCompile "org.powermock:powermock-module-junit4-rule:1.7.3"
 *  testCompile "org.powermock:powermock-classloading-xstream:1.7.3"
 */
@RunWith(PowerMockRunner.class)
public class PowerMockPrivateTest2 {
    @Test
    @PrepareForTest(User.class)
    public void testUserPrivate() throws Exception{
        //mock一个对象
        User mUser = PowerMockito.mock(User.class);

        PowerMockito.when(mUser.getUserLike()).thenCallRealMethod();
        PowerMockito.when(mUser,"likeFruit").thenReturn("桃子");

        System.out.println(mUser.getUserLike());
        //验证flavor是否调用了一次
        PowerMockito.verifyPrivate(mUser).invoke("likeFruit");
    }
}

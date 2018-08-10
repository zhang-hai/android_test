package com.sxw.unittestdemo.powermock;

import com.sxw.unittestdemo.User;

import org.junit.Rule;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;

/**
 * Created by zhanghai on 2018/7/2.
 * function：不带@RunWith运行器的方式
 *
 *  这种方式时，gradle中需要引入以下两个依赖：
 *  testCompile "org.powermock:powermock-module-junit4-rule:1.7.3"
 *  testCompile "org.powermock:powermock-classloading-xstream:1.7.3"
 */
public class PowerMockPrivateTest {

    @Rule
    public PowerMockRule rule = new PowerMockRule();

    @Test
    @PrepareForTest(User.class)
    public void testUserPrivate() throws Exception{
        User mUser = PowerMockito.mock(User.class);

        PowerMockito.when(mUser.getUserLike()).thenCallRealMethod();
        PowerMockito.when(mUser,"likeFruit").thenReturn("桃子");

        System.out.println(mUser.getUserLike());
        //验证flavor是否调用了一次
        PowerMockito.verifyPrivate(mUser).invoke("likeFruit");
    }
}

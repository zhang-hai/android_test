package com.sxw.unittestdemo.mock;

import com.sxw.unittestdemo.User;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 * Created by zhanghai on 2018/7/2.
 * function：mockito测试框架使用
 */

public class MockitoTest {

    //1.第一种mock方式，普通方式
    @Test
    public void testIsNotNull(){
        User user = mock(User.class); //<--使用mock方法
        Assert.assertNotNull(user);
    }


}

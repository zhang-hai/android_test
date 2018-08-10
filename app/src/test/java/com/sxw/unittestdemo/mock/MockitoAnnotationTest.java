package com.sxw.unittestdemo.mock;

import com.sxw.unittestdemo.User;
import com.sxw.unittestdemo.mvp.bean.UserBean;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Created by zhanghai on 2018/7/2.
 * function：第二种注解方式引入
 */

public class MockitoAnnotationTest {

    @Mock
    User mUser; //注解方式引入

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);//初始化
    }

    @Test
    public void testIsNotNull(){
        Assert.assertNotNull(mUser);
    }
}

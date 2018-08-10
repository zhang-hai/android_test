package com.sxw.unittestdemo.mock;

import com.sxw.unittestdemo.User;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by zhanghai on 2018/7/2.
 * function：第三种运行器方式
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoJunitRunnerTest {

    @Mock
    User mUser;

    @Test
    public void testIsNotNull(){
        Assert.assertNotNull(mUser);
    }
}

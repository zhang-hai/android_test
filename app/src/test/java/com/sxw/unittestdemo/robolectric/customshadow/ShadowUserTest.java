package com.sxw.unittestdemo.robolectric.customshadow;

import android.util.Log;

import com.sxw.unittestdemo.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import static org.junit.Assert.assertEquals;
import static org.robolectric.shadow.api.Shadow.extract;

/**
 * Created by zhanghai on 2018/7/16.
 * function：
 */
@RunWith(RobolectricTestRunner.class)
@Config(shadows = ShadowUser.class)
public class ShadowUserTest {
    @Before
    public void setUp() {
        ShadowLog.stream = System.out;
    }

    @Test
    public void testShadow(){
        User person = new User();
        //实际上调用的是ShadowPerson的方法
        Log.d("test", person.getUserName());
        Log.d("test", String.valueOf(person.getAge()));
        Log.d("test", String.valueOf(person.getUserId()));

        //获取Person对象对应的Shadow对象
        ShadowUser shadowPerson = extract(person);
        assertEquals("Herry", shadowPerson.getUserName());
        assertEquals(20, shadowPerson.getAge());


    }
}

package com.sxw.unittestdemo.robolectric.customshadow;

import com.sxw.unittestdemo.User;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;

/**
 * Created by zhanghai on 2018/7/16.
 * function：
 */
@Implements(User.class)
public class ShadowUser {

    @Implementation
    public int getUserId(){
        return 10000;
    }

    @Implementation
    public String getUserName(){
        return "Herry";
    }

    @Implementation
    public int getAge(){
        return 20;
    }
}

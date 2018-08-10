package com.sxw.unittestdemo.mvp;

import com.sxw.unittestdemo.MyRule;

import org.junit.Rule;

/**
 * Created by zhanghai on 2018/7/4.
 * function：
 */
public class BaseTest {
    @Rule
    public MyRule myRule = new MyRule();
}

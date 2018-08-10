package com.sxw.unittestdemo.mock;

import com.sxw.unittestdemo.User;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghai on 2018/8/10.
 * function：Spy一个真实的对象
 */

public class SpyTest {

    @Test
    public void testList(){
        List<String> list0 = new ArrayList<>();
        List list = Mockito.spy(list0);
        list.add("1111111");
        list.add("222222222");
        //设置预期值,必须在不越界的情况下设置，否则会越界
        Mockito.when(list.get(0)).thenReturn("hello");
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println("list size : " + list.size());
        System.out.println("list0 size : " + list.size());

    }
}

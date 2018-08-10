package com.sxw.unittestdemo.mock;

import com.sxw.unittestdemo.User;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;

import java.util.List;

/**
 * Created by zhanghai on 2018/7/2.
 * function：第四种MockitoRule方式，推荐使用这种方式
 */
public class MockitoRuleTest {
    @Mock
    User mUser;//使用@Mock注解


    @Mock
    List<String> list;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();//使用MockitoRule

    @Test
    public void testIsNotNull(){
        Assert.assertNotNull(mUser);
        Assert.assertNotNull("list is null",list);
    }


    //以下为常用的打桩方法(设置预期值)

    /****
     * mock的对象只能通过mock方法去设置预期值，调用对象中的方法设置，将无效
     * ****/
    @Test
    public void TestList(){
        //设置预期值
        Mockito.when(list.get(0)).thenReturn("hello");
        //获取预期值
        System.out.println(list.get(0));
        //对于Mock出的对象，没有设置预期值，不会出现越界问题，只会返回默认值
        System.out.println(list.get(1));

        //对于Mock出的对象，如果直接调用对象中的方法去设置值，是无效的
        list.add("1111111");
        list.add("222222222");

        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println("size : " + list.size());

    }


    //使用then打桩方法
    @Test
    public void testUserReturn(){
        //使用then
        //注意 !!! when参数必须调用的是method，如果调用的是公有变量，报错误
        Mockito.when(mUser.getUserName()).thenReturn("小明");
        Mockito.when(mUser.getAge()).thenThrow(new NullPointerException("性别不正确"));

        //输出小明
        System.out.println(mUser.getUserName());

        //抛出异常
//        System.out.println(mUser.getAge());

    }


    //@@@@@@@ 使用do打桩方法
    @Test
    public void testUserDoReturn(){

        Mockito.doReturn("小蓝").when(mUser).getUserName();

        Mockito.doThrow(new NullPointerException("哈哈，性别有问题")).when(mUser).getAge();

        //输出小蓝
        System.out.println(mUser.getUserName());

        //抛出异常
//        System.out.println(mUser.getAge());

    }

    //测试thenAnswer打桩方法
    @Test
    public void testUserAnswer(){
        Mockito.when(mUser.eat(Mockito.anyString())).thenAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return args[0].toString() + "，测试thenAnswer对结果进行拦截";
            }
        });

        System.out.println(mUser.eat("水果"));
    }

/////////////////////////////////////////// 常用验证方法 /////////////////////////////////
    /**
     * 如果不关心返回结果，而是关心方法有否被正确的参数调用过，这时候就应该使用验证方法了。
     */


    /**
     * 使用验证方法 verify(T mock)验证发生的某些行为
     *
     */
    @Test
    public void testVerifyAfter(){
        Mockito.when(mUser.getAge()).thenReturn(17);

        System.out.println(mUser.getAge());
        System.out.println(System.currentTimeMillis());

        //after 在给定的时间后进行验证
        Mockito.verify(mUser,Mockito.after(1000)).getAge();

        System.out.println(System.currentTimeMillis());

        //atLeast 至少进行n次验证
        Mockito.verify(mUser,Mockito.atLeast(1)).getAge();

    }

    /**
     * 使用atLeasty验证，至少进行n次验证
     */
    @Test
    public void testPersonVerifyAtLeast(){
        mUser.getAge();
        mUser.getAge();
        //至少验证2次
        Mockito.verify(mUser, Mockito.atLeast(2)).getAge();
    }

    /**
     * atMost 至多进行n次验证
     */
    @Test
    public void testPersonVerifyAtMost(){
        mUser.getAge();
        //至多验证2次
        Mockito.verify(mUser, Mockito.atMost(2)).getAge();
    }

    /**
     * times 验证调用方法的次数
     */
    @Test
    public void testPersonVerifyTimes(){
        mUser.getAge();
        mUser.getAge();
        //验证方法调用2次
        Mockito.verify(mUser, Mockito.times(2)).getAge();
    }

    /**
     * timeout 验证方法执行是否超时
     */
    @Test
    public void testPersonVerifyTimeout(){
        mUser.getAge();
        mUser.getAge();
        //验证方法在100ms超时前调用2次
        Mockito.verify(mUser, Mockito.timeout(100).times(2)).getAge();
    }
}

package com.sxw.unittestdemo;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Created by zhanghai on 2018/7/2.
 * function：测试中使用的Rule，用于打印测试开始和结束信息
 */

public class MyRule implements TestRule {
    @Override
    public Statement apply(final Statement base, final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                //evaluate前执行，相当于@Before
                String methodName = description.getMethodName();
                System.out.println(methodName + "测试开始--->" + System.currentTimeMillis());
                base.evaluate();
                //evaluate后执行，相当于@After
                System.out.println(methodName + "测试结束--->" + System.currentTimeMillis());
            }
        };
    }
}

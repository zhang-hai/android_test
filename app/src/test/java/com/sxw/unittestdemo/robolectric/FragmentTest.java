package com.sxw.unittestdemo.robolectric;

import com.sxw.unittestdemo.MainFragment;
import com.sxw.unittestdemo.MyRule;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

/**
 * Created by zhanghai on 2018/7/3.
 * function：测试fragment
 */
@RunWith(RobolectricTestRunner.class)
public class FragmentTest {
    @Rule
    public MyRule myRule = new MyRule();

    /**
     * 测试fragment,ui同Activity
     */
    @Test
    public void testFragment(){
        //支持使用support-v4的fragment
        MainFragment fragment = new MainFragment();
        SupportFragmentTestUtil.startFragment(fragment);
        Assert.assertNotNull(fragment);
        Assert.assertNotNull(fragment.getView());

        //使用android.app.fragment
//        MainFragment mainFragment = Robolectric.buildFragment(MainFragment.class).create().start().resume().get();
//        Assert.assertNotNull(mainFragment);
//        Assert.assertNotNull(mainFragment.getView());
    }


}

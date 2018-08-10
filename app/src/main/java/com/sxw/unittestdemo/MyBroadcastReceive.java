package com.sxw.unittestdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by zhanghai on 2018/7/3.
 * function：广播接收器
 */

public class MyBroadcastReceive extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent != null){
            String action = intent.getAction();

            System.out.println("收到广播 ~~~ ---->>>"+action);
        }
    }
}

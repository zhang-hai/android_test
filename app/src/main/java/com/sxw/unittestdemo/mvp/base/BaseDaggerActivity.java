package com.sxw.unittestdemo.mvp.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import javax.inject.Inject;

/**
 * Created by zhanghai on 2018/7/4.
 * function：
 */

public abstract class BaseDaggerActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView{
    //presenter对象
    @Inject
    protected P mPresenter;

    private ProgressDialog dialogLoading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        if (mPresenter != null)
            mPresenter.detachView();
    }

    @Override
    public void showLoading() {
        if(dialogLoading == null){
            dialogLoading = new ProgressDialog(this);
            dialogLoading.setMessage("正在加载中...");
            dialogLoading.show();
        }else {
            dialogLoading.setMessage("正在加载中...");
            dialogLoading.show();
        }
    }

    @Override
    public void hideLoading() {
        if (dialogLoading != null){
            if(dialogLoading.isShowing()){
                dialogLoading.dismiss();
            }
            dialogLoading = null;
        }
    }

    /**
     * 显示toast信息
     * @param toast
     */
    @Override
    public void showToast(String toast){
        Toast.makeText(this,toast,Toast.LENGTH_SHORT).show();
    }
}

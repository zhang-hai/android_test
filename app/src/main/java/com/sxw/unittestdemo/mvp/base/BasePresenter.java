package com.sxw.unittestdemo.mvp.base;

import java.lang.ref.WeakReference;

/**
 * Created by zhanghai on 2018/7/3.
 * function：
 */

public class BasePresenter<M extends IBaseModel,V extends IBaseView> {
    protected M model;
    private WeakReference<V> weakReference;

    public BasePresenter(M model,V view){
        this.model = model;
        attachView(view);
    }

    /**
     * 建立关联
     * @param view
     */
    public void attachView(V view){
        weakReference = new WeakReference<V>(view);
    }

    /**
     * 获取view
     * @return
     */
    public V getView(){
        if (isViewAttached()){
            return weakReference.get();
        }
        return null;
    }

    /**
     * 解除关联
     */
    public void detachView(){
        if(weakReference != null){
            weakReference.clear();
            weakReference = null;
        }
    }

    /**
     * UI展示相关的操作需要判断一下 Activity 是否已经 finish.
     * <p>
     * todo : 只有当 isActivityAlive 返回true时才可以执行与Activity相关的操作,
     * 比如 弹出Dialog、Window、跳转Activity等操作.
     *
     * @return boolean
     */
    public boolean isViewAttached(){
        return weakReference != null && weakReference.get() != null;
    }

}

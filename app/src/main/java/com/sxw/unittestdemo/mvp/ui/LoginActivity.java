package com.sxw.unittestdemo.mvp.ui;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.sxw.unittestdemo.R;
import com.sxw.unittestdemo.mvp.base.BaseActivity;
import com.sxw.unittestdemo.mvp.model.LoginModelImpl;
import com.sxw.unittestdemo.mvp.presenter.LoginPresenter;
import com.sxw.unittestdemo.mvp.view.ILoginView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by zhanghai on 2018/7/4.
 * function：登录
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView{
    private final static String TAG = "LoginActivity";
    @ViewById(R.id.et_username)
    EditText mEditUserName;
    @ViewById(R.id.et_password)
    EditText mEditUserPassword;

    @Override
    public void createPresenter() {
        mPresenter = new LoginPresenter(new LoginModelImpl(),this);
    }


    @Click({R.id.btn_login})
    public void click(View view){
        switch (view.getId()){
            case R.id.btn_login:
                mPresenter.login(getUserNameEditText(),getPasswordEditText());
                break;
        }
    }

    @Override
    public void setUserNameEditText(String userName) {
        mEditUserName.setText(userName);
    }

    @Override
    public String getUserNameEditText() {
        return mEditUserName.getText().toString().trim();
    }

    @Override
    public void setPasswordEditText(String password) {
        mEditUserPassword.setText(password);
    }

    @Override
    public String getPasswordEditText() {
        return mEditUserPassword.getText().toString().trim();
    }

    @Override
    public void loginResult(boolean result, String data) {
        if(result){//success
            System.out.println("loginResult --->>> "+data);
            jump2main(data);
            finish();
        }else {
            //failed

        }
    }

    private void jump2main(String data){
        Intent intent = new Intent(this,MainActivity2_.class);
        intent.putExtra("loginData",data);
        startActivity(intent);
    }
}

package com.sxw.unittestdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_login)
public class MainActivity extends Activity {

    @ViewById(R.id.et_username)
    EditText mEditUserName;
    @ViewById(R.id.et_password)
    EditText mEditUserPassword;
    @ViewById(R.id.btn_login)
    Button btnLogin;

    @AfterViews
    void initView(){
        mEditUserName.setText("username");
        mEditUserPassword.setText("111111");
    }

    @Click({R.id.btn_login})
    public void click(View view){
        switch (view.getId()){
            case R.id.btn_login:
                showToast("登录成功");
                //登录跳转
                jump2Login();
                //显示对话框
                showConfirmDialog();
                break;
        }
    }

    /**
     * 页面跳转
     */
    private void jump2Login(){
        startActivity(new Intent(this,MainActivity_.class));
    }

    /**
     * 显示Toast消息
     * @param msg
     */
    private void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示对话框
     */
    private void showConfirmDialog(){
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("title")
                .setMessage("Robolectric验证对话框！")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.println("点击了Cancel");
                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.println("点击了Ok");
                    }
                })
                .create();
        dialog.show();
    }

    private String lifecycle;
    public String getLifecycleState(){
        return lifecycle;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycle = "onCreate";
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        lifecycle = "onRestart";
    }

    @Override
    protected void onStart() {
        super.onStart();
        lifecycle = "onStart";
    }

    @Override
    protected void onResume() {
        super.onResume();
        lifecycle = "onResume";
    }

    @Override
    protected void onPause() {
        super.onPause();
        lifecycle = "onPause";
    }

    @Override
    protected void onStop() {
        super.onStop();
        lifecycle = "onStop";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lifecycle = "onDestroy";
    }
}

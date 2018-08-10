package com.sxw.unittestdemo.mvp.ui;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.sxw.unittestdemo.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

/**
 * Created by zhanghai on 2018/7/4.
 * function：
 */
@EActivity(R.layout.activity_main)
public class MainActivity2 extends AppCompatActivity{

    @ViewById(R.id.tv_result_data)
    TextView tv_result_data;

    @Extra("loginData")
    String loginData;


    @AfterViews
    void initData(){
        tv_result_data.setText(loginData);
    }
}

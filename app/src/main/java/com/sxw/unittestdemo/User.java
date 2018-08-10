package com.sxw.unittestdemo;

/**
 * Created by zhanghai on 2018/7/2.
 * function：
 */

public class User {
    private int userId;
    private String userName;
    private int age;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String eat(String food){

        return food;
    }

    private String likeFruit(){
        return "苹果";
    }

    public String getUserLike(){
        return "喜欢吃" + likeFruit();
    }
}

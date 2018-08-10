package com.sxw.unittestdemo.mvp.bean;

import com.sxw.unittestdemo.App;

/**
 * Created by zhanghai on 2018/7/5.
 * function：
 */

public class RefreshToken {

    public class AppToken{
        private String refreshToken;
        private int refreshTokenExpiryDate;
        private String token;
        private int tokenExpiryDate;

        @Override
        public String toString() {
            return "{\"refreshToken\":\"" + refreshToken + "\",\"refreshTokenExpiryDate\":\"" + refreshTokenExpiryDate + "\",\"token\":\"" + token + "\",\"tokenExpiryDate\":\"" + tokenExpiryDate + "\"}";
        }
    }
    /**
     * alg : SALT_MD5
     * code : 200
     * data : {"tokenExpiryDate":12000,"refreshTokenExpiryDate":60000,"token":"eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJzdWIiOiJ7XCJhY2NvdW50SWRcIjpcImIzNmFkMGNkLWYzYjgtNGRlZi1iY2NmLTk3OWNlMjMzMTNjNlwiLFwiYWNjb3VudFR5cGVcIjoxLFwiYXBwXCI6XCJYV0tXXCIsXCJjbGllbnRcIjpcIlRFQUNIRVJcIixcInBsYXRmb3JtXCI6XCJBTkRST0lEXCIsXCJ1c2VySWRcIjpcImE3Mzk0ZDhmLTgzYjUtNGY1Yi05NzRmLWRiYjg0NmZjNGJiN1wifSIsImV4cCI6MTUzMDg1NTQwOCwiaXNzIjoic3hqeSJ9.ePIsielKwH2CzqyFO5EeG2cs010_-y_9PUsB3mj9w2A","refreshToken":"eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJzdWIiOiJleUpoWTJOdmRXNTBTV1FpT2lKaU16WmhaREJqWkMxbU0ySTRMVFJrWldZdFltTmpaaTA1TnpsalpUSXpNekV6WXpZaUxDSmhZMk52ZFc1MFZIbHdaU0k2TVN3aVlYQndJam9pV0ZkTFZ5SXNJbU5zYVdWdWRDSTZJbFJGUVVOSVJWSWlMQ0p3YkdGMFptOXliU0k2SWtGT1JGSlBTVVFpTENKMWMyVnlTV1FpT2lKaE56TTVOR1E0WmkwNE0ySTFMVFJtTldJdE9UYzBaaTFrWW1JNE5EWm1ZelJpWWpjaWZRPT0iLCJleHAiOjE1MzA5MDM0MDgsImlzcyI6InN4ankifQ._aEzlB_ISq6AiQel73a9aIQk5nUnmChpUWu8sKqTGkY"}
     * errorData :
     * md5 :
     * message : OK
     * success : true
     */

    private String alg;
    private int code;
    private AppToken data;
    private String errorData;
    private String md5;
    private String message;
    private boolean success;

    public String getAlg() {
        return alg;
    }

    public void setAlg(String alg) {
        this.alg = alg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public AppToken getData() {
        return data;
    }

    public void setData(AppToken data) {
        this.data = data;
    }

    public String getErrorData() {
        return errorData;
    }

    public void setErrorData(String errorData) {
        this.errorData = errorData;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

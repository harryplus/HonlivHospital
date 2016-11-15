package com.honliv.honlivhospital;

/**
 * Created by Rodin on 2016/11/10.
 */
public interface ConstantValue {
    /**
     * 图片地址前缀
     */
    String HOST_URL = "http://10.1.33.61:8090/"; //本app官方网址
    /**
     * 接口地址
     */
    String URL = HOST_URL + "api/user/posttest";
//    String URL = HOST_URL + "api/user/verifycode";

    String SecretKey="honlivhp";
}
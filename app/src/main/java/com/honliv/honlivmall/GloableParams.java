package com.honliv.honlivmall;

import android.app.Activity;
import android.content.Context;

import com.honliv.honlivmall.bean.Category;
import com.honliv.honlivmall.bean.OrderInfo;
import com.tencent.mm.sdk.openapi.IWXAPI;

import java.util.ArrayList;

public final class GloableParams {
    /**
     * 代理ip
     */
    public static String PROXY_IP = "";
    /**
     * 代理端口
     */
    public static int PROXY_PORT = 0;

    /**
     * 屏幕宽度
     */
    public static int WINDOW_WIDTH = 0;


    public static int WINDOW_HEIGHT = 0;

    /**
     * 用户ID
     */
    public static int USERID = -1;

    /**
     * 用户余额
     */
    public static Float MONEY = 0f;

    /**
     * 从哪个界面去登陆界面,好跳转回来
     */
    public static Class toLoginActivity;

    /**
     * 要关闭的界面。
     */
    public static Activity toCloseActivity;

    public static Context CONTEXT;

    public static int RequestID;

    public static boolean SUBMITOK = false;
    /**
     * 设为默认地址
     */
    public static boolean SETDEFAULTADDRESS = false;

    /**
     * 热门搜索关键词
     */
    public static String[] hotKeys;

    public static boolean hasHotKey = false; // true 为已经联网获取过关键词

    public static boolean hasCategory = false; // true 为已经联网获取过分类

    public static ArrayList<Category> categoryInfos;// true 联网分类信息

    public static int PRODUCTID2 = 0;
    public static IWXAPI api;
    public static OrderInfo CurrentOrder = null;//存储当前支付订单
}

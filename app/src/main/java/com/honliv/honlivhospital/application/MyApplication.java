package com.honliv.honlivhospital.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.honliv.honlivhospital.ConstantValue;
import com.honliv.honlivhospital.api.FouthApi;
import com.honliv.honlivhospital.utils.RxManager;

import java.util.HashSet;
import java.util.Set;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rodin on 2016/10/28.
 */
public class MyApplication extends Application {
    private static final String TAG = "MyApplication";
    public static RxManager mRxManager = new RxManager();
    public static Retrofit retrofit = null;
    public static FouthApi fouthApi = null;
    private static MyApplication instance;

    private Set<Activity> allActivities;

    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        getScreenSize();
        initJar();

    }

    /**
     * 第三方库初始化
     */
    private void initJar() {
        Fresco.initialize(this);
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(ConstantValue.HOST_URL).build();
        fouthApi = retrofit.create(FouthApi.class);
    }

    public static synchronized MyApplication getInstance() {
        return instance;
    }

    /**
     * 初始化屏幕宽高
     */
    public void getScreenSize() {
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if (SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }

    /**
     * 添加activity
     */
    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    /**
     * 移除activity
     */
    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    /**
     * 退出app
     */
    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    public Context getAppContext() {
        return this;
    }

    public String setBaseUrl() {
        return ConstantValue.HOST_URL;
    }
}

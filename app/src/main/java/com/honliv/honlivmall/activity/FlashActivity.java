package com.honliv.honlivmall.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.Toast;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.honliv.honlivmall.ConstantValue;
import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.bean.DefaultParas;
import com.honliv.honlivmall.bean.UserInfo;
import com.honliv.honlivmall.contract.ActivityContract;
import com.honliv.honlivmall.helper.RxPermissions;
import com.honliv.honlivmall.model.activity.FlashModel;
import com.honliv.honlivmall.presenter.activity.FlashPresenter;
import com.honliv.honlivmall.util.BeanFactory;
import com.honliv.honlivmall.util.LogUtil;
import com.honliv.honlivmall.util.RxUtil;
import com.honliv.honlivmall.util.Utils;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Created by rodin on 16/10/28.
 */

public class FlashActivity extends CoreBaseActivity<FlashPresenter,FlashModel>implements ActivityContract.FlashView {
    @Override
    public int getLayoutId() {
        return R.layout.activity_flash;
    }

    @Override
    public boolean isOpen() {
        return true;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
//        deleteUpdata();//删除升级的apk
//        mPresenter.getServiceDafault(); //获取默认参数
//
//        installShortCut();// 创建快捷图标
//
//        registerWeiXin();//注册微信
//        getDbUserInfo();
        startActivity(MainActivity.class);
//        Observable.timer(2000, TimeUnit.MILLISECONDS)
//                .compose(RxPermissions.getInstance(this).ensure(Manifest.permission.READ_PHONE_STATE))
//                .compose(RxUtil.rxSchedulerHelper())
//                .subscribe(granted -> {
//                    if (granted) {
//                        startActivity(MainActivity.class);
//                        finish();
//                    }
//                    startActivity(MainActivity.class);
//                });
    }

    private void registerWeiXin() {
        GloableParams.api = WXAPIFactory.createWXAPI(this, ConstantValue.APP_ID, false);
        GloableParams.api.registerApp(ConstantValue.APP_ID);
    }

    private void deleteUpdata() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file = new File(Environment.getExternalStorageDirectory() + "/dyupdate");
            if (file.exists()) {
                deldir(file);
            }
        }
    }

    public static void deldir(File f) {
        File[] files = f.listFiles();

        for (int x = 0; x < files.length; x++) {
            if (files[x].isDirectory()) {
                deldir(files[x]);
            } else {//不带else，会比较低效，因为是文件夹时已经删过一次了
                files[x].delete();
            }
        }
        f.delete();
    }

    private void getDbUserInfo() {
        DbUtils db = DbUtils.create(this);
        UserInfo userDbInfo = null;
        try {
            userDbInfo = db.findFirst(UserInfo.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        if (userDbInfo != null) {
            GloableParams.USERID = userDbInfo.getUserId();
        }
    }

    private void installShortCut() {// 1.创建桌面图标,2.在清单文件中配置Activity的intent-filter，名字和发出的动作相同

        int which = sp.getInt("startWhich", 0);
        if (which < 1) {//判断有没有创建快捷方式

            if (Build.VERSION.SDK_INT >= 9) {//2.3以上的系统

            } else {//2.3以下的系统,再创建一个快捷方式
                Intent intent = new Intent();
                intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");

                intent.putExtra(Intent.EXTRA_SHORTCUT_ICON, BitmapFactory
                        .decodeResource(getResources(),
                                R.drawable.android_ms_icon));// shut1  android_jd_notification

                // 获取当前应用名称
                String title = null;
                try {
                    title = getResources().getString(R.string.app_name);
                } catch (Exception e) {
                    return;
                }
                intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);

                Intent homeIntent = new Intent();
                intent.putExtra("duplicate", false);// 防止重复创建

                homeIntent.setAction("com.maticsoft.shop.activity_payresult.splash");
                homeIntent.addCategory(Intent.CATEGORY_DEFAULT);

                intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, homeIntent);
                sendBroadcast(intent);
            }
        }

        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("startWhich", 1 + which);//统计打开的次数
        //editor.putInt("startWhich", 0);//统计打开的次数
        editor.commit();
    }

    private String getAppVersions() {// 当前应用的版本号
        PackageManager manager = getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(getPackageName(), 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            // can't run
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();
        loadHomeActivity();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {//处理返回键
        if (keyCode == KeyEvent.KEYCODE_BACK) {//返回值被点击了
            return false;//确保按下返回键不会退出应用
        }
        return super.onKeyDown(keyCode, event);
    }

    private void loadHomeActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void updateView(DefaultParas result) {
        if (result != null) {
            DefaultParas defaultParas = (DefaultParas) result;
            if (defaultParas != null) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("phone", defaultParas.getPhone());
                editor.commit();

                LogUtil.info("defaultParas==" + defaultParas + "");
            }
        }
    }
}

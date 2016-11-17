package com.honliv.honlivmall.activity;

import android.Manifest;
import android.os.Bundle;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.helper.RxPermissions;
import com.honliv.honlivmall.util.RxUtil;

import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Created by rodin on 16/10/28.
 */

public class FlashActivity extends CoreBaseActivity {
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
        startActivity(MainActivity.class);
//        Observable.timer(2000, TimeUnit.MILLISECONDS)
//                .compose(RxPermissions.getInstance(this).ensure(Manifest.permission.READ_PHONE_STATE))
//                .compose(RxUtil.rxSchedulerHelper())
//                .subscribe(granted -> {
////                    if (granted) {
////                        startActivity(MainActivity.class);
////                        finish();
////                    }
//                    startActivity(MainActivity.class);
//                });
    }
}

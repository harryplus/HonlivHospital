package com.honliv.honlivmall.fragment.fifth.child;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.honliv.honlivmall.ConstantValue;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.activity.AboutActivity;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.bean.UserInfo;
import com.honliv.honlivmall.bean.VersionInfo;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.model.fifth.child.FifthAboutModel;
import com.honliv.honlivmall.model.fifth.child.FifthSettingModel;
import com.honliv.honlivmall.presenter.fifth.child.FifthAboutPresenter;
import com.honliv.honlivmall.presenter.fifth.child.FifthSettingPresenter;
import com.honliv.honlivmall.util.LogUtil;
import com.honliv.honlivmall.util.PromptManager;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/11/16.
 */
public class FifthAboutFragment extends BaseFragment<FifthAboutPresenter, FifthAboutModel> implements FifthContract.FifthAboutView, View.OnClickListener {
@BindView(R.id.head_back_text)
TextView head_back_text;

    public static FifthAboutFragment newInstance() {
        Bundle args = new Bundle();

        FifthAboutFragment fragment = new FifthAboutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fifth_about;
    }

    /**
     * 初始化控件
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        head_back_text.setOnClickListener(this);
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public boolean onBackPressedSupport() {
        pop();
        return true;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.head_back_text:
                pop();
                break;
        }
    }
}

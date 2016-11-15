package com.honliv.honlivhospital.fragment.fourth.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.JsonObject;
import com.honliv.honlivhospital.ConstantValue;
import com.honliv.honlivhospital.R;
import com.honliv.honlivhospital.application.MyApplication;
import com.honliv.honlivhospital.base.BaseFragment;
import com.honliv.honlivhospital.base.BaseLazyMainFragment;
import com.honliv.honlivhospital.domain.BaseResult;
import com.honliv.honlivhospital.domain.UserBean;
import com.honliv.honlivhospital.utils.Base64Utils;

import java.util.HashMap;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Rodin on 2016/10/26.
 */
public class FourthRegisterFragment extends BaseFragment implements BaseLazyMainFragment.OnBackToFirstListener, View.OnClickListener {
    private static final String TAG = "FourthRegisterFragment";
    private Toolbar mToolbar;
    private Button register;
    private EditText et_cellphone;
    private EditText et_pwd;
    private EditText et_note;
    private Button bt_send_note;

    public static FourthRegisterFragment newInstance() {

        Bundle args = new Bundle();

        FourthRegisterFragment fragment = new FourthRegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fourth_register, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        register = (Button) view.findViewById(R.id.register);
        bt_send_note = (Button) view.findViewById(R.id.bt_send_note);
        et_cellphone = (EditText) view.findViewById(R.id.et_cellphone);
        et_pwd = (EditText) view.findViewById(R.id.et_pwd);
        et_note = (EditText) view.findViewById(R.id.et_note);

        bt_send_note.setOnClickListener(this);
        register.setOnClickListener(this);

        mToolbar.setTitle(getString(R.string.title_register));

        initToolbarNav(mToolbar);
    }

    @Override
    public void onBackToFirstFragment() {
        _mActivity.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:

                break;
            case R.id.bt_send_note:
                String phoneNum = null;
                try {
                    phoneNum = Base64Utils.EncryptAsDoNet(et_cellphone.getText().toString(), ConstantValue.SecretKey);
                    JsonObject obj = new JsonObject();
                    obj.addProperty("phoneNumber", phoneNum);
                    String objStr = obj.toString();
                    Log.i(TAG, objStr);
                    HashMap<String, String> map = new HashMap<>();
                    map.put("phoneNumber", phoneNum);
//                    MyApplication.mRxManager.add(MyApplication.fouthApi.getVerfyVode(map).subscribe(new Subscriber<BaseResult<List<UserBean>>>() {
//                        @Override
//                        public void onCompleted() {
//
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                            e.printStackTrace();
//                        }
//
//                        @Override
//                        public void onNext(BaseResult<List<UserBean>> bean) {
//                            Log.i(TAG, bean.toString());
//                        }
//                    }));
                    MyApplication.fouthApi.getVerfyVode(map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<BaseResult<List<UserBean>>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onNext(BaseResult<List<UserBean>> bean) {
                            Log.i(TAG, bean.toString());
                        }
                    });
                } catch (Exception e) {
                    Log.i(TAG, "catch--" + e.toString());
                }
                break;
        }
    }
}

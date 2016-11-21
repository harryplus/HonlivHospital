package com.honliv.honlivmall.fragment.fifth.child;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.activity.BaseActivity;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.bean.UserInfo;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.fragment.second.child.SecondMainFragment;
import com.honliv.honlivmall.model.fifth.child.FifthHomeModel;
import com.honliv.honlivmall.presenter.fifth.child.FifthHomePresenter;
import com.honliv.honlivmall.util.BeanFactory;
import com.honliv.honlivmall.util.PromptManager;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/11/16.
 */
public class FifthHomeFragment extends BaseFragment<FifthHomePresenter, FifthHomeModel> implements FifthContract.FifthHomeView, View.OnClickListener {
    @BindView(R.id.my_info_lin)
    LinearLayout my_info;
    @BindView(R.id.my_change_password)
    LinearLayout mychangepassword;
    @BindView(R.id.my_address_manage)
    LinearLayout myaddressmanage;
    @BindView(R.id.my_order_lin)
    LinearLayout myorder;
    @BindView(R.id.my_favorites_lin)
    LinearLayout my_favorites;
    @BindView(R.id.my_Inbox_lin)
    LinearLayout my_Inbox;
    @BindView(R.id.my_coupon_lin)
    LinearLayout my_coupon_lin;
    @BindView(R.id.loginOut_text)
    TextView loginOut;
    @BindView(R.id.head_image_setting)
    TextView loginsetting;
    @BindView(R.id.username_tv)
    TextView usernameTV;
    @BindView(R.id.webviewTitle)
    TextView titleTV;
    @BindView(R.id.head_back_text)
    TextView headBackTV;
    @BindView(R.id.vip_tv)
    TextView vipTV;
    @BindView(R.id.bonus_tv)
    TextView bonusTV;
    @BindView(R.id.userHeadImage)
    ImageView userHeadImage;
    @BindView(R.id.nearby_market)
    LinearLayout nearby_market;
    @BindView(R.id.my_hxt)
    LinearLayout my_hxt;

    UserInfo userInfo = new UserInfo();
    UserInfo dbUserInfo;
    int userId;

    public static FifthHomeFragment newInstance() {
        Bundle args = new Bundle();

        FifthHomeFragment fragment = new FifthHomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fifth_center;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        my_info.setOnClickListener(this);
        my_hxt.setOnClickListener(this);
        mychangepassword.setOnClickListener(this);
        myaddressmanage.setOnClickListener(this);
        myorder.setOnClickListener(this);
        my_favorites.setOnClickListener(this);
        my_Inbox.setOnClickListener(this);
        my_coupon_lin.setOnClickListener(this);
        loginOut.setOnClickListener(this);
        loginsetting.setOnClickListener(this);
        nearby_market.setOnClickListener(this);
        getUserInfo();
    }

    @Override
    public void showError(String msg) {

    }


    void getUserInfo() {
//        dbUserInfo = (UserInfo) this.getIntent().getSerializableExtra("userInfo");
        new Thread() {
            @Override
            public void run() {
                if (dbUserInfo == null) {
                    DbUtils db = DbUtils.create(getContext());
                    try {
                        dbUserInfo = db.findFirst(UserInfo.class);
                    } catch (DbException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    if (dbUserInfo == null) {
                        PromptManager.showToast(getContext(), getString(R.string.reLogin));
                        return;
                    } else {
                        mPresenter.getServiceUserInfo(dbUserInfo.getUserId());
                    }
                }
            }
        }.start();
    }

//     void getServiceUserInfo(int userId) {
//        new BaseActivity.MyHttpTask<Integer>() {
//            protected void onPreExecute() {
//                PromptManager.showCommonProgressDialog(MyCenterActivity.this);
//                super.onPreExecute();
//            }
//
//            @Override
//            protected Object doInBackground(Integer... params) {
//                UserInfoEngine engine = BeanFactory.getImpl(UserInfoEngine.class);
//                return engine.getServiceUserInfo(params[0]);
//            }
//
//            protected void onPostExecute(Object result) {
//                super.onPostExecute(result);
//                PromptManager.closeProgressDialog();
//                if (result != null) {
//                    userInfo = (UserInfo) result;
//                    DbUtils db = DbUtils.create(getApplicationContext());
//                    try {
//                        db.dropTable(UserInfo.class);
//                        userInfo.setPassword(dbUserInfo.getPassword());
//                        db.save(userInfo);
//                    } catch (DbException e) {
//                        e.printStackTrace();
//                    } finally {
//                        //有返回东西 ,解析出来数据，设置给屏幕
//                        initData();
//                    }
//                } else {
//                    PromptManager.showToast(getApplicationContext(), "服务器忙，请稍后重试！！！");
//                }
//            }
//        }.executeProxy(userId);
//    }

    public void initData() {
        // TODO Auto-generated method stub

    }


    @Override
    public void onClick(View v) {
//        if (intent == null) {
//            intent = new Intent();
//        }
//        switch (v.getId()) {
//            case R.id.my_info_lin:
//                //个人资料
//                intent.setClass(getApplicationContext(), EditPersonInfo.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);
//                break;
//            case R.id.my_hxt:
//                //宏信通
//                intent.setClass(getApplicationContext(), MyHxtActivity.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);
//
//                break;
//            case R.id.my_change_password://修改密码
//
//                GloableParams.toCloseActivity = MyCenterActivity.this;
//                intent.setClass(getApplicationContext(), EditPasswordActivity.class);
//                startActivityForResult(intent, 100);
//                overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);
//                break;
//            case R.id.my_address_manage://地址管理
//                intent.setClass(getApplicationContext(), AddressManageActivity.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);
//                break;
//            case R.id.my_order_lin://我的订单
//                intent.setClass(getApplicationContext(), MyOrderActivity.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);
//                break;
//
//            case R.id.my_favorites_lin://我的收藏
//
//                intent.setClass(getApplicationContext(), FavoriteActivity.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);
//                break;
//            case R.id.my_Inbox_lin://收件箱
//
//                break;
//            case R.id.my_coupon_lin://优惠卷
//                intent.setClass(getApplicationContext(), MyCouponActivity.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);
//
//                break;
//            case R.id.loginOut_text://注销登录
//                getServiceLogOut("-1");
//
//                break;
//            case R.id.head_image_setting://设置
//                intent.setClass(getApplicationContext(), SettingActivity.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);
//                break;
//
//            case R.id.nearby_market:
//
//                break;
//        }
    }

    @Override
    public void updateView(UserInfo result) {
        if (result != null) {
            userInfo = result;
            usernameTV.setText(userInfo.getUserName());
            vipTV.setText(userInfo.getNickName());
            bonusTV.setText(userInfo.getPoint() + " 分");
        }
    }

//     void getServiceLogOut(String userId) {
//        new BaseActivity.MyHttpTask<String>() {
//            protected void onPreExecute() {
//                PromptManager.showCommonProgressDialog(MyCenterActivity.this);
//                super.onPreExecute();
//            }
//
//            @Override
//            protected Object doInBackground(String... params) {
//                UserInfoEngine engine = BeanFactory.getImpl(UserInfoEngine.class);
//                return engine.getServiceLogOut();
//            }
//
//            protected void onPostExecute(Object result) {
//                super.onPostExecute(result);
//                PromptManager.closeProgressDialog();
//                if (result != null && (Boolean) result) {
//
//                    DbUtils db = DbUtils.create(getApplicationContext());
//                    try {
//                        db.dropTable(UserInfo.class);
//                    } catch (DbException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//                    GloableParams.USERID = -1;
//
//                    PromptManager.showToast(getApplicationContext(), "注销成功!");
//                    animNextActivity(LoginActivity.class);
//                    finish();
//                } else {
//                    PromptManager.showToast(getApplicationContext(), "服务器忙，请稍后重试！！！");
//                }
//            }
//        }.executeProxy(userId);
//    }
}

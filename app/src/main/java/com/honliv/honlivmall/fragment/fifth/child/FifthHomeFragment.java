package com.honliv.honlivmall.fragment.fifth.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.bean.UserInfo;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.fragment.global.GlobalLoginFragment;
import com.honliv.honlivmall.model.fifth.child.FifthHomeModel;
import com.honliv.honlivmall.presenter.fifth.child.FifthHomePresenter;
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
        return R.layout.fragment_fifth_home;
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

    }

    @Override
    public void showError(String msg) {

    }


    public void initData() {
        if (dbUserInfo == null) {
            DbUtils db = DbUtils.create(getContext());
            try {
                dbUserInfo = db.findFirst(UserInfo.class);
            } catch (DbException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (dbUserInfo == null) {
//                showToast(getString(R.string.reLogin));
                start(GlobalLoginFragment.newInstance());
                return;
            } else {
                mPresenter.getServiceUserInfo(dbUserInfo.getUserId());
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_info_lin:
                //个人资料
                start(FifthEditPersonInfoFragment.newInstance());
                break;
            case R.id.my_hxt:
                //宏信通
//                start(FifthEditPersonInfoFragment.newInstance());
                start(FifthMyHxtFragment.newInstance());
                break;
            case R.id.my_change_password://修改密码
                start(FifthEditPwdFragment.newInstance());
                break;
            case R.id.my_address_manage://地址管理
                start(FifthAddressManageFragment.newInstance());
                break;
            case R.id.my_order_lin://我的订单
                start(FifthMyOrderFragment.newInstance());
                break;

            case R.id.my_favorites_lin://我的收藏
                start(FifthFavoriteFragment.newInstance());
                break;
            case R.id.my_Inbox_lin://收件箱

                break;
            case R.id.my_coupon_lin://优惠卷
                start(FifthMyCouponFragment.newInstance());
                break;
            case R.id.loginOut_text://注销登录
                mPresenter.getServiceLogOut("-1");
                break;
            case R.id.head_image_setting://设置
                start(FifthSettingFragment.newInstance());
                break;

            case R.id.nearby_market:

                break;
        }
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

    @Override
    public void updateLogOutView(Boolean result) {
        if (result != null && result) {
            DbUtils db = DbUtils.create(getContext());
            try {
                db.dropTable(UserInfo.class);
            } catch (DbException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            GloableParams.USERID = -1;
            showToast("注销成功!");
            start(GlobalLoginFragment.newInstance());
        } else {
            showToast("服务器忙，请稍后重试！！！");
        }
    }
}

package com.honliv.honlivmall.fragment.fifth.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.bean.UserInfo;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.model.fifth.child.FifthEditPersonInfoModel;
import com.honliv.honlivmall.presenter.fifth.child.FifthEditPersonInfoPresenter;
import com.honliv.honlivmall.util.LogUtil;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/11/16.
 */
public class FifthEditPersonInfoFragment extends BaseFragment<FifthEditPersonInfoPresenter, FifthEditPersonInfoModel> implements FifthContract.FifthEditPersonInfoView, View.OnClickListener {
    @BindView(R.id.radioGroup)
    RadioGroup mRadioGroup;
    int userId = -1;
    int userSex = 1;//1代表男，0代表女
    UserInfo userInfo;
    @BindView(R.id.edit_nick_name)
    EditText nicknameET;
    @BindView(R.id.edit_Email_ET)
    EditText editEmailET;
    @BindView(R.id.Edit_mobile_ET)
    EditText editMobileET;//手机号
    @BindView(R.id.edit_TEL_ET)
    EditText editTELET;//固定电话
    @BindView(R.id.editBrithdayET)
    TextView editBrithdayET;//生日
    @BindView(R.id.add_address_province)
    TextView addressProvinceET;//区域选择
    @BindView(R.id.head_goback)
    TextView headGoback;//区域选择
    @BindView(R.id.updateInfo)
    TextView updateInfo;
    @BindView(R.id.register_ok)
    TextView register_ok;

    String nicknameStr;
    String eMailStr;
    String mobileStr;
    String telStr;
    String brithdayStr;
    String provinceStr;
    static final String[] proviceitems = {"东城区", "西城区", "崇文区", "宣武区", "朝阳区", "丰台区", "石景山区", "海淀区", "门头沟区", "房山区", "通州区", "顺义区", "昌平区", "大兴区", "怀柔区", "平谷区", "密云县", "延庆县", "其他区"};
    static final int[] proviceIds = {644, 645, 646, 647, 648, 649, 650, 651, 652, 653, 654, 655, 656, 657, 658, 659, 660, 661, 662};
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    UserInfo info;

    public static FifthEditPersonInfoFragment newInstance() {
        Bundle args = new Bundle();

        FifthEditPersonInfoFragment fragment = new FifthEditPersonInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fifth_edit_personinfo;
    }

    /**
     * 初始化控件
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        mRadioGroup.setOnCheckedChangeListener(new RadioButtonOnCheckedChangeListenerImpl());
        headGoback.setOnClickListener(this);
        updateInfo.setOnClickListener(this);
        register_ok.setOnClickListener(this);
    }

    @Override
    public void showError(String msg) {

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.head_goback:
                pop();
                break;
            case R.id.updateInfo:
            case R.id.register_ok:
                if (userId < 0) {
                    return;
                }
                nicknameStr = nicknameET.getText().toString().trim();
                eMailStr = editEmailET.getText().toString().trim();
                mobileStr = editMobileET.getText().toString().trim();
                telStr = editTELET.getText().toString().trim();
                brithdayStr = editBrithdayET.getText().toString().trim();
                provinceStr = addressProvinceET.getText().toString().trim();

                if (!"".equals(eMailStr)) {
                    String reg = "[a-zA-Z0-9_.]{2,20}@[a-zA-Z0-9]+(\\.[a-zA-Z]+){1,3}";//比较精确的匹配
                    if (!eMailStr.matches(reg)) {
                        showToast("请输入正确的邮箱");
                        return;
                    }
                }

                if (!"".equals(mobileStr)) {
                    String reg = "^1([38][0-9]|4[57]|5[^4]|7[0678]|)\\d{8}$";      //      1[0-9]{10}       /^1([38][0-9]|4[57]|5[^4])\d{8}$/
                    if (!mobileStr.matches(reg)) {
                        showToast("请输入正确的手机号");
                        return;
                    }
                }
                int selRegionId = sp.getInt("regionId", -1);

                info = new UserInfo();
                info.setUserId(GloableParams.USERID);
                if (!"".equals(nicknameStr))
                    info.setNickName(nicknameStr);
                if (!"".equals(eMailStr))
                    info.setEmail(eMailStr);
                info.setSex(userSex + "");
                if (!"".equals(mobileStr))
                    info.setPhone(mobileStr);
                if (!"".equals(telStr))
                    info.setTel(telStr);
                if (!"".equals(brithdayStr))
                    info.setBirthday(brithdayStr);
                if (selRegionId > 0)
                    info.setRegionId(selRegionId);

                mPresenter.updateUserInfo(info);
                break;
        }
    }

    public void initData() {
        userId = GloableParams.USERID;
        if (userId < 0) {
            showToast("请您先登录~");
            return;
        }
        mPresenter.getServicePersonal(getContext());
    }

    @Override
    public void updateServicePersonal(UserInfo result) {
        if (result != null) {
            //有返回东西 ,解析出来数据，设置给屏幕
            userInfo = result;
            if (userInfo != null) {
                //获取成功
                if (userInfo.getNickName() != null)
                    nicknameET.setText(userInfo.getNickName() + "");

                if (userInfo.getEmail() != null)
                    editEmailET.setText(userInfo.getEmail() + "");

                if (userInfo.getPhone() != null)
                    editMobileET.setText(userInfo.getPhone() + "");

                if (userInfo.getTel() != null)
                    editTELET.setText(userInfo.getTel() + "");

                if (userInfo.getBirthday() != null)
                    editBrithdayET.setText(userInfo.getBirthday() + "");

                if (userInfo.getProvince() != null) {
                    int index = 0;
                    for (int i = 0; i < proviceIds.length; i++) {
                        if (userInfo.getProvince().equals(proviceIds[i] + "")) {
                            index = i;
                        }
                    }
                    addressProvinceET.setText(proviceitems[index]);
                }
                if ("0".equals(userInfo.getSex())) {
                    mRadioGroup.check(R.id.woman);//如果是女的，就调用这句代码
                }
            } else {
                //获取失败
                showToast("服务器忙，请稍后重试！！！");
            }
        } else {
            showToast("服务器忙，请稍后重试！！！");
        }
    }

    @Override
    public void updateUserInfoView(Boolean result) {
        if (result != null) {
            //有返回东西 ,解析出来数据，设置给屏幕
            boolean isSuccess = (Boolean) result;
            if (isSuccess) {
                showToast("修改成功！！！");
                updateDBuserIfno();
                pop();
            } else {
                //获取失败
                showToast("修改失败");
            }
        } else {
            showToast("服务器忙，请稍后重试！！！");
        }
    }

    // 监听单选的变化
    class RadioButtonOnCheckedChangeListenerImpl implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            RadioButton rb = (RadioButton) getActivity().findViewById(group.getCheckedRadioButtonId());
            String currentSelected = rb.getText().toString();
            System.out.println("现在选中是:" + currentSelected + ",checkedId=" + checkedId);
            if ("女".equals(currentSelected)) {
                userSex = 0;
            } else {
                userSex = 1;
            }
        }
    }


//     void updateUserInfo(final UserInfo info) {
//        new BaseActivity.MyHttpTask<Integer>() {
//            @Override
//            protected void onPreExecute() {
//                PromptManager.showCommonProgressDialog(EditPersonInfo.this);
//                super.onPreExecute();
//            }
//
//            @Override
//            protected Object doInBackground(Integer... params) {
//                UserInfoEngine engine = BeanFactory.getImpl(UserInfoEngine.class);
//                return engine.updatePerson(info);
//            }
//
//            @Override
//            protected void onPostExecute(Object result) {
//                // TODO Auto-generated method stub
//                super.onPostExecute(result);
//                PromptManager.closeProgressDialog();
//
//            }
//        }.executeProxy(userId);
//    }

    @Override
    public boolean onBackPressedSupport() {
        pop();
        return true;
    }

    protected void updateDBuserIfno() {
        DbUtils db = DbUtils.create(getContext());
        try {
            db.update(info);
        } catch (DbException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

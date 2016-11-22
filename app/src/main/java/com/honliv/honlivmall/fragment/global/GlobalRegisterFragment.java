package com.honliv.honlivmall.fragment.global;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.bean.UserInfo;
import com.honliv.honlivmall.contract.GlobalContract;
import com.honliv.honlivmall.model.global.GlobalRegisterModel;
import com.honliv.honlivmall.presenter.global.GlobalRegisterPresenter;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by YoKeyword on 16/2/9.
 */
public class GlobalRegisterFragment extends BaseFragment<GlobalRegisterPresenter, GlobalRegisterModel> implements GlobalContract.GlobalRegisterView, View.OnClickListener {

    @BindView(R.id.username_et)
    EditText username_et;
    @BindView(R.id.nickname_et)
    EditText nickname_et;
    @BindView(R.id.password_et)
    EditText password_et;
    @BindView(R.id.configpassword_et)
    EditText configpassword_et;
    @BindView(R.id.regist_checkbox)
    CheckBox regist_checkbox;
    @BindView(R.id.register_ok)
    TextView register_ok;
    @BindView(R.id.seeRegisterMent)
    TextView seeRegisterMent;
    @BindView(R.id.backTv)
    TextView backTv;


    public static GlobalRegisterFragment newInstance() {
        Bundle args = new Bundle();
        GlobalRegisterFragment fragment = new GlobalRegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_global_register;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        register_ok.setOnClickListener(this);
        seeRegisterMent.setOnClickListener(this);
        backTv.setOnClickListener(this);
    }

    @Override
    public void showError(String msg) {

    }


    /**
     * 点击注册按钮
     *
     * @param view
     */
    public void registerOK(View view) {
//		regist_checkbox.setChecked(true);

    }

//     void getServiceRegist(UserInfo userInfo){
//        new BaseActivity.MyHttpTask<UserInfo>() {
//            protected void onPreExecute() {
//                PromptManager.showCommonProgressDialog(RegisterActivity.this);
//                super.onPreExecute();
//            }
//            @Override
//            protected Object doInBackground(UserInfo... params) {
//                UserInfoEngine engine = BeanFactory.getImpl(UserInfoEngine.class);
//                return engine.getServiceRegistInfo(params[0]);
//            }
//            protected void onPostExecute(Object result) {
//                super.onPostExecute(result);
//                PromptManager.closeProgressDialog();
//
//            }
//        }.executeProxy(userInfo);
//    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_ok:
                if (regist_checkbox.isChecked()) {
                    String username = username_et.getText().toString().trim();
                    String nickname = nickname_et.getText().toString().trim();
                    String password = password_et.getText().toString().trim();
                    String configpassword = configpassword_et.getText().toString().trim();

                    if (StringUtils.isBlank(username)) {
                        showToast("用户名不能为空");
                        return;
                    }
                    String reg = "^1([38][0-9]|4[57]|5[^4]|7[0678]|)\\d{8}$";      //      1[0-9]{10}       /^1([38][0-9]|4[57]|5[^4])\d{8}$/
                    if (!username.matches(reg)) {
                        showToast("请输入正确的手机号");
                        return;
                    }
                    if (StringUtils.isBlank(nickname)) {
                        showToast("昵称不能为空");
                        return;
                    }
                    if (StringUtils.isBlank(password)) {
                        showToast("密码不能为空");
                        return;
                    }
                    if (password.length() < 6) {
                        showToast("密码6位以上");
                        return;
                    }
                    if (!password.equals(configpassword)) {
                        showToast("两次密码不一致");
                        return;
                    }
                    //到了这里，验证通过
                    UserInfo userInfo = new UserInfo();
                    userInfo.setUserName(username);
                    userInfo.setNickName(nickname);
                    userInfo.setPassword(password);
                    mPresenter.getServiceRegist(userInfo);
                } else {
                    showToast("请您同意注册协议后再注册!!!");
                }
                break;
            case R.id.seeRegisterMent:
//                animNextActivity(RegisterStatementActivity.class);

                break;
            case R.id.backTv:
                pop();
                break;
        }
    }

    @Override
    public void updateView(UserInfo result) {
        if (result != null) {
            UserInfo userInfo = (UserInfo) result;
            int userId = userInfo.getUserId();
            GloableParams.USERID = userId;
            DbUtils db = DbUtils.create(getContext());
            try {
                db.save(userInfo);
                ArrayList<Product> productlist = (ArrayList) db.findAll(Selector.from(Product.class).where("userId", "=", -100));

                if (productlist != null && productlist.size() > 0) {
                    for (int i = 0; i < productlist.size(); i++) {
                        Product dbproduct = null;
                        productlist.get(i).setUserId(GloableParams.USERID);

                        dbproduct = db.findFirst(Selector.from(Product.class).where("userId", "=", GloableParams.USERID).and("id", "=", productlist.get(i).getId()));
                        if (dbproduct != null) {
                            db.delete(dbproduct);
                            productlist.get(i).setNumber(dbproduct.getNumber() + productlist.get(i).getNumber());
                        }

                        db.update(productlist.get(i));
                    }
                }
            } catch (DbException e) {
                e.printStackTrace();
            }

            //成功的
//            intent = new Intent();
//					/*if(GloableParams.CARTTO){//从购物车来
//						 intent.setClass(getApplicationContext(), ShopCartActivity.class);
//					}else{*/
//            intent.setClass(getApplicationContext(), MyCenterActivity.class);
//            //intent.putExtra("userId",Integer.parseInt(result+""));
//            intent.putExtra("userId",userId);
////					}
//            startActivity(intent);
//            overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);//下个
//
//            finish();
            //animNextActivity(MyCenterActivity.class);
//					initData();
            //有返回东西 ,解析出来数据，设置给屏幕
//					LogUtil.info(((List)result).toString());
        } else {
            showToast("注册失败！！！");
        }
    }
    @Override
    public boolean onBackPressedSupport() {
        pop();
        return true;
    }
}

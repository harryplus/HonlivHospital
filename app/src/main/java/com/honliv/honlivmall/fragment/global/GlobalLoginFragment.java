package com.honliv.honlivmall.fragment.global;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.bean.UserInfo;
import com.honliv.honlivmall.contract.GlobalContract;
import com.honliv.honlivmall.model.global.GlobalLoginModel;
import com.honliv.honlivmall.presenter.global.GlobalLoginPresenter;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/10/29.
 */
public class GlobalLoginFragment extends BaseFragment<GlobalLoginPresenter, GlobalLoginModel> implements SwipeRefreshLayout.OnRefreshListener, GlobalContract.GlobalLoginView, View.OnClickListener {
    @BindView(R.id.backTv)
    TextView backTv;
    @BindView(R.id.username_et)
    EditText username_et;
    @BindView(R.id.password_et)
    EditText password_et;
    @BindView(R.id.login_register)
    TextView login_register;
    @BindView(R.id.login_text)
    TextView login_text;
    @BindView(R.id.forgin_password_TV)
    TextView forgin_password_TV;


    public static GlobalLoginFragment newInstance() {

        Bundle args = new Bundle();

        GlobalLoginFragment fragment = new GlobalLoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_global_login;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        backTv.setOnClickListener(this);
        login_register.setOnClickListener(this);
        login_text.setOnClickListener(this);
        forgin_password_TV.setOnClickListener(this);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void showError(String msg) {

    }

//    /**
//     * 和服务器连接登录
//     * @param userInfo
//     */
//     void getServiceLoginInfo(final UserInfo userInfo){
//        new BaseActivity.MyHttpTask<UserInfo>() {
//            protected void onPreExecute() {
//                PromptManager.showCommonProgressDialog(LoginActivity.this);
//                super.onPreExecute();
//            }
//            @Override
//            protected Object doInBackground(UserInfo... params) {
//                UserInfoEngine engine = BeanFactory.getImpl(UserInfoEngine.class);
//                return engine.getServiceLoginInfo(params[0]);
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
            case R.id.backTv:
                pop();
                break;
            case R.id.login_register:
                GlobalRegisterFragment registerFragment = GlobalRegisterFragment.newInstance();
                start(registerFragment);
                break;
            case R.id.login_text:
                String username = username_et.getText().toString().trim();
                String password = password_et.getText().toString().trim();

                if (StringUtils.isBlank(username)) {
                    showToast("登录名不能为空");
                    return;
                }
                if (username.length() < 2) {
                    showToast("登录名2位以上");
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
                ///////到达这里，说明数据配置通过
                UserInfo userInfo = new UserInfo();
                userInfo.setUserName(username);
                userInfo.setPassword(password);
                mPresenter.getServiceLoginInfo(userInfo);
                break;
            case R.id.forgin_password_TV:
//                intent = new Intent();
//                intent.setClass(getApplicationContext(), FindPasswordActivity.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);
                break;
        }
    }

    @Override
    public void updateView(UserInfo result) {
        if (result != null) {
            UserInfo userInfo2 = result;
//            userInfo2.setPassword(userInfo.getPassword());
            GloableParams.USERID = userInfo2.getUserId();
            DbUtils db = DbUtils.create(getContext());
            try {
                db.save(userInfo2);
                ArrayList<Product> productlist = (ArrayList) db.findAll(Selector.from(Product.class).where("userId", "=", -100));
                if (productlist != null && productlist.size() > 0) {
                    Product dbproduct = null;
                    for (int i = 0; i < productlist.size(); i++) {
                        productlist.get(i).setUserId(GloableParams.USERID);
                        //("openSkuStr","=",product.getOpenSkuStr())
                        dbproduct = db.findFirst(Selector.from(Product.class).where("userId", "=", GloableParams.USERID).and("openSkuStr", "=", productlist.get(i).getOpenSkuStr()));
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
//            intent = new Intent();
//
//            if(GloableParams.toLoginActivity!=null){//从哪来
//                intent.setClass(getApplicationContext(), GloableParams.toLoginActivity);
//            }else{
//                intent.putExtra("userInfo", userInfo2);
//                intent.setClass(getApplicationContext(), MyCenterActivity.class);
//            }
//            GloableParams.toLoginActivity=null;
//            startActivity(intent);
//            finish();

//					initData();
            //有返回东西 ,解析出来数据，设置给屏幕
//					LogUtil.info(((List)result).toString());
        }/*else{
                    PromptManager.showMyToast(LoginActivity.this,"服务器忙，请稍后重试！！！");
				}*/
    }
}

package com.honliv.honlivmall.base;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.model.CoreBaseModel;
import com.honliv.honlivmall.presenter.CoreBasePresenter;
import com.honliv.honlivmall.util.TUtil;
import com.honliv.honlivmall.util.ToastUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by Rodin on 2016/10/25.
 */
public abstract class BaseFragment<T extends CoreBasePresenter, E extends CoreBaseModel> extends SupportFragment {
    protected String TAG;
    protected OnBackToFirstListener _mBackToFirstListener;
    public SharedPreferences sp;
    public T mPresenter;
    public E mModel;
    protected Context mContext;
    protected Activity mActivity;
    Unbinder binder;

    protected void initToolbar(Toolbar toolbar,String title,boolean flag) {
        toolbar.setTitle(title);
        if(flag){
            toolbar.setNavigationIcon(R.drawable.back_btn);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pop();
                }
            });
        }
    }

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
        if (context instanceof OnBackToFirstListener) {
            _mBackToFirstListener = (OnBackToFirstListener) context;
        }
//        Log.i(TAG, (_mBackToFirstListener == null) + "--onAttach");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayoutView() != null) {
            return getLayoutView();
        } else {
            return inflater.inflate(getLayoutId(), null);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        sp = getContext().getSharedPreferences("config", getContext().MODE_PRIVATE);

        mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        TAG = getClass().getSimpleName();
        binder = ButterKnife.bind(this, view);
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        initUI(view, savedInstanceState);
        if (this instanceof CoreBaseView) mPresenter.attachVM(this, mModel);
        getBundle(getArguments());
        initData();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (binder != null) binder.unbind();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        _mBackToFirstListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.detachVM();
    }

    @Override
    protected FragmentAnimator onCreateFragmentAnimator() {
        FragmentAnimator fragmentAnimator = _mActivity.getFragmentAnimator();
        fragmentAnimator.setEnter(0);
        fragmentAnimator.setExit(0);
        return fragmentAnimator;
    }

    public abstract int getLayoutId();

    public View getLayoutView() {
        return null;
    }

    /**
     * 得到Activity传进来的值
     */
    public void getBundle(Bundle bundle) {

    }

    /**
     * 初始化控件
     */
    public abstract void initUI(View view, @Nullable Bundle savedInstanceState);

    /**
     * 在监听器之前把数据准备好
     */
    public void initData() {

    }

    /**
     * 处理回退事件
     * 如果是孩子fragment需要重写onBackPressedSupport(){_mBackToFirstListener.onBackToFirstFragment();return true;}
     *
     * @return
     */
    @Override
    public boolean onBackPressedSupport() {
        pop();
//        if (getChildFragmentManager().getBackStackEntryCount() > 1) {
//            popChild();
//        } else {
////            _mBackToFirstListener.onBackToFirstFragment();
//            start(getPreFragment());
//            _mActivity.finish();
//        }
        return true;
    }

    public interface OnBackToFirstListener {
        void onBackToFirstFragment();
    }

    public void showToast(String msg) {
        ToastUtils.showToast(mContext, msg, Toast.LENGTH_SHORT);
    }

    public void showLog(String msg) {
        Log.i(TAG, msg);
    }

    protected void initToolbarNav(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.i(TAG,"---initToolbarNav--setNavigationOnClickListener");
                _mActivity.onBackPressed();
            }
        });

//        initToolbarMenu(toolbar);
    }

    /**
     * 初始化底部购物车商品
     */
    protected void initShopCarNumber() {
//        shopCarNumTV = (TextView)findViewById(R.id.textShopCarNum);
//        DbUtils db = DbUtils.create(this);
//        List<Product> productList=null;
//        try {
//            if(GloableParams.USERID>0){
//                productList = db.findAll(Selector.from(Product.class)
//                        .where("userId","=", GloableParams.USERID));
//            }else{
//                productList = db.findAll(Selector.from(Product.class)
//                        .where("userId","=",-100));
//            }
//        } catch (DbException e) {
//            e.printStackTrace();
//        }
//        if(productList!=null){
//            if(productList.size() > 0){
//                int num = 0;
//                for (int i = 0; i < productList.size(); i++) {
//                    num+=productList.get(i).getNumber();
//                }
//                shopCarNumTV.setText(""+num);
//                shopCarNumTV.setVisibility(View.VISIBLE);
//            }else{
//                shopCarNumTV.setVisibility(View.INVISIBLE);
//            }
//        }else{
//            shopCarNumTV.setVisibility(View.INVISIBLE);
//        }
    }
}

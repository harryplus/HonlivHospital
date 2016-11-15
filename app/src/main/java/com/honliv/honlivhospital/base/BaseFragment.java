package com.honliv.honlivhospital.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.honliv.honlivhospital.R;
import com.honliv.honlivhospital.activity.CoreBaseView;
import com.honliv.honlivhospital.model.CoreBaseModel;
import com.honliv.honlivhospital.presenter.CoreBasePresenter;
import com.honliv.honlivhospital.utils.TUtil;
import com.honliv.honlivhospital.utils.ToastUtils;

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

    public T mPresenter;
    public E mModel;
    protected Context mContext;
    protected Activity mActivity;
    Unbinder binder;

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
        //设置状态栏透明
//        setStatusBarColor();
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
        if (getChildFragmentManager().getBackStackEntryCount() > 1) {
            popChild();
        } else {
            _mBackToFirstListener.onBackToFirstFragment();
            _mActivity.finish();
        }
        return true;
    }

    public interface OnBackToFirstListener {
        void onBackToFirstFragment();
    }

    public void showToast(String msg) {
        ToastUtils.showToast(mContext, msg, Toast.LENGTH_SHORT);
    }

    public void showLog(String msg) {
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
}

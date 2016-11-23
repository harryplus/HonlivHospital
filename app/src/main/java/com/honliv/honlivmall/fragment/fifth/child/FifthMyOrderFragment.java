package com.honliv.honlivmall.fragment.fifth.child;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.adapter.MyOrderAdapter;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.bean.OrderInfo;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.model.fifth.child.FifthMyOrderModel;
import com.honliv.honlivmall.presenter.fifth.child.FifthMyOrderPresenter;
import com.honliv.honlivmall.util.MyOrderUtils;
import com.honliv.honlivmall.view.GalleryItem;
import com.honliv.honlivmall.view.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/11/16.
 */
public class FifthMyOrderFragment extends BaseFragment<FifthMyOrderPresenter, FifthMyOrderModel> implements FifthContract.FifthMyOrderView, View.OnClickListener, PullToRefreshView.OnHeaderRefreshListener, PullToRefreshView.OnFooterRefreshListener {

    static final String TAG = "MyOrderActivity";
    public static Context mContext;
    public boolean isUpload = false;//是否是加载
    @BindView(R.id.main_pull_refresh_view)
    public PullToRefreshView mPullToRefreshView;
    public int start = 1;
    public List<OrderInfo> ordereList;//所有商品的集合
    public List<OrderInfo> currentOrderList;//商品显示列表
    @BindView(R.id.my_order_list)
    public ListView orderListView;
    public int userId = -1;
    @BindView(R.id.my_order_null_text)
    public TextView orderNullTV;
    @BindView(R.id.head_back_text)
    public TextView head_back_text;
    public MyOrderAdapter orderAdapter;

    public static FifthMyOrderFragment newInstance() {
        Bundle args = new Bundle();

        FifthMyOrderFragment fragment = new FifthMyOrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fifth_my_order;
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
        mPullToRefreshView.setOnHeaderRefreshListener(this);
        mPullToRefreshView.setOnFooterRefreshListener(this);
        if (userId == -1) {
            userId = GloableParams.USERID;
        }
        Log.i(TAG, "getUserId--" + userId);
        if (userId < 0) {
            showToast("登录状态错误，请重新登录！");
            pop();
            return;
        }
//        MyOrderUtils.getServiceOrderList((MyOrderActivity) mContext, userId);
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
            case R.id.head_back_text:
                pop();
                break;
        }
    }


    /**
     * 存GalleryItem的list集合
     */
    public List<GalleryItem> galleryitemList = new ArrayList<GalleryItem>();


    public void initDate(List<OrderInfo> orderes) {
        GalleryItem item = null;
        galleryitemList = new ArrayList<GalleryItem>();
        for (int i = 0; i < orderes.size(); i++) {
            item = new GalleryItem(getContext(), orderes.get(i));
            item.initAdapter(getContext());
            galleryitemList.add(item);
        }
        orderAdapter = new MyOrderAdapter(getContext(), galleryitemList, currentOrderList, ordereList);
        orderListView.setAdapter(orderAdapter);
    }

//    public void goBack(View view) {
//        animPreActivity(MyCenterActivity.class);
//        finish();
//    }

    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        mPullToRefreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println("上拉加载");
                start = start + 1;
                isUpload = true;
//                MyOrderUtils.getServiceOrderList((MyOrderActivity) mContext, userId);
                mPullToRefreshView.onFooterRefreshComplete();
            }
        }, 1);
    }

    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        mPullToRefreshView.postDelayed(new Runnable() {
            public void run() {
                // 设置更新时间
                // mPullToRefreshView.onHeaderRefreshComplete("最近更新:01-23 12:01");
                System.out.println("下拉更新");
                start = 1;
//                MyOrderUtils.getServiceOrderList((MyOrderActivity) mContext, userId);
                mPullToRefreshView.onHeaderRefreshComplete();
                mPullToRefreshView.setEnablePullLoadMoreDataStatus(true);
            }
        }, 1);
    }

    @Override
    public void onResume() {
        start = 1;
        if (GloableParams.USERID <= 0) {
            showToast("登陆状态错误");
            return;
        }
//        MyOrderUtils.getServiceOrderList((MyOrderActivity) mContext, GloableParams.USERID);
        super.onResume();
    }

}

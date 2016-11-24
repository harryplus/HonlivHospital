package com.honliv.honlivmall.fragment.fifth.child;

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
import com.honliv.honlivmall.view.GalleryItem;
import com.honliv.honlivmall.view.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/11/16.
 */
public class FifthMyOrderFragment extends BaseFragment<FifthMyOrderPresenter, FifthMyOrderModel> implements FifthContract.FifthMyOrderView, View.OnClickListener, PullToRefreshView.OnHeaderRefreshListener, PullToRefreshView.OnFooterRefreshListener {
    boolean isUpload = false;//是否是加载
    @BindView(R.id.main_pull_refresh_view)
    PullToRefreshView mPullToRefreshView;
    int start = 1;
    List<OrderInfo> ordereList;//所有商品的集合
    List<OrderInfo> currentOrderList;//商品显示列表
    @BindView(R.id.my_order_list)
    ListView orderListView;
    int userId = -1;
    @BindView(R.id.my_order_null_text)
    TextView orderNullTV;
    @BindView(R.id.head_back_text)
    TextView head_back_text;
    MyOrderAdapter orderAdapter;
    List<GalleryItem> galleryitemList = new ArrayList<GalleryItem>();

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
    }

    @Override
    public boolean onBackPressedSupport() {
        pop();
        return true;
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

    @Override
    public void initData() {
        mPresenter.getServiceOrderList(userId, start, 30);
    }

    public void nDate(List<OrderInfo> orderes) {
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

    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        mPullToRefreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println("上拉加载");
                start = start + 1;
                isUpload = true;
//                MyOrderUtils.OrderList((MyOrderActivity) mContext, userId);
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
//                MyOrderUtils.OrderList((MyOrderActivity) mContext, userId);
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
//        MyOrderUtils.OrderList((MyOrderActivity) mContext, GloableParams.USERID);
        super.onResume();
    }

    @Override
    public void updateView(List<OrderInfo> result) {
        if (result != null) {
            ordereList = (List<OrderInfo>) result;
            if (isUpload) {
                //加载更多
                if (ordereList.size() == 0) {
                    showToast("暂无更多内容");
                    mPullToRefreshView.setEnablePullLoadMoreDataStatus(false);
                } else {
                    currentOrderList.addAll(ordereList);

                    GalleryItem item = null;
                    for (int i = 0; i < ordereList.size(); i++) {
                        item = new GalleryItem(mContext, ordereList.get(i));
                        item.initAdapter(mContext);
                        galleryitemList.add(item);
                    }
                    orderAdapter.notifyDataSetChanged();
                }
                isUpload = false;
            } else {
                if (ordereList.size() == 0) {
                    orderListView.setVisibility(View.GONE);
                    orderNullTV.setVisibility(View.VISIBLE);
                    mPullToRefreshView.setVisibility(View.GONE);
                } else {
                    //有返回东西 ,解析出来数据，设置给屏幕
                    currentOrderList = ordereList;
                    nDate(currentOrderList);
                }
            }
        } else {
            showToast("服务器忙，请稍后重试！！！");
        }
    }
}

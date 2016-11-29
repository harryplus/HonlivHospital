package com.honliv.honlivmall.fragment.fifth.child;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.adapter.ProductListAdapter;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.fragment.global.GlobalProductDetailFragment;
import com.honliv.honlivmall.model.fifth.child.FifthFavoriteModel;
import com.honliv.honlivmall.presenter.fifth.child.FifthFavoritePresenter;
import com.honliv.honlivmall.view.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/11/16.
 */
public class FifthFavoriteFragment extends BaseFragment<FifthFavoritePresenter, FifthFavoriteModel> implements FifthContract.FifthFavoriteView, View.OnClickListener, PullToRefreshView.OnHeaderRefreshListener, PullToRefreshView.OnFooterRefreshListener, AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {
    @BindView(R.id.main_pull_refresh_view)
    PullToRefreshView mPullToRefreshView;
    @BindView(R.id.nullProductTV)
    TextView nullProductTV;
    @BindView(R.id.address_title)
    TextView address_title;//
    @BindView(R.id.head_back_text)
    TextView head_back_text;//
    @BindView(R.id.myfavorite_product_list)
    ListView productList;

    int start = 1;
    boolean isUpload = false;//是否是加载
    ArrayList<Product> productlist;//所有商品的集合
    ArrayList<Product> currentProductList;//商品显示列表
    ProductListAdapter productAdapter;

    public static FifthFavoriteFragment newInstance() {
        Bundle args = new Bundle();

        FifthFavoriteFragment fragment = new FifthFavoriteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fifth_my_favorite;
    }

    /**
     * 初始化控件
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        currentProductList = new ArrayList<>();
        productlist = new ArrayList<>();
        mPullToRefreshView.setOnHeaderRefreshListener(this);
        mPullToRefreshView.setOnFooterRefreshListener(this);
        productAdapter = new ProductListAdapter(getContext(), currentProductList, false);
        productList.setAdapter(productAdapter);
        head_back_text.setOnClickListener(this);
        productList.setOnItemLongClickListener(this);
        productList.setOnItemClickListener(this);
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }

    @Override
    public boolean onBackPressedSupport() {
        pop();
        return true;
    }

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
        mPresenter.getServiceFavInfo(GloableParams.USERID, start, 30);
    }

    @Override
    public void updateServiceFavInfo(List<Product> result) {
        if (result != null) {
            productlist.addAll(result);
            if (isUpload) {
                //加载更多
                if (productlist.size() == 0) {
                    showToast("暂无更多内容");
                    mPullToRefreshView.setEnablePullLoadMoreDataStatus(false);
                } else {
                    currentProductList.addAll(productlist);
                    productAdapter.notifyDataSetChanged();
                }
                isUpload = false;
            } else {
                if (productlist.size() == 0) {
                    nullProductTV.setVisibility(View.VISIBLE);
                    nullProductTV.setText(" 暂 无 收 藏 商 品 ");
                    mPullToRefreshView.setVisibility(View.GONE);
                    address_title.setVisibility(View.GONE);
                } else {
                    //有返回东西 ,解析出来数据，设置给屏幕
                    currentProductList.addAll(productlist);
                    productAdapter.notifyDataSetChanged();
                    mPullToRefreshView.setEnablePullLoadMoreDataStatus(true);
                }
            }
        } else {
            showToast("服务器忙，请稍后重试！！！");
        }
    }

    @Override
    public void updateServiceCancelFav(Boolean result, int position) {
        if (result != null) {
            if (result) {
                currentProductList.remove(position);
                productAdapter.notifyDataSetChanged();
                if (currentProductList.size() == 0) {
                    nullProductTV.setVisibility(View.VISIBLE);
                    nullProductTV.setText(" 暂 无 收 藏 商 品 ");
                    mPullToRefreshView.setVisibility(View.GONE);
                    address_title.setVisibility(View.GONE);
                }
            } else {
                showToast("取消收藏失败！！！");
            }
        } else {
            showToast("服务器忙，请稍后重试！！！");
        }
    }

    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        // TODO Auto-generated method stub
        mPullToRefreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                start = start + 1;
                isUpload = true;
                mPresenter.getServiceFavInfo(GloableParams.USERID, start, 30);

                mPullToRefreshView.onFooterRefreshComplete();
            }
        }, 1);
    }

    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        mPullToRefreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                start = 1;
                mPresenter.getServiceFavInfo(GloableParams.USERID, start, 30);
                mPullToRefreshView.onHeaderRefreshComplete();
                mPullToRefreshView.setEnablePullLoadMoreDataStatus(true);
            }
        }, 1);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {// 无法取消对话框
            public void onCancel(DialogInterface dialog) {
            }
        });
        builder.setTitle("取消收藏!!!");
        builder.setMessage("您确定要取消收藏这件商品？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mPresenter.getServiceCancelFav(GloableParams.USERID, currentProductList.get(position).getFavId(), position);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int pId = currentProductList.get(position).getId();
        Bundle data = new Bundle();
        data.putInt("pId", pId);
        start(GlobalProductDetailFragment.newInstance(data));
    }
}

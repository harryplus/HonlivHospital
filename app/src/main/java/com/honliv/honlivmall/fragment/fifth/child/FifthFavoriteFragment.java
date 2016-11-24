package com.honliv.honlivmall.fragment.fifth.child;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.fragment.first.child.FirstProductDetailFragment;
import com.honliv.honlivmall.model.fifth.child.FifthFavoriteModel;
import com.honliv.honlivmall.presenter.fifth.child.FifthFavoritePresenter;
import com.honliv.honlivmall.util.LogUtil;
import com.honliv.honlivmall.util.Utils;
import com.honliv.honlivmall.view.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/11/16.
 */
public class FifthFavoriteFragment extends BaseFragment<FifthFavoritePresenter, FifthFavoriteModel> implements FifthContract.FifthFavoriteView, View.OnClickListener, PullToRefreshView.OnHeaderRefreshListener, PullToRefreshView.OnFooterRefreshListener {
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
//        mPullToRefreshView.setOnHeaderRefreshListener(this);
//        mPullToRefreshView.setOnFooterRefreshListener(this);
//        productAdapter = new ProductListAdapter();
//        productList.setAdapter(productAdapter);
//        head_back_text.setOnClickListener(this);
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

//        mPresenter.getServiceFavInfo(GloableParams.USERID, start, 30);
    }

    @Override
    public void updateServiceFavInfo(List<Product> result) {
        if (result != null) {
            productlist = ((ArrayList<Product>) result);
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
                    currentProductList = productlist;
                    mPullToRefreshView.setEnablePullLoadMoreDataStatus(true);
                }
            }
            //LogUtil.info("=="+Arrays.toString(hotNames));
        } else {
            showToast("服务器忙，请稍后重试！！！");
        }
    }

    @Override
    public void updateServiceCancelFav(Boolean result) {
        if (result != null) {
            Boolean isSuccess = (Boolean) result;
            if (isSuccess) {
                //OrderList(userId);
//                initCancelOrder(position);
            } else {
                showToast("取消收藏失败！！！");
            }
        } else {
            showToast("服务器忙，请稍后重试！！！");
        }
    }

    class ProductListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return currentProductList.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return currentProductList.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            View view = null;
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                view = View.inflate(getContext(), R.layout.my_favorite_listitem, null);
                holder.productImg = (ImageView) view.findViewById(R.id.myfavorite_product_img);
                holder.productTitleTV = (TextView) view.findViewById(R.id.myfavorite_title_text);
                holder.productPriceTV = (TextView) view.findViewById(R.id.myfavorite_price_text);
                holder.deletePriceTV = (TextView) view.findViewById(R.id.myfavorite_deleteprice_text);
                holder.commentTV = (TextView) view.findViewById(R.id.myfavorite_comment_text);

                view.setTag(holder);
            } else {
                view = convertView;
                holder = (ViewHolder) view.getTag();
            }

            holder.deletePriceTV.setText("原价:￥" + currentProductList.get(position).getMarketprice());
            holder.deletePriceTV.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线
            holder.deletePriceTV.getPaint().setAntiAlias(true);// 抗锯齿
            holder.productPriceTV.setText("现价:￥" + currentProductList.get(position).getSaleprice());
            holder.productTitleTV.setText(currentProductList.get(position).getName());
            holder.commentTV.setText("(" + currentProductList.get(position).getCreateddate() + ") 加入收藏");

            Glide.with(mContext).load(Utils.checkImagUrl(Utils.checkImagUrl(currentProductList.get(position).getPic() + ""))).crossFade().placeholder(R.mipmap.picture_no).into(holder.productImg);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pId = currentProductList.get(position).getId();
                    Bundle data = new Bundle();
                    data.putInt("pId", pId);
                    start(FirstProductDetailFragment.newInstance(data));
//                    intent = new Intent(FavoriteActivity.this, ProductDetailActivity.class);
//                    intent.putExtra("pId", pId);
//                    startActivity(intent);
//                    overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);
                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    showCancelFavDialog(position);
                    return true;
                }
            });

            return view;
        }
    }

    static class ViewHolder {
        ImageView productImg;//商品图标
        TextView productTitleTV;//商品标题
        TextView productPriceTV;//会员价
        TextView deletePriceTV;//原价
        TextView commentTV;//评论
    }

    void showCancelFavDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {// 无法取消对话框
            public void onCancel(DialogInterface dialog) {
                //loadHomeActivity();// 取消对话框，进入主界面
                LogUtil.info(" 取消对话框");
            }
        });
        builder.setTitle("取消收藏!!!");
        builder.setMessage("您确定要取消收藏这件商品？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mPresenter.getServiceCancelFav(GloableParams.USERID, currentProductList.get(position).getFavId());
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }

    protected void initCancelOrder(int position) {
        currentProductList.remove(position);
        productAdapter.notifyDataSetChanged();
        if (currentProductList.size() == 0) {
            nullProductTV.setVisibility(View.VISIBLE);
            nullProductTV.setText(" 暂 无 收 藏 商 品 ");
            mPullToRefreshView.setVisibility(View.GONE);
            address_title.setVisibility(View.GONE);
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
}

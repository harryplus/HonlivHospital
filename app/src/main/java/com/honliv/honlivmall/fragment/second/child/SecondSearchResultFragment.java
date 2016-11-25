package com.honliv.honlivmall.fragment.second.child;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.contract.SecondContract;
import com.honliv.honlivmall.fragment.global.GlobalProductDetailFragment;
import com.honliv.honlivmall.model.second.child.SecondSearchResultModel;
import com.honliv.honlivmall.presenter.second.child.SecondSearchResultPresenter;
import com.honliv.honlivmall.util.DensityUtil;
import com.honliv.honlivmall.util.Utils;
import com.honliv.honlivmall.view.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/10/26.
 */
public class SecondSearchResultFragment extends BaseFragment<SecondSearchResultPresenter, SecondSearchResultModel> implements SecondContract.SecondSearchResultView, View.OnClickListener, PullToRefreshView.OnHeaderRefreshListener, PullToRefreshView.OnFooterRefreshListener {
    @BindView(R.id.productLv)
    GridView productGv;
    // String[] productTitles;
    boolean isBigFlag = true;
    boolean isLowestPrice = true;
    BaseAdapter adapter;
    @BindView(R.id.textRankSale)
    TextView textRankSale;
    @BindView(R.id.textRankPrice)
    TextView textRankPrice;
    @BindView(R.id.textRankTime)
    TextView textRankTime;
    @BindView(R.id.textRankImage)
    TextView textRankImage;
    @BindView(R.id.backTv)
    TextView backTv;

    boolean flagPrice = false;

    String searchKey;
    boolean[] flags;
    ArrayList<Product> currentProductList; //商品列表
    ArrayList<Product> productList;//服务器返回的商品列表
    @BindView(R.id.textsearchNull)
    TextView textsearchNull;
    @BindView(R.id.search_result_Title)
    TextView searchResultTitle;

    GVItemListener gvItemListener;//条目点击监听器

    int start = 1;
    boolean isUpload = false;//是否是加载
    String orderby = "hot";
    @BindView(R.id.main_pull_refresh_view)
    PullToRefreshView mPullToRefreshView;

    public static SecondSearchResultFragment newInstance(Bundle args) {
        SecondSearchResultFragment fragment = new SecondSearchResultFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_second_search_list;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        mPullToRefreshView.setOnHeaderRefreshListener(this);
        mPullToRefreshView.setOnFooterRefreshListener(this);
        flags = new boolean[]{true, false, false, false};

        setBackgroundColor();
        textRankSale.setBackgroundResource(R.drawable.segment_selected_2_bg);
        backTv.setOnClickListener(this);
        textRankImage.setOnClickListener(this);
        textRankTime.setOnClickListener(this);
        textRankPrice.setOnClickListener(this);
        textRankSale.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backTv:
                pop();
                break;
            case R.id.textRankImage:
                initFlags();
                flags[3] = true;
                start = 1;
                setBackgroundColor();
                textRankImage.setBackgroundResource(R.drawable.segment_selected_3_bg);
                orderby = "hot";
                mPresenter.getSeaviceSearchProductList(searchKey, orderby, start);
                break;
            case R.id.textRankTime:
                initFlags();
                flags[2] = true;
                flags[3] = false;
                start = 1;
                setBackgroundColor();
                textRankTime.setBackgroundResource(R.drawable.segment_selected_2_bg);
                orderby = "new";
                mPresenter.getSeaviceSearchProductList(searchKey, orderby, start);
                break;
            case R.id.textRankPrice:
                initFlags();
                flags[1] = true;
                flags[3] = false;
                start = 1;
                setBackgroundColor();
                textRankPrice.setBackgroundResource(R.drawable.segment_selected_2_bg);

                if (isLowestPrice) {
                    isLowestPrice = !isLowestPrice;
                    orderby = "price";
                    mPresenter.getSeaviceSearchProductList(searchKey, orderby, start);
                } else {
                    isLowestPrice = !isLowestPrice;
                    orderby = "pricedesc";
                    mPresenter.getSeaviceSearchProductList(searchKey, orderby, start);
                }
                break;

            case R.id.textRankSale:
                initFlags();
                flags[0] = true;
                flags[3] = false;
                start = 1;
                orderby = "hot";
                setBackgroundColor();
                textRankSale.setBackgroundResource(R.drawable.segment_selected_1_bg);
                mPresenter.getSeaviceSearchProductList(searchKey, orderby, start);
                break;
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        pop();
        return true;
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }

    @Override
    public void initData() {
        Bundle arguments = getArguments();
        searchKey = arguments.getString("searchKey");
//        searchKey = (String)this.getIntent().getSerializableExtra("searchKey");
        searchResultTitle.setText("(" + searchKey + ")搜索结果");
        mPresenter.getSeaviceSearchProductList(searchKey, orderby, start);
    }

    void initFlags() {
        for (int i = 0; i < flags.length; i++) {
            flags[i] = false;
        }
    }

    void setBackgroundColor() {
        textRankSale.setBackgroundResource(R.drawable.segment_normal_1_bg);
        textRankPrice.setBackgroundResource(R.drawable.segment_normal_2_bg);
        textRankTime.setBackgroundResource(R.drawable.segment_normal_2_bg);
        textRankImage.setBackgroundResource(R.drawable.segment_normal_3_bg);
    }

    void initDate() {
        if (flags[3]) {
            adapter = new GridAdapter();
            productGv.setNumColumns(2);
            productGv.setVerticalSpacing(DensityUtil.dip2px(getContext(), 4L));

        } else {
            productGv.setNumColumns(1);
            productGv.setVerticalSpacing(DensityUtil.dip2px(getContext(), 0L));
            adapter = new ListAdapter();
        }
        productGv.setAdapter(adapter);

        productGv.setOnItemClickListener(new GVItemListener());
    }

    @Override
    public void updataSearchProductList(List<Product> result) {
        if (result != null) {
            productList = ((ArrayList<Product>) result);
            if (isUpload) {
                //加载更多
                if (productList.size() == 0) {
                    showToast("暂无更多内容");
                    mPullToRefreshView.setEnablePullLoadMoreDataStatus(false);
                } else {
                    currentProductList.addAll(productList);
                    adapter.notifyDataSetChanged();
                }
                isUpload = false;
            } else {
                if (productList.size() == 0) {
                    textsearchNull.setVisibility(View.VISIBLE);
                    textsearchNull.setText(" 暂 无 搜 索 商 品 ");
                    mPullToRefreshView.setVisibility(View.GONE);
                } else {
                    //有返回东西 ,解析出来数据，设置给屏幕
                    currentProductList = productList;
                    mPullToRefreshView.setEnablePullLoadMoreDataStatus(true);
                    initDate();
                }
            }
        } else {
            textsearchNull.setVisibility(View.VISIBLE);
            mPullToRefreshView.setVisibility(View.GONE);
            showToast("服务器忙，请稍后重试！！！");
        }
    }

    class GridAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return currentProductList.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            View view = null;
            GirdViewHolder holder = null;
            if (convertView == null) {
                holder = new GirdViewHolder();
                view = View.inflate(getContext(), R.layout.searchresultgriditem, null);//=====================
                holder.productImg = (ImageView) view.findViewById(R.id.searchresult_product_img);
                holder.productPriceTV = (TextView) view.findViewById(R.id.searchresult_product_price_text);

                view.setTag(holder);
            } else {
                view = convertView;
                holder = (GirdViewHolder) view.getTag();
            }

            holder.productPriceTV.setText("现价:￥" + (currentProductList.get(position)).getSaleprice());
            Glide.with(mContext).load(Utils.checkImagUrl(Utils.checkImagUrl(currentProductList.get(position).getPic() + ""))).crossFade().placeholder(R.mipmap.picture_no).into(holder.productImg);
            return view;
        }
    }

    class ListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return currentProductList.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            View view = null;
            ListViewHolder holder = null;
            if (convertView == null) {
                holder = new ListViewHolder();
                view = View.inflate(getContext(), R.layout.searchresultlistitem, null);//=====================
                holder.productImg = (ImageView) view.findViewById(R.id.searchresult_product_img);
                holder.productTitleTV = (TextView) view.findViewById(R.id.searchresult_product_title_text);
                holder.productPriceTV = (TextView) view.findViewById(R.id.searchresult_product_price_text);
                holder.productOldPriceTV = (TextView) view.findViewById(R.id.searchresult_product_oldprice_text);
                holder.commentTV = (TextView) view.findViewById(R.id.searchresult_product_comment_text);

                view.setTag(holder);
            } else {
                view = convertView;
                holder = (ListViewHolder) view.getTag();
            }
            holder.productTitleTV.setText((currentProductList.get(position)).getName());
            holder.productOldPriceTV.setText("原价:￥" + (currentProductList.get(position)).getMarketprice());
            holder.productOldPriceTV.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线
            holder.productOldPriceTV.getPaint().setAntiAlias(true);// 抗锯齿
            holder.productPriceTV.setText("现价:￥" + (currentProductList.get(position)).getSaleprice());
            holder.commentTV.setText("已有(" + ((currentProductList.get(position)).getCommentCount()) + ")人评论");
            Glide.with(mContext).load(Utils.checkImagUrl(Utils.checkImagUrl(currentProductList.get(position).getPic() + ""))).crossFade().placeholder(R.mipmap.picture_no).into(holder.productImg);
            return view;
        }
    }

    static class ListViewHolder {
        ImageView productImg;//商品图标
        TextView productTitleTV;//商品标题
        TextView productPriceTV;//商品现价
        TextView productOldPriceTV;//商品原价
        TextView commentTV;//评论
    }

    static class GirdViewHolder {
        ImageView productImg;//商品图标
        TextView productPriceTV;//商品价
    }

    class GVItemListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            int pId = currentProductList.get(position).getId();
            Bundle data = new Bundle();
            data.putInt("pId", pId);
            start(GlobalProductDetailFragment.newInstance(data));
//            Intent intent = new Intent(SearchResultActivity.this,ProductDetailActivity.class);
//            intent.putExtra("pId", pId);
//            startActivityForResult(intent, 100);
////			finish();
//            overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);
        }
    }

    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        mPullToRefreshView.postDelayed(new Runnable() {

            @Override
            public void run() {
                start = start + 1;
                isUpload = true;
                mPresenter.getSeaviceSearchProductList(searchKey, orderby, start);
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
                mPresenter.getSeaviceSearchProductList(searchKey, orderby, start);
                mPullToRefreshView.onHeaderRefreshComplete();
                mPullToRefreshView.setEnablePullLoadMoreDataStatus(true);
            }
        }, 1);
    }
}

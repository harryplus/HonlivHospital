package com.honliv.honlivmall.fragment.third.child;

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
import com.honliv.honlivmall.adapter.ProductListAdapter;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.bean.ProductListFilter;
import com.honliv.honlivmall.contract.ThirdContract;
import com.honliv.honlivmall.fragment.first.child.FirstProductDetailFragment;
import com.honliv.honlivmall.model.third.child.ThirdProductListModel;
import com.honliv.honlivmall.presenter.third.child.ThirdProductListPresenter;
import com.honliv.honlivmall.util.DensityUtil;
import com.honliv.honlivmall.util.PromptManager;
import com.honliv.honlivmall.util.Utils;
import com.honliv.honlivmall.view.PullToRefreshView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/10/26.
 */
public class ThirdProductListFragment extends BaseFragment<ThirdProductListPresenter, ThirdProductListModel> implements ThirdContract.ThirdProductListView, PullToRefreshView.OnHeaderRefreshListener, PullToRefreshView.OnFooterRefreshListener, View.OnClickListener {
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
    @BindView(R.id.productlist_Title)
    TextView productlistTitle;
    @BindView(R.id.productLv)
    GridView productLv;
    BaseAdapter adapter;

    boolean[] flags = new boolean[4];
    boolean isLowestPrice = true;
    List<Product> currentProductList;//当前显示商品的集合
    List<Product> productList;//返回商品的集合
    @BindView(R.id.nullProductTV)
    TextView nullProductTV;

    int cId;//分类传递过来的商品id

    int start = 1;
    boolean isUpload = false;//是否是加载
    String orderby = "hot";
    @BindView(R.id.main_pull_refresh_view)
    PullToRefreshView mPullToRefreshView;

    int colorId = -1;
    int sizeId = -1;
    int key3Id = -1;
    int key4Id = -1;

    public static ThirdProductListFragment newInstance(Bundle args) {
        ThirdProductListFragment fragment = new ThirdProductListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_third_product_list;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        mPullToRefreshView.setOnHeaderRefreshListener(this);
        mPullToRefreshView.setOnFooterRefreshListener(this);
        textRankTime.setOnClickListener(this);
        textRankImage.setOnClickListener(this);
        textRankPrice.setOnClickListener(this);
        textRankSale.setOnClickListener(this);
        backTv.setOnClickListener(this);
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }


    public void initDate() {
        Bundle data = getArguments();
        cId = data.getInt("cId", -1);
        String cTitle = data.getString("cTitle");
//       cId = this.getIntent().getIntExtra("cId", -1);
//       String cTitle = this.getIntent().getStringExtra("cTitle");
        if (cTitle != null) {
            productlistTitle.setText("(" + cTitle + ")列表");
        }
        mPresenter.getServiceProductList(cId, "hot", start, 30);
        if (flags[3]) {
            productLv.setNumColumns(2);
            productLv.setVerticalSpacing(DensityUtil.dip2px(getContext(), 4L));
            adapter = new GridAdapter();
        } else {
            productLv.setNumColumns(1);
            productLv.setVerticalSpacing(DensityUtil.dip2px(getContext(), 0L));
            adapter = new ProductListAdapter(getContext(), currentProductList, false);
        }
        productLv.setAdapter(adapter);
        productLv.setOnItemClickListener(new ProductItemListener());
    }

    /**
     * flags全部设为false
     */
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

    @Override
    public void updateView(ProductListFilter result) {
        if (result != null) {
            ProductListFilter productLS = result;
            productList = productLS.getProductlist();

            if (isUpload) {
                //加载更多
                if (productList == null || productList.size() == 0) {
                    showToast("暂无更多内容");
                    mPullToRefreshView.setEnablePullLoadMoreDataStatus(false);
                } else {
                    currentProductList.addAll(productList);
                    adapter.notifyDataSetChanged();
                }
                isUpload = false;
            } else {
                if (productList == null || productList.size() == 0) {
                    nullProductTV.setVisibility(View.VISIBLE);
                    mPullToRefreshView.setVisibility(View.GONE);
                } else {
                    //有返回东西 ,解析出来数据，设置给屏幕
                    currentProductList = productList;
                    mPullToRefreshView.setEnablePullLoadMoreDataStatus(true);
                    initDate();
                }
            }
        } else {
            PromptManager.showToast(getContext(), "服务器忙，请稍后重试！！！");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textRankTime:
                initFlags();
                flags[2] = true;
                flags[3] = false;
                start = 1;
                setBackgroundColor();
                textRankTime.setBackgroundResource(R.drawable.segment_selected_2_bg);
                orderby = "new";
                mPresenter.getServiceProductList(cId, "new", start, 30);
                break;
            case R.id.textRankImage:
                initFlags();
                flags[3] = true;
                start = 1;
                setBackgroundColor();
                textRankImage.setBackgroundResource(R.drawable.segment_selected_3_bg);
                orderby = "hot";
                mPresenter.getServiceProductList(cId, "hot", start, 30);
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
                    mPresenter.getServiceProductList(cId, "price", start, 30);
                } else {
                    isLowestPrice = !isLowestPrice;
                    orderby = "pricedesc";
                    mPresenter.getServiceProductList(cId, "pricedesc", start, 30);
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
                mPresenter.getServiceProductList(cId, "hot", start, 30);
                break;
            case R.id.backTv:
                pop();
                break;
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        pop();
        return true;
    }

    class ProductItemListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            int pId = currentProductList.get(position).getId();
            Bundle data = new Bundle();
            data.putInt("pId", pId);
            start(FirstProductDetailFragment.newInstance(data));
//            Intent intent = new Intent(ProductListActivity.this, ProductDetailActivity.class);
//            intent.putExtra("pId", pId);
//            startActivity(intent);
//            //finish();
//            overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);

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

    static class GirdViewHolder {
        ImageView productImg;//商品图标
        TextView productPriceTV;//商品价
    }


    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        mPullToRefreshView.postDelayed(new Runnable() {

            @Override
            public void run() {
                System.out.println("上拉加载");/*
                listDrawable.add(R.drawable.pic1);
				adapter.notifyDataSetChanged();*/
                start = start + 1;
                isUpload = true;
                mPresenter.getServiceProductList(cId, orderby, start, 30);
                mPullToRefreshView.onFooterRefreshComplete();
            }
        }, 1);

    }

    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        mPullToRefreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 设置更新时间
                // mPullToRefreshView.onHeaderRefreshComplete("最近更新:01-23 12:01");
                System.out.println("下拉更新");/*
                listDrawable.add(R.drawable.pic1);
				adapter.notifyDataSetChanged();*/

                start = 1;
                mPresenter.getServiceProductList(cId, orderby, start, 30);
                mPullToRefreshView.onHeaderRefreshComplete();
                mPullToRefreshView.setEnablePullLoadMoreDataStatus(true);
            }
        }, 1);
    }

    @Override
    public void onResume() {
        super.onResume();
        initShopCarNumber();
    }
}

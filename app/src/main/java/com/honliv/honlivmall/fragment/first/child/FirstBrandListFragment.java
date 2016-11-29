package com.honliv.honlivmall.fragment.first.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.adapter.ProductListAdapter;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.contract.FirstContract;
import com.honliv.honlivmall.fragment.global.GlobalProductDetailFragment;
import com.honliv.honlivmall.model.first.child.FirstBrandListModel;
import com.honliv.honlivmall.presenter.first.child.FirstBrandListPresenter;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

/**
 * Created by YoKeyword on 16/6/3.
 */
public class FirstBrandListFragment extends BaseFragment<FirstBrandListPresenter, FirstBrandListModel> implements FirstContract.FirstBrandListView, AdapterView.OnItemClickListener {
    @BindView(R.id.nullProductTV)
    TextView nullProductTV;
    @BindView(R.id.marketing_productLV)
    ListView marketing_productLV;
    int start = 1;
    Timer timer;
    TimerTask task;


    List<Product> products;//返回的商品集合

    int brandId;

    public static FirstBrandListFragment newInstance(Bundle args) {
        FirstBrandListFragment fragment = new FirstBrandListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_first_brandlist;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showError(String msg) {

    }


    public void initData() {
        brandId = getArguments().getInt("brandid", -1);
        marketing_productLV.setAdapter(new ProductListAdapter(getContext(), products, true));
        marketing_productLV.setOnItemClickListener(this);
        mPresenter.getServiceProductLS(brandId);
    }

    @Override
    public void updateProductLS(List<Product> result) {
        if (result != null) {//有返回东西 ,解析出来数据，设置给屏幕
            products = result;
            if (products.size() == 0) {//返回一个空的list对象，里面没有数据
                //PromptManager.showToast(getApplicationContext(), "服务器忙，请稍后重试！！！");
                //return;
                nullProductTV.setVisibility(View.VISIBLE);
                marketing_productLV.setVisibility(View.GONE);
            } else {
                //里面有真实数据
                initData();
            }
        } else {
            showToast("服务器忙，请稍后重试！！！");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Product selProduct = products.get(position);
        Bundle data = new Bundle();
        data.putInt("pId", selProduct.getId());
        start(GlobalProductDetailFragment.newInstance(data));
//        intent = new Intent();
//        intent.setClass(getApplicationContext(), ProductDetailActivity.class);
//        intent.putExtra("pId", selProduct.getId());
//        startActivity(intent);
//        overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);//下个
    }
}

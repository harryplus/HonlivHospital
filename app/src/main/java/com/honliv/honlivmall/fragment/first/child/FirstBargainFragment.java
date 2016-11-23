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
import com.honliv.honlivmall.model.first.child.FirstBargainModel;
import com.honliv.honlivmall.presenter.first.child.FirstBargainPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by YoKeyword on 16/6/5.
 */
public class FirstBargainFragment extends BaseFragment<FirstBargainPresenter, FirstBargainModel> implements FirstContract.FirstBargainView, View.OnClickListener {
    @BindView(R.id.marketing_productLV)
    ListView marketing_productLV;
    @BindView(R.id.nullProductTV)
    TextView nullProductTV;
    @BindView(R.id.head_goback)
    TextView headGoback;

    List<Product> products;//返回的商品集合
    private ProductListAdapter adapter;


    public static FirstBargainFragment newInstance() {
        Bundle args = new Bundle();

        FirstBargainFragment fragment = new FirstBargainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_first_bargain;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        products = new ArrayList<>();
        adapter = new ProductListAdapter(getContext(), products, true);
        marketing_productLV.setAdapter(adapter);
        marketing_productLV.setOnItemClickListener(new MarkingListListener());
        headGoback.setOnClickListener(this);
    }


    @Override
    public void showError(String msg) {

    }

    @Override
    public boolean onBackPressedSupport() {
        pop();
        return true;
    }

    public void initData() {
        mPresenter.getServiceProductLS();
    }

    @Override
    public void updateProductLS(List<Product> result) {
        if (result != null) {//有返回东西 ,解析出来数据，设置给屏幕
            if (products.size() == 0) {//返回一个空的list对象，里面没有数据
                nullProductTV.setVisibility(View.VISIBLE);
                marketing_productLV.setVisibility(View.GONE);
            } else {
                products.clear();
                products.addAll(result);
                adapter.notifyDataSetChanged();
            }
        } else {
            showToast("服务器忙，请稍后重试！！！");
        }
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.head_goback:
                pop();
                break;
        }
    }

    class MarkingListListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
//            if (GloableParams.USERID < 0) {
//                //弹出登陆按钮
//                showLoginDialog();
//            } else {
            Product selProduct = products.get(position);

//            intent = new Intent();
//            intent.setClass(getApplicationContext(), ProductDetailActivity.class);
//            intent.putExtra("pId", selProduct.getId());
//            startActivity(intent);
//            overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);//下个
//            }
        }
    }
}

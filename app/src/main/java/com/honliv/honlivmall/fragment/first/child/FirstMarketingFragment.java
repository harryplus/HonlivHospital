package com.honliv.honlivmall.fragment.first.child;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.honliv.honlivmall.ConstantValue;
import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.adapter.MarketingAdapter;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.contract.FirstContract;
import com.honliv.honlivmall.fragment.global.GlobalProductDetailFragment;
import com.honliv.honlivmall.model.first.child.FirstMarketingModel;
import com.honliv.honlivmall.presenter.first.child.FirstMarketingPresenter;
import com.honliv.honlivmall.util.BuilderTools;
import com.honliv.honlivmall.util.ViewUtils;


import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/10/29.
 */
public class FirstMarketingFragment extends BaseFragment<FirstMarketingPresenter, FirstMarketingModel> implements SwipeRefreshLayout.OnRefreshListener, FirstContract.FirstMarketingView, View.OnClickListener {
    @BindView(R.id.nullProductTV)
    TextView nullProductTV;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.marketing_productLV)
    ListView marketing_productLV;
    Timer timer;
    TimerTask task;
    List<Product> products;//返回的商品集合
    private MarketingAdapter marketingAdapter;

    Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int resultNum = msg.what;

            if (resultNum == 20) {  // products.size()
                for (int i = 0; i < products.size(); i++) {
                    if (marketing_productLV != null) {
                        TextView timeTV = (TextView) marketing_productLV.findViewWithTag(i);
                        String dateStr = products.get(i).getLefttime();
                        if (timeTV != null) {
                            ViewUtils.updataTimeTV(timeTV, dateStr);
                        }
                    }
                }
            }
        }
    };

    public static FirstMarketingFragment newInstance() {

        Bundle args = new Bundle();

        FirstMarketingFragment fragment = new FirstMarketingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_first_marketing;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        initToolbar(toolbar,getString(R.string.text_marketing),true);
//        headGoback.setOnClickListener(this);
    }

    @Override
    public void onRefresh() {

    }


    @Override
    public void showError(String msg) {

    }

    void initTimer() {
        timer = new Timer();
        task = new TimerTask() {
            public void run() {
                Message message = Message.obtain();
                message.what = 20;
                handler.sendMessage(message);
            }
        };
        timer.schedule(task, 1000, 1000);
    }

    @Override
    public void initData() {
        initTimer();
        products = new ArrayList<>();
        marketingAdapter = new MarketingAdapter(getContext(), getPreFragment(), products);
        marketing_productLV.setAdapter(marketingAdapter);
        marketing_productLV.setOnItemClickListener(new MarkingListListener());
        mPresenter.getServiceProductLS();
    }


    @Override
    public void onResume() {
        super.onResume();
        initTimer();
    }

    @Override
    public void onDestroy() {
        if (timer != null) {
            timer.cancel();
            timer = null;
            task = null;
        }

        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        if (timer != null) {
            timer.cancel();
            timer = null;
            task = null;
        }
        super.onDestroyView();
    }

    @Override
    public void updateProductLS(List<Product> result) {
        if (result != null) {//有返回东西 ,解析出来数据，设置给屏幕
            if (result.size() == 0) {//返回一个空的list对象，里面没有数据
                nullProductTV.setVisibility(View.VISIBLE);
                marketing_productLV.setVisibility(View.GONE);
            } else {
                products.clear();
                products.addAll(result);
                marketingAdapter.notifyDataSetChanged();
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
        }
    }

    class MarkingListListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            if (GloableParams.USERID < 0) {
                //弹出登陆按钮
                BuilderTools.showLoginDialog(mContext, getPreFragment(), "抢购需要登录");
            } else {
                Product selProduct = products.get(position);
                mPresenter.mRxManager.post(ConstantValue.msg_privilegeproduct, selProduct);
                Bundle data=new Bundle();
                data.putInt("pId", selProduct.getId());
                data.putSerializable("privilegeProduct", selProduct);
                start(GlobalProductDetailFragment.newInstance(data));

//                intent = new Intent();
//                intent.setClass(getApplicationContext(), ProductDetailActivity.class);
//                intent.putExtra("privilegeProduct", selProduct);
//                intent.putExtra("pId", selProduct.getId());
//                startActivity(intent);
//                overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);//下个
            }
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        // 这里实际项目中推荐使用 EventBus接耦
        pop();
        return true;
    }
}

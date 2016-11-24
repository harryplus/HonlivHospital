package com.honliv.honlivmall.fragment.fifth.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.adapter.HxtAdapter;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.bean.HxtBean;
import com.honliv.honlivmall.bean.OrderInfo;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.model.fifth.child.FifthMyHxtModel;
import com.honliv.honlivmall.presenter.fifth.child.FifthMyHxtPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/11/16.
 */
public class FifthMyHxtFragment extends BaseFragment<FifthMyHxtPresenter, FifthMyHxtModel> implements FifthContract.FifthMyHxtView, View.OnClickListener, AdapterView.OnItemClickListener {

    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.addCard)
    ImageView addCard;
    @BindView(R.id.head_back_text)
    TextView head_back_text;

    HxtAdapter adapter;
    ArrayList<HxtBean> dataList;
    OrderInfo orderInfo = null;

    public static FifthMyHxtFragment newInstance() {
        Bundle args = new Bundle();

        FifthMyHxtFragment fragment = new FifthMyHxtFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fifth_myhxt;
    }

    /**
     * 初始化控件
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        addCard.setOnClickListener(this);
        head_back_text.setOnClickListener(this);
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addCard:
                start(FifthMyHxtPwdFragment.newInstance(new Bundle()));
                break;
            case R.id.head_back_text:
                pop();
                break;
        }
    }


    public void initData() {
        Bundle arguments = getArguments();
        orderInfo = (OrderInfo) arguments.getSerializable("orderInfo");
//        orderInfo = (OrderInfo) getIntent().getSerializableExtra("orderInfo");
        dataList = new ArrayList<HxtBean>();
        adapter = new HxtAdapter(getContext(), dataList);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);
        mPresenter.getData();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HxtBean hxtBean = dataList.get(position);
        if (orderInfo == null) {
//            Intent intent = new Intent(this, MyHxtDetailActivity.class);
//            intent.putExtra("hxtBean", hxtBean);
//            startActivity(intent);
            Bundle data = new Bundle();
            data.putSerializable("hxtBean", hxtBean);
            start(FifthMyHxtDetailFragment.newInstance(data));
        } else {
            Bundle data = new Bundle();
            data.putSerializable("orderInfo", orderInfo);
            data.putString("cardNo", hxtBean.getCardNo());
            start(FifthMyHxtPwdFragment.newInstance(data));
//            Intent intent = new Intent(MyHxtActivity.this, MyHxtPwdActivity.class);
//            intent.putExtra("orderInfo", orderInfo);
//            intent.putExtra("cardNo", hxtBean.getCardNo());
//            startActivity(intent);
        }
    }

    @Override
    public void updateView(List<HxtBean> list) {
        if (list != null) {
            dataList.clear();
            dataList.addAll(list);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        pop();
        return true;
    }
}

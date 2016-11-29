package com.honliv.honlivmall.fragment.third.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.activity.MainActivity;
import com.honliv.honlivmall.adapter.MyCategoryAdapter;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.bean.Category;
import com.honliv.honlivmall.contract.ThirdContract;
import com.honliv.honlivmall.listener.MyCategoryItemClickListener;
import com.honliv.honlivmall.model.third.child.ThirdMainModel;
import com.honliv.honlivmall.presenter.third.child.ThirdMainPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Rodin on 2016/10/26.
 */
public class ThirdMainFragment extends BaseFragment<ThirdMainPresenter, ThirdMainModel> implements ThirdContract.ThirdMainView, AdapterView.OnItemClickListener {
    @BindView(R.id.category1List)
    ListView category1List;
    ArrayList<Category> categorys;//一级分类.里面有所有的分类信息
    MyCategoryAdapter myCategoryAdapter;
//    MyCategoryItemClickListener myCategoryItemClickListener;

    public static ThirdMainFragment newInstance() {
        Bundle args = new Bundle();

        ThirdMainFragment fragment = new ThirdMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_third_main;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        categorys = new ArrayList<>();
        myCategoryAdapter = new MyCategoryAdapter(getContext(), categorys);
        category1List.setAdapter(myCategoryAdapter);
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }

    @Override
    public void updateView(List<Category> result) {
        if (result != null) {
            category1List.setVisibility(View.VISIBLE);
            categorys.addAll(result);
            GloableParams.hasCategory = true;
            GloableParams.categoryInfos = categorys;
//            myCategoryItemClickListener.setData(categorys);
            myCategoryAdapter.notifyDataSetChanged();
        }else{

        }
    }

    // 初始化数据
    public void initData() {
//        if (myCategoryItemClickListener == null) {
//            myCategoryItemClickListener = new MyCategoryItemClickListener(this);
//        }
        category1List.setOnItemClickListener(this);
    }

    @Override
    public boolean onBackPressedSupport() {
        ((MainActivity) getActivity()).onBackToFirstFragment();
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Category category = categorys.get(position);
        if (category.isHaschild()) {
            Bundle data=new Bundle();
            data.putSerializable("category", category);
            start(ThirdCategoryFragment.newInstance(data));
        } else {
//            mContext.start(ThirdCategoryFragment.newInstance(data));
//            intent = new Intent(mContext, ProductListActivity.class);
        }
    }
}

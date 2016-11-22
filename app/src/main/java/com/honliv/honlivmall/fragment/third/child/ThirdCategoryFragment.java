package com.honliv.honlivmall.fragment.third.child;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.adapter.MyELVAdapter;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.bean.Category;
import com.honliv.honlivmall.contract.ThirdContract;
import com.honliv.honlivmall.model.third.child.ThirdCategoryModel;
import com.honliv.honlivmall.presenter.third.child.ThirdCategoryPresenter;
import com.honliv.honlivmall.util.PromptManager;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/10/26.
 */
public class ThirdCategoryFragment extends BaseFragment<ThirdCategoryPresenter, ThirdCategoryModel> implements ThirdContract.ThirdCategoryView {
    @BindView(R.id.category2EList)
    ExpandableListView ELV_category;

    private String[] groupTitles;
    private String[] item_describes;
    private TextView categoryEmptyListTv;
    private List<Bitmap> bitmaps;
    private String[] category3;
    private String[][] childTitle;
    private int[] groupLogos;
    private int[][] childLogos;
    private Category category;//
    private List<Category> groupList;//本页面的父分类的

    public static ThirdCategoryFragment newInstance() {

        Bundle args = new Bundle();

        ThirdCategoryFragment fragment = new ThirdCategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_third_category;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
//        category = (Category)this.getIntent().getSerializableExtra("category");
        groupList = category.getChildlist();

        initView();
    }

    @Override
    public void showError(String msg) {

    }


    private void getAllCategorys(List<Category> childlist) {
        int index = 2;
        for (int i = 0; i < childlist.size(); i++) {
            groupTitles = new String[childlist.size()];
            groupTitles[i] = childlist.get(i).getTitle();
            if (childlist.get(i).isHaschild()) {

            }
        }
    }

    private int sign = -1;//控制列表的展开 在不在顶部

    private void initView() {
        ELV_category.setAdapter(new MyELVAdapter(getContext(), groupList));

        ELV_category.setOnChildClickListener(new MyChildListener());
        ELV_category.setGroupIndicator(null);//设置去掉前面的箭头

        ELV_category.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override//一次只展开一组
            public void onGroupExpand(int groupPosition) {
                // TODO Auto-generated method stub
                //for (int i = 0; i < groupTitles.length; i++) {
                for (int i = 0; i < groupList.size(); i++) {
                    if (groupPosition != i) {
                        ELV_category.collapseGroup(i);
                    }
                }
            }
        });

        //只展开一个group的实现方法，置于顶端
        ELV_category.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // TODO Auto-generated method stub
                if (sign == -1) {
                    // 展开被选的group
                    ELV_category.expandGroup(groupPosition);
                    // 设置被选中的group置于顶端
                    ELV_category.setSelectedGroup(groupPosition);
                    sign = groupPosition;
                } else if (sign == groupPosition) {
                    ELV_category.collapseGroup(sign);
                    sign = -1;
                } else {
                    ELV_category.collapseGroup(sign);
                    // 展开被选的group
                    ELV_category.expandGroup(groupPosition);
                    // 设置被选中的group置于顶端
                    ELV_category.setSelectedGroup(groupPosition);
                    sign = groupPosition;
                }
                return true;
            }
        });

        // 这里是控制如果列表没有孩子菜单不展开的效果
        ELV_category.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent,
                                        View v, int groupPosition, long id) {
                // TODO Auto-generated method stub
                //if (childTitle[groupPosition].length==0) {// isEmpty没有
                if (groupList.get(groupPosition).isHaschild()) {// isEmpty没有
                    return false;
                } else {
                    String titlegroup = groupList.get(groupPosition).getTitle();
                    showToast("已经没有子类，进入商品列表=" + titlegroup);
                    int cId = groupList.get(groupPosition).getId();

//                    Intent intent = new Intent(CategoryActivity2.this,ProductListActivity.class);
//                    intent.putExtra("cId", cId);
//                    intent.putExtra("cTitle", titlegroup);
//                    startActivity(intent);
                    //finish();
                    return true;
                }
            }
        });
    }

    /**
     * @author wang
     */
    private class MyChildListener implements ExpandableListView.OnChildClickListener {

        @Override
        public boolean onChildClick(ExpandableListView parent, View v,
                                    int groupPosition, int childPosition, long id) {
            if (groupList.get(groupPosition).getChildlist().get(childPosition).isHaschild()) {
                PromptManager.showToastTest(getContext(), "还有子分类。。。");

                int cId = groupList.get(groupPosition).getChildlist().get(childPosition).getId();

//                Intent intent = new Intent();
//                intent.putExtra("cId", cId);
//                intent.putExtra("cTitle", groupList.get(groupPosition).getChildlist().get(childPosition).getTitle());
//                intent.setClass(getContext(), ProductListActivity.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);
            } else {
                int cId = groupList.get(groupPosition).getChildlist().get(childPosition).getId();

//                Intent intent = new Intent();
//                intent.putExtra("cId", cId);
//                intent.putExtra("cTitle", groupList.get(groupPosition).getChildlist().get(childPosition).getTitle());
//                intent.setClass(getContext(), ProductListActivity.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);
            }
            return false;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initShopCarNumber();
    }
}

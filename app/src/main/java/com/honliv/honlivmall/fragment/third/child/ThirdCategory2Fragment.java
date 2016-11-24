package com.honliv.honlivmall.fragment.third.child;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.adapter.MyELVAdapter;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.bean.Category;
import com.honliv.honlivmall.contract.ThirdContract;
import com.honliv.honlivmall.model.third.child.ThirdCategoryModel;
import com.honliv.honlivmall.presenter.third.child.ThirdCategory2Presenter;
import com.honliv.honlivmall.presenter.third.child.ThirdCategoryPresenter;
import com.honliv.honlivmall.util.LogUtil;
import com.honliv.honlivmall.util.PromptManager;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/10/26.
 */
public class ThirdCategory2Fragment extends BaseFragment<ThirdCategory2Presenter, ThirdContract.ThirdCategory2Model> implements ThirdContract.ThirdCategory2View {

    String[] groupTitles;
    String[] item_describes;
    TextView categoryEmptyListTv;
    List<Bitmap> bitmaps;
    String[] category3;
    String[][] childTitle;
    @BindView(R.id.category2EList)
    ExpandableListView ELV_category;
    int[] groupLogos;
    int[][] childLogos;
    Category category;//
    List<Category> groupList;//本页面的父分类的
    int sign = -1;//控制列表的展开 在不在顶部

    public static ThirdCategory2Fragment newInstance() {

        Bundle args = new Bundle();

        ThirdCategory2Fragment fragment = new ThirdCategory2Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_third_category2;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        //        category = (Category)this.getIntent().getSerializableExtra("category");
//        groupList = category.getChildlist();
//        ELV_category.setAdapter(new MyELVAdapter());
//
//        ELV_category.setOnChildClickListener(new MyChildListener());
//        ELV_category.setGroupIndicator(null);//设置去掉前面的箭头
//
//        ELV_category.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//            @Override//一次只展开一组
//            public void onGroupExpand(int groupPosition) {
//                // TODO Auto-generated method stub
//                //for (int i = 0; i < groupTitles.length; i++) {
//                for (int i = 0; i < groupList.size(); i++) {
//                    if (groupPosition != i) {
//                        ELV_category.collapseGroup(i);
//                    }
//                }
//            }
//        });
//
//        //只展开一个group的实现方法，置于顶端
//        ELV_category.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//
//            @Override
//            public boolean onGroupClick(ExpandableListView parent, View v,
//                                        int groupPosition, long id) {
//                // TODO Auto-generated method stub
//                if (sign == -1) {
//                    // 展开被选的group
//                    ELV_category.expandGroup(groupPosition);
//                    // 设置被选中的group置于顶端
//                    ELV_category.setSelectedGroup(groupPosition);
//                    sign = groupPosition;
//                } else if (sign == groupPosition) {
//                    ELV_category.collapseGroup(sign);
//                    sign = -1;
//                } else {
//                    ELV_category.collapseGroup(sign);
//                    // 展开被选的group
//                    ELV_category.expandGroup(groupPosition);
//                    // 设置被选中的group置于顶端
//                    ELV_category.setSelectedGroup(groupPosition);
//                    sign = groupPosition;
//                }
//                return true;
//            }
//        });
//
//        // 这里是控制如果列表没有孩子菜单不展开的效果
//        ELV_category.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//            @Override
//            public boolean onGroupClick(ExpandableListView parent,
//                                        View v, int groupPosition, long id) {
//                // TODO Auto-generated method stub
//                //if (childTitle[groupPosition].length==0) {// isEmpty没有
//                if (groupList.get(groupPosition).isHaschild()) {// isEmpty没有
//                    return false;
//                } else {
//                    String titlegroup = groupList.get(groupPosition).getTitle();
//                    PromptManager.showToastTest(getContext(), "已经没有子类，进入商品列表=" + titlegroup);
//
//                    int cId = groupList.get(groupPosition).getId();
//
////                    Intent intent = new Intent(CategoryActivity2.this,ProductListActivity.class);
////                    intent.putExtra("cId", cId);
////                    intent.putExtra("cTitle", titlegroup);
////                    startActivity(intent);
//                    //finish();
//                    return true;
//                }
//            }
//        });
    }

    @Override
    public void showError(String msg) {

    }

//    public class MyELVAdapter extends BaseExpandableListAdapter {
//        public int getGroupCount() {
//            //return groupTitles.length;
//            return groupList.size();
//        }
//
//        public int getChildrenCount(int groupPosition) {
//            //return childTitle[groupPosition].length;
//            return groupList.get(groupPosition).getChildlist().size();
//        }
//
//        public Object getGroup(int groupPosition) {
//            return null;
//        }
//
//        public Object getChild(int groupPosition, int childPosition) {
//            return null;
//        }
//
//        @Override
//        public long getGroupId(int groupPosition) {
//            return groupPosition;
//        }
//
//        public long getChildId(int groupPosition, int childPosition) {
//            return childPosition;
//        }
//
//        //
//        //如果返回true表示子项和组的ID始终表示一个固定的组件对象
//        @Override
//        public boolean hasStableIds() {
//            return false;
//        }
//
//        @Override
//        public View getGroupView(int groupPosition, boolean isExpanded,
//                                 View convertView, ViewGroup parent) {
//
//            //LinearLayout parentLayout=(LinearLayout)View.inflate(getContext(), R.layout.cateory2_parent_item, null);
//            LayoutInflater inflater = LayoutInflater.from(getContext());
//            LinearLayout parentLayout = (LinearLayout) inflater.inflate(R.layout.cateory2_parent_item, parent, false);
//
//            TextView parentTextView = (TextView) parentLayout.findViewById(R.id.parentitem);
//
//            // parentTextView.setText(""+groupTitles[groupPosition]);
//            parentTextView.setText(groupList.get(groupPosition).getTitle());
//
//            ImageView parentImageViw = (ImageView) parentLayout.findViewById(R.id.arrow_right);
//
//            //判断isExpanded就可以控制是按下还是关闭，同时更换图片
//            if (isExpanded) {
//                //  parentImageViw.setBackgroundResource(R.drawable.arrow_down);
//                parentImageViw.setBackgroundResource(R.drawable.arrow);
//            } else {
//                //   parentImageViw.setBackgroundResource(R.drawable.arrow_up);
//                parentImageViw.setBackgroundResource(R.drawable.arrow_right);
//            }
//            return parentLayout;
//        }
//
//        @Override
//        public View getChildView(int groupPosition, int childPosition,
//                                 boolean isLastChild, View convertView, ViewGroup parent) {
//
//            // LinearLayout childrenLayout=(LinearLayout)View.inflate(getContext(), R.layout.cateory2_children_item, null);
//            LayoutInflater inflater = LayoutInflater.from(getContext());
//            LinearLayout childrenLayout = (LinearLayout) inflater.inflate(R.layout.cateory2_children_item, parent, false);
//
//
//            // childrenLayout.setPadding(40, 5, 3, 5) ;
//            // childrenLayout.setPadding(40, 5, 3, 5) ;
//            TextView childrenTextView = (TextView) childrenLayout.findViewById(R.id.childrenitem);
//            // childrenTextView.setText(""+childTitle[groupPosition][childPosition]);
//            childrenTextView.setText(groupList.get(groupPosition).getChildlist().get(childPosition).getTitle());
//
//            return childrenLayout;
//        }
//
//        @Override
//        public boolean isChildSelectable(int groupPosition, int childPosition) {
//            return true;
//        }
//    }

    /**
     * @author wang
     */
//    class MyChildListener implements ExpandableListView.OnChildClickListener {
//
//        @Override
//        public boolean onChildClick(ExpandableListView parent, View v,
//                                    int groupPosition, int childPosition, long id) {
//
//            if (groupList.get(groupPosition).getChildlist().get(childPosition).isHaschild()) {
//                PromptManager.showToastTest(getContext(), "还有子分类。。。");
//
//                int cId = groupList.get(groupPosition).getChildlist().get(childPosition).getId();
//
////                Intent intent = new Intent();
////                intent.putExtra("cId", cId);
////                intent.putExtra("cTitle", groupList.get(groupPosition).getChildlist().get(childPosition).getTitle());
////
////                intent.setClass(getContext(), ProductListActivity.class);
////                startActivity(intent);
//                start(ThirdProductListFragment.newInstance());
//            } else {
//                int cId = groupList.get(groupPosition).getChildlist().get(childPosition).getId();
//                start(ThirdProductListFragment.newInstance());
////                Intent intent = new Intent();
////                intent.putExtra("cId", cId);
////                intent.putExtra("cTitle", groupList.get(groupPosition).getChildlist().get(childPosition).getTitle());
//
////                intent.setClass(getContext(), ProductListActivity.class);
////                startActivity(intent);
////                overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);
//            }
//            return false;
//        }
//    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        initShopCarNumber();
//    }

}

package com.honliv.honlivmall.listener;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.honliv.honlivmall.bean.Category;
import com.honliv.honlivmall.fragment.third.child.ThirdCategoryFragment;

import java.util.ArrayList;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Rodin on 2016/11/11.
 */
public class MyCategoryItemClickListener implements AdapterView.OnItemClickListener {
    private static final String TAG = "MyCategoryItemClickListener";
    private final SupportFragment mContext;

    public void setData(ArrayList<Category> categorys) {
        this.categorys = categorys;
    }

    private ArrayList<Category> categorys=new ArrayList<>();

    public MyCategoryItemClickListener(SupportFragment mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        Category category = categorys.get(position);
//        Intent intent;
        if (category.isHaschild()) {
//            intent = new Intent(mContext, CategoryActivity2.class);
//            intent.putExtra("category", category);
            Bundle data=new Bundle();
            data.putSerializable("category", category);
            mContext.start(ThirdCategoryFragment.newInstance(data));
        } else {
//            mContext.start(ThirdCategoryFragment.newInstance(data));
//            intent = new Intent(mContext, ProductListActivity.class);
        }
//        mContext.startActivity(intent);
    }
}


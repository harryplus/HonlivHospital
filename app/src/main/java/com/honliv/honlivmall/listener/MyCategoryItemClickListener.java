package com.honliv.honlivmall.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.honliv.honlivmall.bean.Category;

import java.util.ArrayList;

/**
 * Created by Rodin on 2016/11/11.
 */
public class MyCategoryItemClickListener implements AdapterView.OnItemClickListener {
    private static final String TAG = "MyCategoryItemClickListener";
    private final Context mContext;
    private final ArrayList<Category> categorys;

    public MyCategoryItemClickListener(Context mContext, ArrayList<Category> categorys) {
        this.mContext = mContext;
        this.categorys = categorys;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Category category = categorys.get(position);
        Intent intent;
        if (category.isHaschild()) {
//            intent = new Intent(mContext, CategoryActivity2.class);
//            intent.putExtra("category", category);
        } else {
//            intent = new Intent(mContext, ProductListActivity.class);
        }
//        mContext.startActivity(intent);
    }
}


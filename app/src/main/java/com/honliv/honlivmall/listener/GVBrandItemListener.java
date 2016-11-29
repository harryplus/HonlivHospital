package com.honliv.honlivmall.listener;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.bean.HomeBrand;
import com.honliv.honlivmall.fragment.first.child.FirstBrandListFragment;
import com.honliv.honlivmall.util.BuilderTools;
import com.honliv.honlivmall.util.RxManager;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Rodin on 2016/11/1.
 */
public class GVBrandItemListener implements AdapterView.OnItemClickListener {

    private final List<HomeBrand> data;
    private final boolean flag;
    private final SupportFragment fragment;
    private Context mContext;


//    public GVBrandItemListener(Context mContext, List<HomeBrand> data, boolean flag) {
//        this.mContext = mContext;
//        this.data = data;
//        this.flag = flag;
//    }

    public GVBrandItemListener(Context context, SupportFragment fragment,  ArrayList<HomeBrand> brandProduct, boolean flag) {
        this.mContext = context;
        this.data = brandProduct;
        this.flag = flag;
        this.fragment=fragment;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        if (GloableParams.USERID < 0 && flag) {
            //弹出登陆按钮
            BuilderTools.showLoginDialog(mContext,fragment,"需要登录");
        } else {
            if (data.size() > 0) {
                position = position % data.size();
            }
            HomeBrand selProduct = data.get(position);
            Bundle dataBun=new Bundle();
            dataBun.putInt("brandid", selProduct.getId());
            fragment.start(FirstBrandListFragment.newInstance(dataBun));
//            Intent intent = new Intent();
//            intent.setClass(mContext, BrandListActivity.class);
//            intent.putExtra("brandid", selProduct.getId());
//            mContext.startActivity(intent);
        }
    }
}

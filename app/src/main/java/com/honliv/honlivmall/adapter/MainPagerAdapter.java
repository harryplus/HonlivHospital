package com.honliv.honlivmall.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.honliv.honlivmall.ConstantValue;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.bean.HomeBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rodin on 2016/10/25.
 * 首页滑动页面适配器
 */
public class MainPagerAdapter extends PagerAdapter {
    private static final String TAG = "MainPagerAdapter";
    private final List<HomeBanner> viewpaperData;
    private final Context mContext;
    private List<View> viewList;//view数组

    public MainPagerAdapter(Context mContext) {
        this.viewpaperData = new ArrayList<>();
        this.mContext = mContext;
        viewList = new ArrayList<>();
        for (HomeBanner bean : viewpaperData) {
            View view = View.inflate(mContext, R.layout.viewpaper_item, null);
            viewList.add(view);
        }
    }


    @Override
    public int getCount() {
        Log.i(TAG, "viewList.size()--" + viewList.size());
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        // TODO Auto-generated method stub
        container.removeView(viewList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // TODO Auto-generated method stub
        View view = viewList.get(position);
        HomeBanner homeBanner = viewpaperData.get(position);
        ImageView imageview = (ImageView) view.findViewById(R.id.imageview);
        TextView describe = (TextView) view.findViewById(R.id.describe);
        Glide.with(mContext).load(ConstantValue.IMAGE_URL + homeBanner.getPic()).crossFade().placeholder(R.mipmap.picture_no).into(imageview);
        describe.setText(homeBanner.getTitle());
        container.addView(view);
        return view;
    }

    public void addAll(List<HomeBanner> home_banner) {
        viewList.clear();
        this.viewpaperData.addAll(home_banner);
        for (HomeBanner bean : viewpaperData) {
            View view = View.inflate(mContext, R.layout.viewpaper_item, null);
            viewList.add(view);
        }
    }
}

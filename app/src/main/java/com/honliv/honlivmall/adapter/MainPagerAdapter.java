package com.honliv.honlivmall.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.honliv.honlivmall.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rodin on 2016/10/25.
 * 首页滑动页面适配器
 */
public class MainPagerAdapter extends PagerAdapter {
    private static final String TAG = "MainPagerAdapter";
    private final List<String> viewpaperData;
    private final Context mContext;
    private List<View> viewList;//view数组
    ControllerListener listener = new BaseControllerListener() {
    };

    public MainPagerAdapter(Context mContext, List<String> viewpaperData) {
        this.viewpaperData = viewpaperData;
        this.mContext = mContext;
        viewList = new ArrayList<>();
        for (String imageUri : viewpaperData) {
            View view = View.inflate(mContext, R.layout.viewpaper_item, null);
            viewList.add(view);
        }
    }


    @Override
    public int getCount() {
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
        SimpleDraweeView imageView = (SimpleDraweeView) view.findViewById(R.id.imageview);
        String imageUri = viewpaperData.get(position);

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(imageUri)
                .setTapToRetryEnabled(true)
                .setOldController(imageView.getController())
                .setControllerListener(listener)
                .build();

        imageView.setController(controller);

//        Log.i(TAG, imageUri);
//        imageView.setImageURI(imageUri);
        container.addView(view);
        return view;
    }
}
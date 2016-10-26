package com.honliv.honlivhospital.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.honliv.honlivhospital.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.List;

/**
 * Created by Rodin on 2016/10/25.
 * 首页滑动页面适配器
 */
public class MainPagerAdapter extends PagerAdapter {

    private final List<String> viewpaperData;
    private final Context mContext;
    private List<View> viewList;//view数组
    ImageLoader imageLoader = ImageLoader.getInstance(); // Get singleton instance

    SimpleImageLoadingListener imageListener = new SimpleImageLoadingListener() {
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            // Do whatever you want with Bitmap
            view.setBackground(new BitmapDrawable(loadedImage));
        }

        @Override
        public void onLoadingStarted(String imageUri, View view) {
            view.setBackgroundResource(R.mipmap.picture_no);
        }

        @Override
        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
            view.setBackgroundResource(R.mipmap.load_fail);
        }
    };

    public MainPagerAdapter(Context mContext, List<String> viewpaperData) {
        this.viewpaperData = viewpaperData;
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        return viewpaperData.size();
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
        String imageUri = viewpaperData.get(position);
        View view = View.inflate(mContext, R.layout.viewpaper_item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);

        imageLoader.displayImage(imageUri, imageView);

        imageLoader.loadImage(imageUri, imageListener);

        viewList.add(view);
        container.addView(viewList.get(position));
        return viewList.get(position);
    }


}

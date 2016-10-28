package com.honliv.honlivhospital.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.honliv.honlivhospital.R;
import com.honliv.honlivhospital.application.MyApplication;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

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
        viewList = new ArrayList<>();
        for (String imageUri : viewpaperData) {
//            String imageUri = viewpaperData.get(position);
//            ImageView   mIcon = new ImageView(mContext);
//            int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 64, mContext.getResources().getDisplayMetrics());
//            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(size, size);
//            params.gravity = Gravity.CENTER;
//            mIcon.setImageResource(icon);
//            mIcon.setLayoutParams(params);
//            mIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.tab_unselect));
//            imageLoader.displayImage(imageUri, mIcon);
//            imageLoader.loadImage(imageUri, imageListener);

            View view = View.inflate(mContext, R.layout.viewpaper_item, null);


            viewList.add(view);
        }
    }


    @Override
    public int getCount() {
        Log.i(TAG, viewList.size() + "");
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
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        String imageUri = viewpaperData.get(position);
//        imageLoader.displayImage(imageUri, imageView);
//
//        imageLoader.loadImage(imageUri, imageListener);
        MyApplication.imageLoader.displayImage(imageUri, imageView);
// Load image, decode it to Bitmap and return Bitmap to callback
        MyApplication.imageLoader.loadImage(imageUri, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                // Do whatever you want with Bitmap
                Log.i(TAG, imageUri);
            }
        });

        container.addView(view);
        return view;
    }
}

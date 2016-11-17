package com.honliv.honlivmall.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.listener.MyItemListener;
import com.honliv.honlivmall.listener.ProductItemListener;
import com.honliv.honlivmall.util.ImageOptionsUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

/**
 * Created by Rodin on 2016/11/10.
 */
public class ProductViewPagerAdapter extends PagerAdapter {

    private final Context mContext;
    private final String[] imagevpes;
    private final ImageLoader imageLoader;

    public ProductViewPagerAdapter(Context mContext, String[] imagevpes, ImageLoader imageLoader) {
        this.mContext = mContext;
        this.imagevpes = imagevpes;
        this.imageLoader = imageLoader;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        View imageLayout = View.inflate(mContext, R.layout.item_pager_image, null);
        ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image);
        final ProgressBar spinner = (ProgressBar) imageLayout.findViewById(R.id.loading);
        String tempUrl = imagevpes[position % imagevpes.length].replace("{0}", "T300X390_");

        imageView.setOnClickListener(new MyItemListener(position));

        imageLoader.displayImage(tempUrl, imageView, ImageOptionsUtils.getProductDetailOption(), new SimpleImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                spinner.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                spinner.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                spinner.setVisibility(View.GONE);        // 不显示圆形进度条
            }
        });
        container.addView(imageLayout);
        ProductItemListener listener=new ProductItemListener(mContext,position,imagevpes);
        imageLayout.setOnClickListener(listener);
        return imageLayout;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

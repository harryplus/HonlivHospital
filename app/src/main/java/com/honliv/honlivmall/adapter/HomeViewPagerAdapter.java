package com.honliv.honlivmall.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.honliv.honlivmall.ConstantValue;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.bean.HomeBanner;
import com.honliv.honlivmall.listener.MyItemListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

import java.util.List;

/**
 * Created by Rodin on 2016/11/1.
 */
public class HomeViewPagerAdapter extends PagerAdapter {
    private DisplayImageOptions options;
    private List<HomeBanner> homeBanners;
    private Context mContext;
    private ImageLoader imageLoader;
    private LayoutInflater inflater;

    public HomeViewPagerAdapter(Context mContext, ImageLoader imageLoader, List<HomeBanner> homeBanners, DisplayImageOptions options) {
        this.mContext = mContext;
        this.imageLoader = imageLoader;
        this.homeBanners = homeBanners;
        this.options = options;
        inflater = ((Activity) mContext).getLayoutInflater();
    }

    @Override
    public int getCount() {
        // return images.size();
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        View imageLayout = inflater.inflate(R.layout.item_homepager_image, container, false);
        ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image);
        imageView.setOnClickListener(new MyItemListener(position % homeBanners.size()));
        final ProgressBar spinner = (ProgressBar) imageLayout.findViewById(R.id.loading);
        imageLoader.displayImage(ConstantValue.IMAGE_URL + homeBanners.get(position % homeBanners.size()).getPic(), imageView, options, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                spinner.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                String message = null;
                switch (failReason.getType()) {        // 获取图片失败类型
                    case IO_ERROR:                // 文件I/O错误
                        message = "Input/Output error";
                        break;
                    case DECODING_ERROR:        // 解码错误
                        message = "Image can't be decoded";
                        break;
                    case NETWORK_DENIED:        // 网络延迟
                        message = "Downloads are denied";
                        break;
                    case OUT_OF_MEMORY:            // 内存不足
                        message = "Out Of Memory error";
                        break;
                    case UNKNOWN:                // 原因不明
                        message = "Unknown error";
                        break;
                }
                //PromptManager.showToast(getApplicationContext(), message);
                spinner.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                spinner.setVisibility(View.GONE);        // 不显示圆形进度条
            }
        });
        try {
            if (imageLayout.getParent() == null) {
                //container.addView(view);//这里可能会报一个错。must call removeView().on the child....first
                container.addView(imageLayout);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageLayout;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        //container.removeView(images.get(position % images.size()));// 移除一个
        container.removeView((View) object);
    }
}

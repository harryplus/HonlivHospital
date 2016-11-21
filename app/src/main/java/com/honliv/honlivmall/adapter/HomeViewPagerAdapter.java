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

import java.util.List;

/**
 * Created by Rodin on 2016/11/1.
 */
public class HomeViewPagerAdapter extends PagerAdapter {
    private List<HomeBanner> homeBanners;
    private Context mContext;
    private LayoutInflater inflater;

    public HomeViewPagerAdapter(Context mContext, List<HomeBanner> homeBanners) {
        this.mContext = mContext;
        this.homeBanners = homeBanners;
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

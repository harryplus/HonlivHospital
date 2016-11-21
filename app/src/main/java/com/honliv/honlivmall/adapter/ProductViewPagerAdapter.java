package com.honliv.honlivmall.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.listener.MyItemListener;
import com.honliv.honlivmall.listener.ProductItemListener;

/**
 * Created by Rodin on 2016/11/10.
 */
public class ProductViewPagerAdapter extends PagerAdapter {

    private final Context mContext;
    private final String[] imagevpes;

    public ProductViewPagerAdapter(Context mContext, String[] imagevpes) {
        this.mContext = mContext;
        this.imagevpes = imagevpes;
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
        container.addView(imageLayout);
        ProductItemListener listener = new ProductItemListener(mContext, position, imagevpes);
        imageLayout.setOnClickListener(listener);
        return imageLayout;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

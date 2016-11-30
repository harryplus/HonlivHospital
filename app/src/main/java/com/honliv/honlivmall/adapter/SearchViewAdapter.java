package com.honliv.honlivmall.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Rodin on 2016/11/30.
 */
public class SearchViewAdapter extends PagerAdapter {
    private final List<View> pagers;

    public SearchViewAdapter(List<View> pagers) {
        this.pagers=pagers;
    }

    public int getCount() {
        return 2;
    }

    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        View view = pagers.get(position);

        container.addView(view);

        return view;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(pagers.get(position));
    }
}

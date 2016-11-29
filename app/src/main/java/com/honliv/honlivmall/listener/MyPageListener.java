package com.honliv.honlivmall.listener;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.application.MyApplication;

/**
 * Created by Rodin on 2016/11/19.
 */
public class MyPageListener implements ViewPager.OnPageChangeListener {

    private final TextView hotTitle;
    private final TextView historyTitle;
    private final TextView searchDelTV;
    private final ImageView selete;
    private final String[] historyNames;
    private int currentPage = 0;

    public MyPageListener(TextView hotTitle, TextView historyTitle, TextView searchDelTV, ImageView selete, String[] historyNames) {
        this.hotTitle = hotTitle;
        this.historyTitle = historyTitle;
        this.searchDelTV = searchDelTV;
        this.selete = selete;
        this.historyNames = historyNames;
    }

    public void onPageScrollStateChanged(int position) {
    }

    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }

    public void onPageSelected(int position) {
        hotTitle.setTextColor(Color.BLACK);
        historyTitle.setTextColor(Color.BLACK);

        switch (position) {
            case 0:
                hotTitle.setTextColor(Color.RED);
                hotTitle.setBackgroundResource(R.drawable.android_horizontal_button_4_selected);
                historyTitle.setBackgroundResource(R.drawable.android_horizontal_button_4);
                searchDelTV.setVisibility(View.GONE);
                break;
            case 1:
                historyTitle.setTextColor(Color.RED);
                historyTitle.setBackgroundResource(R.drawable.android_horizontal_button_4_selected);
                hotTitle.setBackgroundResource(R.drawable.android_horizontal_button_4);
                if (historyNames != null && historyNames.length > 0) {
                    searchDelTV.setVisibility(View.VISIBLE);
                }
                break;
        }

        TranslateAnimation animation = new TranslateAnimation(currentPage * MyApplication.getInstance().SCREEN_WIDTH / 2, position * MyApplication.getInstance().SCREEN_WIDTH / 2, 0, 0);
        animation.setFillAfter(true);
        animation.setDuration(300);

        selete.startAnimation(animation);
        currentPage = position;
    }
}

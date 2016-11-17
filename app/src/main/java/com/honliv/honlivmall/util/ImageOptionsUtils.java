package com.honliv.honlivmall.util;

import android.graphics.Bitmap;

import com.honliv.honlivmall.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

/**
 * Created by Rodin on 2016/8/28.
 */
public class ImageOptionsUtils {
    private static DisplayImageOptions CategoryItemOption = null;
    private static DisplayImageOptions HomeItemOption = null;
    private static DisplayImageOptions HomeGridOption = null;
    private static DisplayImageOptions myOrderOptions = null;
    private static DisplayImageOptions marketOption = null;
    private static DisplayImageOptions imageOption;

    public static DisplayImageOptions getCategoryItemOption() {
        if (CategoryItemOption == null) {
            // 使用DisplayImageOptions.Builder()创建DisplayImageOptions
            CategoryItemOption = new DisplayImageOptions.Builder()
                    //.showStubImage(R.drawable.ic_stub)			// 设置图片下载期间显示的图片
                    .showImageForEmptyUri(R.drawable.category_none)    // 设置图片Uri为空或是错误的时候显示的图片
                    .showImageOnFail(R.drawable.category_none)        // 设置图片加载或解码过程中发生错误显示的图片
                    .cacheInMemory(true)                        // 设置下载的图片是否缓存在内存中
                    .cacheOnDisc(true)                            // 设置下载的图片是否缓存在SD卡中
                    .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//按目标比例缩小
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    //.displayer(new RoundedBitmapDisplayer(20))	// 设置成圆角图片
                    .displayer(new FadeInBitmapDisplayer(1))    //图片渐入淡入的时间 。原始是200，太慢
                    .build();
        }
        return CategoryItemOption;
    }

    public static DisplayImageOptions getHomeItemOption() {
        if (HomeItemOption == null) {
            HomeItemOption = new DisplayImageOptions.Builder()
                    .showImageForEmptyUri(R.drawable.ic_empty)
                    .showImageOnFail(R.drawable.ic_error)
                    .resetViewBeforeLoading(true)
                    .cacheInMemory(true)     //缓存到内存
                    .cacheOnDisc(true)         //缓存到本地
                    .imageScaleType(ImageScaleType.EXACTLY)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .displayer(new FadeInBitmapDisplayer(1))//图片渐入淡入的时间
                    .build();
        }
        return HomeItemOption;
    }

    public static DisplayImageOptions getHomeGridOption() {
        if (HomeGridOption == null) {
            HomeGridOption = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.drawable.ic_onloading)            // 设置图片下载期间显示的图片
                    .showImageForEmptyUri(R.drawable.ic_empty)    // 设置图片Uri为空或是错误的时候显示的图片
                    .showImageOnFail(R.drawable.ic_error)        // 设置图片加载或解码过程中发生错误显示的图片
                    .cacheInMemory(true)                        // 设置下载的图片是否缓存在内存中
                    .cacheOnDisc(true)                            // 设置下载的图片是否缓存在SD卡中
                    .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//按目标比例缩小
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    //.displayer(new RoundedBitmapDisplayer(20))	// 设置成圆角图片
                    .displayer(new FadeInBitmapDisplayer(1))    //图片渐入淡入的时间
                    .build();
        }
        return HomeGridOption;
    }

    public static DisplayImageOptions getMyOrderOptions() {
        if (myOrderOptions == null) {
            // 使用DisplayImageOptions.Builder()创建DisplayImageOptions
            myOrderOptions = new DisplayImageOptions.Builder()
                    //.showStubImage(R.drawable.ic_stub)			// 设置图片下载期间显示的图片
                    .showImageForEmptyUri(R.drawable.ic_error)    // 设置图片Uri为空或是错误的时候显示的图片
                    .showImageOnFail(R.drawable.ic_error)        // 设置图片加载或解码过程中发生错误显示的图片
                    .cacheInMemory(true)                        // 设置下载的图片是否缓存在内存中
                    .cacheOnDisc(true)                            // 设置下载的图片是否缓存在SD卡中
                    .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//按目标比例缩小
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    //.displayer(new RoundedBitmapDisplayer(20))  // 设置成圆角图片
                    .displayer(new FadeInBitmapDisplayer(1))    //图片渐入淡入的时间
                    .build();
        }
        return myOrderOptions;
    }

    public static DisplayImageOptions getMarketOption() {
        marketOption = new DisplayImageOptions.Builder()
                //.showStubImage(R.drawable.ic_stub)			// 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.drawable.ic_empty)    // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.ic_error)        // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true)                        // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)                            // 设置下载的图片是否缓存在SD卡中
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//按目标比例缩小
                .bitmapConfig(Bitmap.Config.RGB_565)
                //.displayer(new RoundedBitmapDisplayer(20))	// 设置成圆角图片
                .displayer(new FadeInBitmapDisplayer(1))    //图片渐入淡入的时间
                .build();
        return marketOption;
    }

    public static DisplayImageOptions getProductDetailOption() {
        imageOption = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.ic_error)
                .resetViewBeforeLoading(true)
                .cacheOnDisc(true)
                .cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)//按比例缩小
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new FadeInBitmapDisplayer(300))
                .build();
        return imageOption;
    }
}

package com.honliv.honlivmall.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;


import java.util.ArrayList;

/**
 * Created by Rodin on 2016/11/10.
 */
public class ProductItemListener implements View.OnClickListener {
    private final String[] imagevpes;
    private final Context mContext;
    private int position;

    public ProductItemListener(Context mContext, int position, String[] imagevpes) {
        super();
        this.mContext = mContext;
        this.position = position;
        this.imagevpes = imagevpes;
    }

    @Override
    public void onClick(View v) {
        ArrayList<String> productImgs = new ArrayList<String>();//传递到下一个界面的图片集合

        String tempUrl = "";
        for (int i = 0; i < imagevpes.length; i++) {
            tempUrl = imagevpes[i].replace("{0}", "T300X390_");
            productImgs.add(tempUrl);//添加进来，方便传送到下一个界面
        }
//        Intent intent = new Intent(mContext, ProductImageList.class);
//        intent.putExtra("images", productImgs);
//        intent.putExtra("position", position);
//        mContext.startActivity(intent);
    }
}

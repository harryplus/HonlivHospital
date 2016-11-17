package com.honliv.honlivmall.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.bean.Category;
import com.honliv.honlivmall.listener.AnimateFirstDisplayListener;
import com.honliv.honlivmall.util.ImageOptionsUtils;
import com.honliv.honlivmall.util.Utils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

import java.util.ArrayList;

/**
 * Created by Rodin on 2016/11/11.
 */
public class MyCategoryAdapter extends BaseAdapter {
    private static final String TAG = "MyCategoryAdapter";
    private final Context mContext;
    private final ImageLoader imageLoader;
    private final ArrayList<Category> categorys;
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    public MyCategoryAdapter(Context mContext, ArrayList<Category> categorys, ImageLoader imageLoader) {
        this.mContext = mContext;
        this.categorys = categorys;
        this.imageLoader = imageLoader;
    }

    @Override
    public int getCount() {
        return categorys.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            view = View.inflate(mContext, R.layout.category1_item, null);

            holder.imgIcon = (ImageView) view.findViewById(R.id.imgIcon);
            holder.textContent = (TextView) view
                    .findViewById(R.id.textContent);

            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        holder.textContent.setText(categorys.get(position).getTitle());
        String picUrl = categorys.get(position).getPic();
        Log.i(TAG,picUrl);
        imageLoader.displayImage(Utils.checkImagUrl(picUrl), holder.imgIcon, ImageOptionsUtils.getCategoryItemOption(), animateFirstListener);
        return view;
    }

    private class ViewHolder {
        TextView textContent;
        //		TextView item_describe;
        ImageView imgIcon;
    }

}


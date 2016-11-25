package com.honliv.honlivmall.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.bean.Category;
import com.honliv.honlivmall.util.Utils;

import java.util.ArrayList;

/**
 * Created by Rodin on 2016/11/11.
 */
public class MyCategoryAdapter extends BaseAdapter {
    private static final String TAG = "MyCategoryAdapter";
    private final Context mContext;
    private final ArrayList<Category> categorys;

    public MyCategoryAdapter(Context mContext, ArrayList<Category> categorys) {
        this.mContext = mContext;
        this.categorys = categorys;
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
        Glide.with(mContext).load(Utils.checkImagUrl(Utils.checkImagUrl(categorys.get(position).getPic()))).crossFade().placeholder(R.mipmap.picture_no_square).into(holder.imgIcon);

        return view;
    }

    private class ViewHolder {
        TextView textContent;
        //		TextView item_describe;
        ImageView imgIcon;
    }

}


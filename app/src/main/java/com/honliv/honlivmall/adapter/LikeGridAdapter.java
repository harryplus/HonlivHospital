package com.honliv.honlivmall.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.util.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rodin on 2016/8/10.
 */
public class LikeGridAdapter extends BaseAdapter {
    private static final String TAG = "LikeGridAdapter";
    private final Context mContext;
    private List<Product> data = new ArrayList<Product>();

    public LikeGridAdapter(Context mContext,ArrayList<Product> likeProduct) {
        this.data = likeProduct;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return Integer.MAX_VALUE;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View view = null;
        if (data.size() != 0)
            position = position % data.size();
        GirdViewHolder holder = null;
        if (convertView == null) {
            holder = new GirdViewHolder();
            view = View.inflate(mContext, R.layout.home_griditem, null);//=====================
            holder.productImg = (ImageView) view.findViewById(R.id.searchresult_product_img);
            holder.productPriceTV = (TextView) view.findViewById(R.id.searchresult_product_price_text);

            holder.name = (TextView) view.findViewById(R.id.name);

            view.setTag(holder);
        } else {
            view = convertView;
            holder = (GirdViewHolder) view.getTag();
        }
        if (data.size() > 0) {
            holder.productPriceTV.setText("￥" + (data.get(position)).getSaleprice());//特价专区现价

            holder.name.setText((data.get(position)).getName());
            Glide.with(mContext).load(Utils.checkImagUrl(data.get(position).getPic() + "")).crossFade().placeholder(R.mipmap.picture_no).into(holder.productImg);

//            imageLoader.displayImage(Utils.checkImagUrl(data.get(position).getPic() + ""), holder.productImg, options, animateFirstListener);
        }
        return view;
    }

    public void initData(ArrayList<Product> data) {
        this.data = data;
    }

    public void addAll(List<Product> data) {
        this.data.addAll(data);
    }

    private class GirdViewHolder {
        public TextView name;
        ImageView productImg;//商品图标
        TextView productPriceTV;//商品价
    }
}

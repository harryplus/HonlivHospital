package com.honliv.honlivmall.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
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
public class LimitGridAdapter extends BaseAdapter {
    private static final String TAG = "LimitGridAdapter";
    private final Context mContext;
    private List<Product> data = new ArrayList<Product>();

    public LimitGridAdapter(Context mContext) {
        this.data = new ArrayList<>();
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
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
            view = View.inflate(mContext, R.layout.home_griditem_limit, null);//=====================
            holder.productImg = (ImageView) view.findViewById(R.id.searchresult_product_img);
            holder.productPriceTV = (TextView) view.findViewById(R.id.searchresult_product_price_text);

            holder.productPreTV = (TextView) view.findViewById(R.id.searchresult_product_pre_price_text);
            holder.name = (TextView) view.findViewById(R.id.name);
            holder.limitTime = (TextView) view.findViewById(R.id.limit_time);
            holder.limitTime.setTag(position);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (GirdViewHolder) view.getTag();
        }
        if (data.size() > 0) {
            holder.productPriceTV.setText("￥" + (data.get(position)).getSaleprice());
            holder.productPreTV.setText("￥" + (data.get(position)).getMarketprice());

            holder.name.setText((data.get(position)).getName());
            holder.productPreTV.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线
            Log.i(TAG,"-----"+Utils.checkImagUrl(data.get(position).getPic() + ""));
            Glide.with(mContext).load(Utils.checkImagUrl(data.get(position).getPic() + "")).crossFade().placeholder(R.mipmap.picture_no).into(holder.productImg);
        }
        return view;
    }

    public void initData(ArrayList<Product> data) {
        this.data = data;
    }

    public void addAll(List<Product> result) {
        this.data.addAll(result);
        Log.i(TAG," this.data----"+ this.data.size());
    }

    private class GirdViewHolder {
        public TextView name;
        public TextView limitTime;
        ImageView productImg;//商品图标
        TextView productPriceTV;//商品价
        TextView productPreTV;//商品价
    }
}

package com.honliv.honlivmall.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.listener.AnimateFirstDisplayListener;
import com.honliv.honlivmall.util.ImageOptionsUtils;
import com.honliv.honlivmall.util.Utils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rodin on 2016/8/10.
 */
public class LikeGridAdapter extends BaseAdapter {
    private static final String TAG = "LikeGridAdapter";
    private final Context mContext;
    private final DisplayImageOptions options;
    //抢购标志位
    private final boolean isMaketing;
    //喜欢商品标志位
    private final boolean isLike;
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    private List<Product> data = new ArrayList<Product>();
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    public LikeGridAdapter(List<Product> data, Context mContext, boolean isMaketing, boolean isLike) {
        if (data != null)
            this.data = data;
        this.mContext = mContext;

        this.isMaketing = isMaketing;
        this.isLike = isLike;
        options = ImageOptionsUtils.getHomeGridOption();
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
            imageLoader.displayImage(Utils.checkImagUrl(data.get(position).getPic() + ""), holder.productImg, options, animateFirstListener);
        }
        return view;
    }

    public void initData(ArrayList<Product> data) {
        this.data = data;
    }

    static class GirdViewHolder {
        public TextView name;
        ImageView productImg;//商品图标
        TextView productPriceTV;//商品价
    }
}

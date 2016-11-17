package com.honliv.honlivmall.adapter;

import android.content.Context;
import android.util.Log;
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
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

import java.util.List;

/**
 * Created by Rodin on 2016/9/11.
 */
public class MyOrderProductLVAdapter extends BaseAdapter {

    private static final String TAG = "MyOrderProductLVAdapter";
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
    private ImageLoader imageLoader;
    private List<Product> currentProductList;
    private Context mContext;

    public MyOrderProductLVAdapter(Context mContext, ImageLoader imageLoader, List<Product> currentProductList) {
        this.mContext = mContext;
        this.imageLoader = imageLoader;
        this.currentProductList = currentProductList;
    }

    public int getCount() {
        Log.i(TAG, currentProductList.size() + "--size");
        return currentProductList.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = null;
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            view = View.inflate(mContext, R.layout.payment_list_item, null);
            holder.productImg = (ImageView) view.findViewById(R.id.item_productImage_IV);
            holder.productTitleTV = (TextView) view.findViewById(R.id.payment_item_prodName_text);
            holder.productPriceTV = (TextView) view.findViewById(R.id.payment_item_prodPrice_text);
            holder.productNumTV = (TextView) view.findViewById(R.id.productNumValue);
            holder.standardTV = (TextView) view.findViewById(R.id.item_prodstanard_text);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }

        holder.productTitleTV.setText((currentProductList.get(position)).getName());
        Log.i(TAG, currentProductList.get(position).getName());
        holder.productPriceTV.setText("￥" + currentProductList.get(position).getSaleprice());
        holder.productNumTV.setText(currentProductList.get(position).getNumber() + "");
        holder.standardTV.setVisibility(View.VISIBLE);

        if (currentProductList.get(position).getShopCarColorKey() != null) {
            holder.standardTV.setText(currentProductList.get(position).getShopCarColorKey());
        } else {
            holder.standardTV.setText("规格: 无");
        }
        imageLoader.displayImage(Utils.checkImagUrl(currentProductList.get(position).getPic() + ""), holder.productImg, ImageOptionsUtils.getMyOrderOptions(), animateFirstListener);
        return view;
    }

    private class ViewHolder {
        ImageView productImg;//商品图标
        TextView productTitleTV;//商品标题
        TextView standardTV;//规格
        TextView productNumTV;//数量
        TextView productPriceTV;//商品现价
//		TextView productOldPriceTV;//商品原价
//		EditText productNumET;//商品数量
    }
}

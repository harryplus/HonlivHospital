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
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.util.Utils;

import java.util.List;

/**
 * Created by Rodin on 2016/9/11.
 */
public class MyOrderProductLVAdapter extends BaseAdapter {

    private static final String TAG = "MyOrderProductLVAdapter";
    private List<Product> currentProductList;
    private Context mContext;

    public MyOrderProductLVAdapter(Context mContext, List<Product> currentProductList) {
        this.mContext = mContext;
        this.currentProductList = currentProductList;
    }

    public int getCount() {
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
        holder.productPriceTV.setText("￥" + currentProductList.get(position).getSaleprice());
        holder.productNumTV.setText(currentProductList.get(position).getNumber() + "");
        holder.standardTV.setVisibility(View.VISIBLE);

        if (currentProductList.get(position).getShopCarColorKey() != null) {
            holder.standardTV.setText(currentProductList.get(position).getShopCarColorKey());
        } else {
            holder.standardTV.setText("规格: 无");
        }
        Glide.with(mContext).load(Utils.checkImagUrl(Utils.checkImagUrl(currentProductList.get(position).getPic() + ""))).crossFade().placeholder(R.mipmap.picture_no).into(holder.productImg);
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

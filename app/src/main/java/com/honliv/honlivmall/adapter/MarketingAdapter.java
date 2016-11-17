package com.honliv.honlivmall.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.listener.AnimateFirstDisplayListener;
import com.honliv.honlivmall.util.BuilderTools;
import com.honliv.honlivmall.util.ImageOptionsUtils;
import com.honliv.honlivmall.util.Utils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

import java.util.List;

/**
 * Created by Rodin on 2016/11/10.
 */
public class MarketingAdapter extends BaseAdapter {
    private static final String TAG = "MarketingAdapter";
    private final List<Product> products;
    private final Context mContext;
    private final ImageLoader imageLoader;
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    public MarketingAdapter(Context mContext, List<Product> products, ImageLoader imageLoader) {
        this.mContext = mContext;
        this.products = products;
        this.imageLoader = imageLoader;
    }

    @Override
    public int getCount() {
        return products.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = null;
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            view = View.inflate(mContext,
                    R.layout.marketing_list_item, null);
            holder.imageLog = (ImageView) view
                    .findViewById(R.id.marketing_product_image);
            holder.title = (TextView) view
                    .findViewById(R.id.marketing_product_title);
            holder.time = (TextView) view
                    .findViewById(R.id.marketing_product_time);

            holder.time.setTag(position); // 3.setTag的第一个用途

            holder.productPriceTV = (TextView) view.findViewById(R.id.marketing_product_price);
            holder.productOldPriceTV = (TextView) view.findViewById(R.id.prodoldPrice_text);
            holder.selectProduct = (TextView) view
                    .findViewById(R.id.marketing_product_select);

            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }

        final Product product = products.get(position);
        holder.title.setText(product.getName() + "");
        holder.productOldPriceTV.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线
        holder.productOldPriceTV.getPaint().setAntiAlias(true);// 抗锯齿
        holder.productPriceTV.setText("￥" + product.getSaleprice());
        holder.productOldPriceTV.setText("￥" + product.getMarketprice());

        holder.selectProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GloableParams.USERID < 0) {
                    //弹出登陆按钮
                    BuilderTools.showLoginDialog(mContext, "抢购需要登录");
                } else {
                    Intent intent = new Intent();
//                    intent.setClass(mContext, ProductDetailActivity.class);
                    intent.putExtra("privilegeProduct", product);
                    intent.putExtra("pId", product.getId());
                    mContext.startActivity(intent);
                }
            }
        });
        DisplayImageOptions options = ImageOptionsUtils.getMarketOption();
        String url = Utils.checkImagUrl(product.getPic() + "");
        imageLoader.displayImage(url, holder.imageLog, options, animateFirstListener);

        return view;
    }

    private class ViewHolder {
        ImageView imageLog;
        TextView title;
        TextView productPriceTV;
        TextView productOldPriceTV;//原价
        TextView time;//倒记时设置
        TextView selectProduct;
    }
}

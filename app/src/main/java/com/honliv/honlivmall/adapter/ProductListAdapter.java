package com.honliv.honlivmall.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.honliv.honlivmall.ConstantValue;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.listener.AnimateFirstDisplayListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

import java.util.List;

/**
 * Created by Rodin on 2016/8/17.
 */
public class ProductListAdapter extends BaseAdapter {
    private static final String TAG = "ProductListAdapter";
    private final Context mContext;
    private final List<Product> currentProductList;
    private final DisplayImageOptions options;
    private final ImageLoader imageLoader;
    private final boolean isBargain;
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    public ProductListAdapter(Context mContext, List<Product> currentProductList, ImageLoader imageLoader, DisplayImageOptions options, boolean isBargain) {
        this.mContext = mContext;
        this.currentProductList = currentProductList;
        this.imageLoader = imageLoader;
        this.options = options;
        this.isBargain=isBargain;
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return currentProductList.size();
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
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            view = View.inflate(mContext, R.layout.searchresultlistitem, null);

            holder.productImg = (ImageView) view.findViewById(R.id.searchresult_product_img);
            holder.productTitleTV = (TextView) view.findViewById(R.id.searchresult_product_title_text);
            holder.productPriceTV = (TextView) view.findViewById(R.id.searchresult_product_price_text);
            holder.productOldPriceTV = (TextView) view.findViewById(R.id.searchresult_product_oldprice_text);
            holder.commentTV = (TextView) view.findViewById(R.id.searchresult_product_comment_text);

            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        holder.commentTV.setText("已有(" + currentProductList.get(position).getCommentCount() + ")人评论");
        holder.productOldPriceTV.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线
        holder.productOldPriceTV.getPaint().setAntiAlias(true);// 抗锯齿
        holder.productTitleTV.setText(currentProductList.get(position).getName());

        holder.productOldPriceTV.setText("原价:￥" + currentProductList.get(position).getMarketprice());
        holder.productPriceTV.setText("现价:￥" + currentProductList.get(position).getSaleprice());
        Log.i(TAG,currentProductList.get(position).getStockcount()+"--"+currentProductList.get(position).getName());

        holder.productOldPriceTV.setVisibility(View.VISIBLE);

        if (currentProductList.get(position).getStockcount() > 0||isBargain) {
            holder.productOldPriceTV.setText("原价:￥" + currentProductList.get(position).getMarketprice());
            holder.productPriceTV.setText("现价:￥" + currentProductList.get(position).getSaleprice());
        } else {
            holder.productOldPriceTV.setVisibility(View.GONE);
            holder.productPriceTV.setText(mContext.getResources().getString(R.string.commodity_null));
        }
        String tempImgUrl = currentProductList.get(position).getPic() + "";
        if (!tempImgUrl.contains("http")) {
            tempImgUrl = tempImgUrl.replace("{0}", "T175X228_");
            tempImgUrl = ConstantValue.IMAGE_URL + tempImgUrl;
        }
        /**
         * 显示图片
         * 参数1：图片url
         * 参数2：显示图片的控件
         * 参数3：显示图片的设置
         * 参数4：监听器
         */
        imageLoader.displayImage(tempImgUrl, holder.productImg, options, animateFirstListener);
        return view;
    }

    static class ViewHolder {
        ImageView productImg;//商品图标
        TextView productTitleTV;//商品标题
        TextView productPriceTV;//商品价
        TextView productOldPriceTV;//商品价
        TextView commentTV;//评论
    }
}

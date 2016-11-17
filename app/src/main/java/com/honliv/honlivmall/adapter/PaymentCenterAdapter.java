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

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Rodin on 2016/9/12.
 */
public class PaymentCenterAdapter extends BaseAdapter {
    private static final String TAG = "PaymentCenterAdapter";
    ArrayList<Product> currentProductList;// 要展示的的商品列表
    String tempStand = "";
    private ImageLoader imageLoader;
    private Context mContext;

    public PaymentCenterAdapter(Context mContext, ArrayList<Product> currentProductList, ImageLoader imageLoader) {
        this.currentProductList = currentProductList;
        this.mContext = mContext;
        this.imageLoader = imageLoader;
    }

    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

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

    public View getView(final int position, View convertView,
                        ViewGroup parent) {
        View view = null;
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            view = View.inflate(mContext,
                    R.layout.payment_list_item, null);
                /*
                 * view = LayoutInflater.from(getApplicationContext()).inflate(
				 * R.layout.product_list_item, null);
				 */
            holder.productImg = (ImageView) view
                    .findViewById(R.id.item_productImage_IV);
            holder.productTitleTV = (TextView) view
                    .findViewById(R.id.payment_item_prodName_text);
            holder.productPriceTV = (TextView) view
                    .findViewById(R.id.payment_item_prodPrice_text);
            holder.productNumTV = (TextView) view
                    .findViewById(R.id.productNumValue);
            holder.standardTV = (TextView) view
                    .findViewById(R.id.item_prodstanard_text);
            holder.productDiscount = (TextView) view
                    .findViewById(R.id.item_prodDiscount_text);
            holder.paymentprodPriceTV = (TextView) view
                    .findViewById(R.id.payment_item_prodPrice_TV);

            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }

        holder.productTitleTV.setText((currentProductList.get(position))
                .getName());
        Log.i(TAG, currentProductList.get(position).getName());
        holder.productNumTV.setText(currentProductList.get(position)
                .getNumber() + "");
        if (currentProductList.get(position).getPrice() > 0) {
            //是优惠卷
            int tempPrice = currentProductList.get(position).getPrice();
            BigDecimal b = new BigDecimal(tempPrice);  //表明四舍五入，保留两位小数
            float allProductPriceStr = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
            holder.productPriceTV.setText("￥"
                    + String.format("%.2f", allProductPriceStr));
            holder.paymentprodPriceTV.setText("面值:");
        } else {//是赠商品，商品
            String tempPrice = currentProductList.get(position).getSaleprice();
            if ("0".equals(tempPrice)) {
                tempPrice = tempPrice + ".00";
            }
            holder.productPriceTV.setText("￥"
                    + tempPrice);
            holder.paymentprodPriceTV.setText("价格:");
        }

        if (currentProductList.get(position).getDiscountValue() != 0) {
            if (currentProductList.get(position).getNumber() >= currentProductList
                    .get(position).getDiscountNum()) {
                holder.productDiscount.setVisibility(View.VISIBLE);
                holder.productDiscount.setText(String
                        .format("%.1f", currentProductList.get(position)
                                .getDiscountValue() / 10)
                        + "折");
            }
        } else {
            holder.productDiscount.setVisibility(View.GONE);
        }

        if (currentProductList.get(position).getPrice() > 0) {
            holder.standardTV.setText("" + currentProductList.get(position)
                    .getLimitStr());
            holder.productImg.setImageResource(R.drawable.couple_default);
        } else {//赠送商品
            tempStand = "";
            holder.standardTV.setVisibility(View.VISIBLE);
            if (currentProductList.get(position).getShopCarColorKey() != null) {
                tempStand = currentProductList.get(position).getShopCarColorKey() + ":" + currentProductList.get(position)
                        .getShopCarProductColor();
            }
            if (currentProductList.get(position).getShopCarSizekey() != null) {
                tempStand += ",   " + currentProductList.get(position).getShopCarSizekey() + ":" + currentProductList.get(position).getShopCarProductSize();
            }
            if (!"".equals(tempStand)) {
                holder.standardTV.setText(tempStand);
            } else {
                holder.standardTV.setText("规格 ：无");
            }

            /**
             * 显示图片 参数1：图片url 参数2：显示图片的控件 参数3：显示图片的设置 参数4：监听器
             */
            imageLoader.displayImage(Utils.checkImagUrl(currentProductList.get(position).getPic() + ""), holder.productImg, ImageOptionsUtils.getMyOrderOptions(),
                    animateFirstListener);
        }
        return view;
    }

    class ViewHolder {
        ImageView productImg;// 商品图标
        TextView productTitleTV;// 商品标题
        TextView standardTV;// 规格
        TextView productNumTV;// 数量
        TextView productPriceTV;// 商品现价productDiscount
        TextView productDiscount;// 折扣
        TextView paymentprodPriceTV;//商品价前的东西
        // EditText productNumET;//商品数量
    }
}

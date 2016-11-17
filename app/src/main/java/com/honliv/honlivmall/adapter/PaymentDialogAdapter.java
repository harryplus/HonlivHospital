package com.honliv.honlivmall.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.bean.PayShip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rodin on 2016/11/6.
 */
public class PaymentDialogAdapter extends BaseAdapter {
    private static final String TAG = "PaymentDialogAdapter";
    private List<PayShip> list;

    public List<View> getListView() {
        return listView;
    }

    private List<View> listView;
    private Context mContext;

    public PaymentDialogAdapter(Context mContext, List<PayShip> list) {
        this.mContext = mContext;
        this.list = list;
        listView = new ArrayList<>();
    }

    @Override
    public int getCount() {
        Log.i(TAG, list.size() + "");
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (listView.size() < position + 1) {
            view = View.inflate(mContext, R.layout.item_payment_dialog, null);
            final TextView content = (TextView) view.findViewById(R.id.content);
            ImageView icon = (ImageView) view.findViewById(R.id.icon);
            String name = list.get(position).getName();
            content.setText(name);
            if (name.equals(mContext.getString(R.string.text_hxt_pay))) {
                icon.setBackgroundResource(R.drawable.icon_hxtpay);
            } else if (name.equals(mContext.getString(R.string.text_weixin_pay))) {
                icon.setBackgroundResource(R.drawable.icon_weixinpay);
            } else if (name.equals(mContext.getString(R.string.text_ali_pay))) {
                icon.setBackgroundResource(R.drawable.icon_alipay);
            } else if (name.equals(mContext.getString(R.string.text_deliver_pay))) {
                icon.setBackgroundResource(R.drawable.icon_deliverpay);
            } else if (name.equals(mContext.getString(R.string.text_hxt_send))) {
                icon.setBackgroundResource(R.drawable.icon_hlsend);
            }
            listView.add(view);
        } else {
            view = listView.get(position);
        }
        return view;
    }
}

package com.honliv.honlivmall.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.bean.HxtBean;

import java.util.ArrayList;

/**
 * Created by Rodin on 2016/11/2.
 */
public class HxtAdapter extends BaseAdapter {
    private static final String TAG = "HxtAdapter";
    private final ArrayList<HxtBean> dataList;
    private final Context mContext;
    private LayoutInflater mInflater;

    public HxtAdapter(Context mContext, ArrayList<HxtBean> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        Log.i(TAG, dataList.size() + "---HxtAdapterCount");
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            View view = mInflater.inflate(R.layout.hxt_item, null);
            HxtBean hxtBean = dataList.get(position);
            TextView cardType = (TextView) view.findViewById(R.id.cardType);
            TextView cardUserName = (TextView) view.findViewById(R.id.cardUserName);
            TextView phone = (TextView) view.findViewById(R.id.phone);
            TextView cardNo = (TextView) view.findViewById(R.id.cardNo);
            cardType.setText(hxtBean.getCardType());
            cardUserName.setText(hxtBean.getCardUserName());
            phone.setText(hxtBean.getPhone());
            cardNo.setText(hxtBean.getCardNo());
            return view;
        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }
        return null;
    }
}

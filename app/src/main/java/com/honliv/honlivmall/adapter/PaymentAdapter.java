package com.honliv.honlivmall.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.bean.PayShip;

import java.util.List;

/**
 * Created by Rodin on 2016/11/2.
 */
public class PaymentAdapter extends BaseAdapter {
    private List<PayShip> PayShipList;
    private Context mContext;

    public PaymentAdapter(Context mContext, List<PayShip> PayShipList) {
        this.mContext=mContext;
        this.PayShipList = PayShipList;
    }

    @Override
    public int getCount() {

        return PayShipList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return PayShipList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;
        ViewSpinnerHolder holder = null;
        if (convertView == null) {
            holder = new ViewSpinnerHolder();
            view = View.inflate(mContext,
                    R.layout.select_spinner__item, null);

            holder.title = (TextView) view
                    .findViewById(R.id.my_spinner_item_name);

            view.setTag(holder);

        } else {
            view = convertView;
            holder = (ViewSpinnerHolder) view.getTag();
        }
        holder.title.setText(PayShipList.get(position).getName() + "");
        return view;
    }
    static class ViewSpinnerHolder {
        TextView title;
    }
}

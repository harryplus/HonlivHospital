package com.honliv.honlivmall.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.honliv.honlivmall.R;

/**
 * Created by Rodin on 2016/11/20.
 */
public class SearchAdapter extends BaseAdapter {
    private final Context context;
    private final String[] hotNames;

    public SearchAdapter(Context context, String[] hotNames) {
        this.context = context;
        this.hotNames = hotNames;
    }

    @Override
    public int getCount() {
        return hotNames.length;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.activity_search_item, null);
//                convertView = View.inflate(getContext(), R.layout.activity_search_item, null);

            //	holder.logo = (ImageView) convertView.findViewById(R.id.ii_hall_lottery_logo);
            holder.title = (TextView) convertView.findViewById(R.id.tv_search_title_item);
            //	holder.summary = (TextView) convertView.findViewById(R.id.ii_hall_lottery_summary);

            //	holder.summary.setTag(position);//3.setTag的第一个用途

            //		holder.bet = (ImageView) convertView.findViewById(R.id.ii_hall_lottery_bet);
            holder.title.setTag(position);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText("" + hotNames[position]);

        return convertView;
    }

    private class ViewHolder {
        TextView title;
    }
}



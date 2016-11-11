package com.honliv.honlivhospital.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.honliv.honlivhospital.R;
import com.honliv.honlivhospital.domain.DoctorTime;
import com.honliv.honlivhospital.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rodin on 2016/10/29.
 */
public class FirstSelectDoctorAdapter extends RecyclerView.Adapter<FirstSelectDoctorAdapter.VH> {
    private final Context mContext;
    private List<DoctorTime> mItems = new ArrayList<>();
    private LayoutInflater mInflater;

    private OnItemClickListener mClickListener;


    public FirstSelectDoctorAdapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_select_doctor_first, parent, false);
        final VH holder = new VH(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        DoctorTime item = mItems.get(position);
        holder.name.setText(item.getName());
        holder.skill.setText(item.getSkill());
        holder.head.setImageURI(item.getHead());
        BaseAdapter adapter = new gridAdapter(item.getTime());
        holder.gridview.setAdapter(adapter);
    }

    public void setDatas(List<DoctorTime> items) {
        mItems.clear();
        mItems.addAll(items);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public DoctorTime getItem(int position) {
        return mItems.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {

    }

    public class VH extends RecyclerView.ViewHolder {
        public TextView name;
        public SimpleDraweeView head;
        public TextView skill;
        public GridView gridview;

        public VH(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            head = (SimpleDraweeView) itemView.findViewById(R.id.head);
            skill = (TextView) itemView.findViewById(R.id.skill);
            gridview = (GridView) itemView.findViewById(R.id.gridview);
        }
    }

    private class gridAdapter extends BaseAdapter {

        private final List<String> timeList;

        public gridAdapter(List<String> time) {
            this.timeList = time;
        }

        @Override
        public int getCount() {
            return timeList.size();
        }

        @Override
        public Object getItem(int position) {
            return timeList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(mContext, R.layout.item_select_doctor_first_gridview, null);
            TextView time = (TextView) view.findViewById(R.id.time);
            time.setText(timeList.get(position));
            return view;
        }
    }
}

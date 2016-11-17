package com.honliv.honlivmall.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.bean.Professor;
import com.honliv.honlivmall.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rodin on 2016/10/29.
 */
public class FirstProfessorAdapter extends RecyclerView.Adapter<FirstProfessorAdapter.VH> {
    private List<Professor> mItems = new ArrayList<>();
    private LayoutInflater mInflater;

    private OnItemClickListener mClickListener;


    public FirstProfessorAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_professor_first, parent, false);
        final VH holder = new VH(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        Professor item = mItems.get(position);
        holder.name.setText(item.getName());
        holder.position.setText(item.getPosition());
        holder.department.setText(item.getDepartment());
        holder.skill.setText(item.getSkill());
        holder.icon_head.setImageURI(item.getIcon_head());
    }

    public void setDatas(List<Professor> items) {
        mItems.clear();
        mItems.addAll(items);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public Professor getItem(int position) {
        return mItems.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {

    }

    public class VH extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView position;
        public TextView department;
        public TextView skill;
        public SimpleDraweeView icon_head;

        public VH(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            position = (TextView) itemView.findViewById(R.id.position);
            department = (TextView) itemView.findViewById(R.id.department);
            skill = (TextView) itemView.findViewById(R.id.skill);
            icon_head = (SimpleDraweeView) itemView.findViewById(R.id.icon_head);
        }
    }
}

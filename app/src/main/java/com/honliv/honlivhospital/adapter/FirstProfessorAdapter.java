package com.honliv.honlivhospital.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.honliv.honlivhospital.R;
import com.honliv.honlivhospital.application.MyApplication;
import com.honliv.honlivhospital.domain.Professor;
import com.honliv.honlivhospital.listener.OnItemClickListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

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

        // 把每个图片视图设置不同的Transition名称, 防止在一个视图内有多个相同的名称, 在变换的时候造成混乱
        // Fragment支持多个View进行变换, 使用适配器时, 需要加以区分
//        ViewCompat.setTransitionName(holder.img, String.valueOf(position) + "_image");

//        holder.img.setImageResource(item.getImgRes());
        holder.name.setText(item.getName());
        holder.position.setText(item.getPosition());
        holder.department.setText(item.getDepartment());
        holder.skill.setText(item.getSkill());
//        MyApplication.imageLoader.loadImage(item.getIcon_head(), holder.icon_head);
        MyApplication.imageLoader.displayImage(item.getIcon_head(), holder.icon_head);
        MyApplication.imageLoader.loadImage(item.getIcon_head(), new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                // Do whatever you want with Bitmap
//                Log.i(TAG, imageUri);
            }
        });
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
        public ImageView icon_head;

        public VH(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            position = (TextView) itemView.findViewById(R.id.position);
            department = (TextView) itemView.findViewById(R.id.department);
            skill = (TextView) itemView.findViewById(R.id.skill);
            icon_head = (ImageView) itemView.findViewById(R.id.icon_head);
        }
    }
}

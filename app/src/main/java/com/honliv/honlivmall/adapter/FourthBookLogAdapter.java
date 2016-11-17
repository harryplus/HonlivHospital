package com.honliv.honlivmall.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.bean.BookLog;
import com.honliv.honlivmall.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rodin on 2016/10/29.
 */
public class FourthBookLogAdapter extends RecyclerView.Adapter<FourthBookLogAdapter.VH> {
    private List<BookLog> mItems = new ArrayList<>();
    private LayoutInflater mInflater;

    private OnItemClickListener mClickListener;


    public FourthBookLogAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_booklog_fourth, parent, false);
        final VH holder = new VH(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        BookLog item = mItems.get(position);

        // 把每个图片视图设置不同的Transition名称, 防止在一个视图内有多个相同的名称, 在变换的时候造成混乱
        // Fragment支持多个View进行变换, 使用适配器时, 需要加以区分
//        ViewCompat.setTransitionName(holder.img, String.valueOf(position) + "_image");

//        holder.img.setImageResource(item.getImgRes());
//        holder.name.setText(item.getName());
//        holder.position.setText(item.getPosition());
//        holder.department.setText(item.getDepartment());
//        holder.skill.setText(item.getSkill());
////        MyApplication.imageLoader.loadImage(item.getIcon_head(), holder.icon_head);
//        MyApplication.imageLoader.displayImage(item.getIcon_head(), holder.icon_head);
//        MyApplication.imageLoader.loadImage(item.getIcon_head(), new SimpleImageLoadingListener() {
//            @Override
//            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//                // Do whatever you want with Bitmap
////                Log.i(TAG, imageUri);
//            }
//        });
    }

    public void setDatas(List<BookLog> items) {
        mItems.clear();
        mItems.addAll(items);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public BookLog getItem(int position) {
        return mItems.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {

    }

    public class VH extends RecyclerView.ViewHolder {
        public TextView office;
        public TextView doctor;
        public TextView time;
        public TextView number;

        public VH(View itemView) {
            super(itemView);
            office = (TextView) itemView.findViewById(R.id.tv_book_office);
            doctor = (TextView) itemView.findViewById(R.id.tv_book_doctor);
            time = (TextView) itemView.findViewById(R.id.tv_book_time);
            number = (TextView) itemView.findViewById(R.id.tv_book_number);
        }
    }
}

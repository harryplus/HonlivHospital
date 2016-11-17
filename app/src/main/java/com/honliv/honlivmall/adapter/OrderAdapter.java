package com.honliv.honlivmall.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.bean.GalleryProduct;

import java.util.List;

/**
 * Created by Rodin on 2016/9/10.
 */
public class OrderAdapter extends BaseAdapter {
//    private final Context mContext;
//    private final List<MSGallery> galleryitemList;
    List<GalleryProduct> galleryProductes;

//    public OrderAdapter(List<GalleryProduct> galleryProductes, Context mContext, List<MSGallery> galleryitemList) {
//        this.galleryProductes = galleryProductes;
//        this.mContext = mContext;
//        this.galleryitemList = galleryitemList;
//    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return galleryProductes.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return galleryProductes.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
//            view = View.inflate(mContext, R.layout.home_list_item, null);

//                holder.classTitle = (TextView) view.findViewById(R.id.grallerywoman_tv);
//            holder.gallery = (MSGallery) view.findViewById(R.id.home_woman_gallery);
//                holder.titleView = (ImageView) view.findViewById(R.id.home_image_title);


            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }

//            imageLoader.displayImage(Utils.checkImagUrl(homeBanners.get(0).getPic()), holder.titleView, options);
//            holder.classTitle.setText(galleryProductes.get(position).getName());

//        MSGallery items = galleryitemList.get(position);
//        holder.gallery.setAdapter(items.adapter);
//        holder.gallery.setSelection(100 * galleryProductes.get(position).getProductlist().size() + 1);
//        holder.gallery.setOnItemClickListener(new HomeGalleryItemListener(galleryProductes.get(position)));
        return view;
    }

    private class ViewHolder {
        //        TextView classTitle;//分类标题
        ImageView titleView;
//        MSGallery gallery;//滑动图片
    }

    private class HomeGalleryItemListener implements AdapterView.OnItemClickListener {
        private GalleryProduct galleryProduct;

        public HomeGalleryItemListener(GalleryProduct galleryProduct) {
            super();
            this.galleryProduct = galleryProduct;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            int pId = galleryProduct.getProductlist().get(position % galleryProduct.getProductlist().size()).getId();

//            Intent intent = new Intent(mContext, ProductDetailActivity.class);
//            intent.putExtra("pId", pId);
//            mContext.startActivity(intent);
//            // finish();
//            ((Activity) mContext).overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);

            //	PromptManager.showToastTest(getApplicationContext(), "跳转功能等待开发;;;"+ (position% womanPrice.length));
        }
    }
}
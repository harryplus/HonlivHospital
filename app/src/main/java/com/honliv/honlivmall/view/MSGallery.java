package com.honliv.honlivmall.view;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

import com.honliv.honlivmall.ConstantValue;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.bean.GalleryProduct;

import java.util.ArrayList;

/**
 * 首页滑动图片
 *
 * @author Administrator
 */
public class MSGallery extends Gallery {

    private float mLastMotionX;// 滑动过程中，x方向的初始坐标
    private float mLastMotionY;// 滑动过程中，y方向的初始坐标
    private int mTouchSlop;// 手指大小的距离
    public ArrayList<String> arraylist;
    //public GalleryProduct galleryProduct;
    public GalleryImageAdapter adapter;

    private Context context;

    private GalleryProduct galleryProduct;

    public MSGallery(Context context, GalleryProduct galleryProduct) {
        super(context);
        this.context = context;
        final ViewConfiguration configuration = ViewConfiguration
                .get(getContext());
        mTouchSlop = configuration.getScaledTouchSlop();
        this.setStaticTransformationsEnabled(true);
        this.galleryProduct = galleryProduct;
    }

    public MSGallery(Context context) {
        super(context);
        this.context = context;
        final ViewConfiguration configuration = ViewConfiguration
                .get(getContext());
        mTouchSlop = configuration.getScaledTouchSlop();
        this.setStaticTransformationsEnabled(true);
    }

    public MSGallery(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.setStaticTransformationsEnabled(true);
    }

    public MSGallery(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        this.setStaticTransformationsEnabled(true);
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        return super.onScroll(e1, e2, distanceX, distanceY);
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();// 获取触摸事件类型
        final float x = ev.getX();// 每次触摸事件的x坐标
        final float y = ev.getY();// 每次触摸事件的y坐标
        switch (action) {
            case MotionEvent.ACTION_DOWN:// 按下事件
                mLastMotionX = x;// 初始化每次触摸事件的x方向的初始坐标，即手指按下的x方向坐标
                mLastMotionY = y;// 初始化每次触摸事件的y方向的初始坐标，即手指按下的y方向坐标
                break;

            case MotionEvent.ACTION_MOVE:
                // 每次滑动事件x方向坐标与触摸事件x方向初始坐标的距离
                final int deltaX = (int) (mLastMotionX - x);
                // 每次滑动事件y方向坐标与触摸事件y方向初始坐标的距离
                final int deltaY = (int) (mLastMotionY - y);
                boolean xMoved = Math.abs(deltaX) > mTouchSlop
                        && Math.abs(deltaY / deltaX) < 1;
                // 判断触摸事件处理的传递方向，该业务中是，
                // x方向的距离大于手指，并且y方向滑动的距离小于x方向的滑动距离时，Gallery消费掉此次触摸事件
                // 如果需要，请在您的业务中，改变判断的逻辑
                if (xMoved) {// Gallery需要消费掉此次触摸事件
                    return true;// 返回true就不会将此次触摸事件传递给子View了
                }
                break;
            case MotionEvent.ACTION_UP:
                if (this.getSelectedItemPosition() < 1) {
                    this.setSelection(1);
                    return true;
                }
                break;
            case MotionEvent.ACTION_OUTSIDE:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            case MotionEvent.ACTION_MASK:
                break;
        }
        return false;// 将此次触摸事件传递给子View
    }

	/*public void initAdapter(Context context, int[] item) {
        this.adapter = new ImageAdapter(context, item);
	}*/

    public void initAdapter(Context context) {
        this.adapter = new GalleryImageAdapter();
    }

    public class GalleryImageAdapter extends BaseAdapter {
        private String imagerUrl;

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return Integer.MAX_VALUE;
        }

        @Override
        public Object getItem(int i) {
            // TODO Auto-generated method stub
            return i;
        }

        @Override
        public long getItemId(int i) {
            // TODO Auto-generated method stub
            return i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewgroup) {
            View view = null;
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                view = LayoutInflater.from(context).inflate(
                        R.layout.allvideo_layout_item_horiitem, null);
                holder.productImg = (ImageView) view.findViewById(R.id.videoIV);
                holder.productPriceTV = (TextView) view.findViewById(R.id.gp_priceTV);
                holder.name = (TextView) view.findViewById(R.id.name);
                view.setTag(holder);
            } else {
                view = convertView;
                holder = (ViewHolder) view.getTag();
            }
            //holder.productImg.setBackgroundResource(imageIDs[(position% imageIDs.length)]);
            //	holder.productPriceTV.setText(""+imagePrice[(position% imagePrice.length)]);
            imagerUrl = galleryProduct.getProductlist().get(position % galleryProduct.getProductlist().size()).getPic() + "";
            if (!imagerUrl.contains("http")) {
                imagerUrl = imagerUrl.replace("{0}", "T175X228_");
                imagerUrl = ConstantValue.IMAGE_URL + imagerUrl;
            }

//            imageLoader.displayImage(imagerUrl, holder.productImg, options);
            if (view != null) {
                holder.name.setText(galleryProduct.getProductlist().get(position % galleryProduct.getProductlist().size()).getName());
                holder.productPriceTV.setText("￥" + galleryProduct.getProductlist().get(position % galleryProduct.getProductlist().size()).getSaleprice() + "");
            }
            return view;
        }
    }

    static class ViewHolder {
        ImageView productImg;//商品图标
        //TextView productTitleTV;//商品标题
        TextView productPriceTV;//商品价
        public TextView name;
    }
}

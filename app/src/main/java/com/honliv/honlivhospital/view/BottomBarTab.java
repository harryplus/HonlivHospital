package com.honliv.honlivhospital.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.honliv.honlivhospital.R;


/**
 * Created by YoKeyword on 16/6/3.
 */
public class BottomBarTab extends FrameLayout {
    private ImageView mIcon;
    private TextView mIconText;
    private Context mContext;
    private int mTabPosition = -1;
    private View view;

    public BottomBarTab(Context context, @DrawableRes int icon, int name) {
        this(context, null, icon, name);
    }

    public BottomBarTab(Context context, AttributeSet attrs, int icon, int name) {
        this(context, attrs, 0, icon, name);
    }

    public BottomBarTab(Context context, AttributeSet attrs, int defStyleAttr, int icon, int name) {
        super(context, attrs, defStyleAttr);
        init(context, icon, name);
    }

    private void init(Context context, int icon, int name) {
        mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(new int[]{R.attr.selectableItemBackgroundBorderless});
        Drawable drawable = typedArray.getDrawable(0);
        setBackgroundDrawable(drawable);
        typedArray.recycle();
        view = View.inflate(context, R.layout.layout_bottom_item, null);
        mIcon = (ImageView) view.findViewById(R.id.imageview);
        mIconText = (TextView) view.findViewById(R.id.textview);
//        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 27, getResources().getDisplayMetrics());
//        LayoutParams params = new LayoutParams(size, size);
//        params.gravity = Gravity.CENTER;
//        mIcon.setLayoutParams(params);
        mIcon.setImageResource(icon);
        mIcon.setColorFilter(ContextCompat.getColor(context, R.color.tab_unselect));
        mIconText.setText(name);
        mIconText.setTextSize(12);
        addView(view);
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (selected) {
            mIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.colorPrimary));
            mIconText.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
        } else {
            mIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.tab_unselect));
            mIconText.setTextColor(ContextCompat.getColor(mContext, R.color.tab_unselect));
        }
    }

    public void setTabPosition(int position) {
        mTabPosition = position;
        if (position == 0) {
            setSelected(true);
        }
    }

    public int getTabPosition() {
        return mTabPosition;
    }
}

package com.honliv.honlivmall.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * 能够兼容ViewPager的ScrollView 
 * @Description: 解决了ViewPager在ScrollView中的滑动反弹问题 
 */  
public class ScrollViewForViewPager extends ScrollView {
	// 滑动距离及坐标  
    private float xDistance, yDistance, xLast, yLast;
    private String TAG="ScrollViewForViewPager";

    public ScrollViewForViewPager(Context context, AttributeSet attrs,
                                  int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	public ScrollViewForViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public ScrollViewForViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	@Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {  
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;  
                xLast = ev.getX();  
                yLast = ev.getY();  
                break;  
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();  
                final float curY = ev.getY();  
                  
                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);
                xLast = curX;  
                yLast = curY;  
               // LogUtil.info("scrollview收到滑动事件111111");
                if(xDistance > yDistance){  
                	//LogUtil.info("scrollview收到滑动事件22222");
                    return false;
                }    
        }  
        return super.onInterceptTouchEvent(ev); 
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}

package com.honliv.honlivmall.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;


/**
 * 解决viewpager无法滑动的问题
 * @author wang
 *
 */
public class MyViewPager extends ViewPager {
	
	// 滑动距离及坐标  
    private float xDistance, xLast;
	public MyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyViewPager(Context context) {
		super(context);
	}
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return super.dispatchTouchEvent(ev);
	}
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
        case MotionEvent.ACTION_DOWN:
            xDistance = 0f;  
            xLast = ev.getX();  
           
            break;  
        case MotionEvent.ACTION_MOVE:
            final float curX = ev.getX();  
           // xDistance = Math.abs(curX - xLast);  
            xDistance = curX - xLast;  
            
            int index = this.getCurrentItem();
            if(xDistance < -25){
                this.setCurrentItem(index+1);
            }else if(25>xDistance&&xDistance>0){
            	 this.setCurrentItem(index-1);
           	 return true;
           }else if(xDistance>25){
            	 this.setCurrentItem(index-1);
            	 return true;
            }    
		}  
		return super.onInterceptTouchEvent(ev);
	}
	
	public boolean onTouchEvent(MotionEvent ev) {
		return super.onTouchEvent(ev);
	}
}

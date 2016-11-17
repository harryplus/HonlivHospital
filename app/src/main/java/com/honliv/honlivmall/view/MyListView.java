package com.honliv.honlivmall.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

/**
 * listview列表
 * @author Administrator
 *
 */
public class MyListView extends ListView {
	
	/**
	 * pull state,pull up or pull down;PULL_UP_STATE or PULL_DOWN_STATE
	 */	
	int mLastMotionY ;
	// 滑动距离及坐标  
    private float xDistance, yDistance, xLast, yLast;
    private float curX;
	private float curY;
	boolean bottomFlag;
	
	public MyListView(Context context) {
		super(context);
	}
	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	public MyListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		switch (ev.getAction()) {  
		
        case MotionEvent.ACTION_DOWN:
            xDistance = yDistance = 0f;  
            xLast = ev.getX();  
            yLast = ev.getY();  
            break;  
        case MotionEvent.ACTION_MOVE:
        	curX = ev.getX();
        	curY = ev.getY();  
            xDistance += Math.abs(curX - xLast);
            yDistance += Math.abs(curY - yLast);
            xLast = curX;  
            yLast = curY;  
           // LogUtil.info("scrollview收到滑动事件111111");
            if(xDistance > yDistance){  
            	bottomFlag = false;
                return false;  
            }    
		}  
		return super.dispatchTouchEvent(ev);
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		//阻止父类拦截事件
		System.out.println("getParent()="+getParent().getClass());
		//经过对scrollView的滑动判断，得知当前scrollView已经滑动到最底部，所以现在要把touch事件传递给listView处理。
		if(bottomFlag){
			getParent().requestDisallowInterceptTouchEvent(true);
		}
		
		return super.onInterceptTouchEvent(ev);
	}
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		int y = (int) ev.getRawY();
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// 首先拦截down事件,记录y坐标
			mLastMotionY = y;
			break;
		case MotionEvent.ACTION_MOVE:
			// deltaY > 0 是向下运动,< 0是向上运动
			int deltaY = y - mLastMotionY;
			System.out.println("deltaY="+deltaY);
			/**
			 * 走到这个过程，说明scrollView已经滑动到最低端，bottomFlag=true，再判断listView是否在向下滑动，
			 * listView是否已经滑动到最顶端。如果是：则将焦点还给scrollView，让整个scrollView滑动。也就是将scrollView
			 * 向下拉，使scrollView不在处于最底端。现在的touch事件被scrollView处理，那么当scrollView再次滑动到最底端
			 * 时，将touch事件交给listView处理。
			 * 
			 * 此判断只判断了向下滑动，因为只有向下滑动listView才会达到最顶端，touch事件才需要交给listView的父级处理。
			 * 如果是listView向上滑动，则直接处理touch事件便可，尽情滑动listView。
			 * 
			 */
			if(deltaY>0){
				View child = getChildAt(0);
				if(child!=null){
					if (getFirstVisiblePosition() == 0 && child.getTop() == 0) {
						bottomFlag = false;
						getParent().requestDisallowInterceptTouchEvent(false); 
					}
					
					int top = child.getTop();
					int padding = getPaddingTop();
					System.out.println("getPaddingTop()="+getPaddingTop());
					if (getFirstVisiblePosition() == 0 && Math.abs(top - padding) <= 8) {//这里之前用3可以判断,但现在不行,还没找到原因
						bottomFlag = false;
						getParent().requestDisallowInterceptTouchEvent(false); 
					}
				}
			}
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			break;
		}
		return super.onTouchEvent(ev);
	}
	 @Override
     public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {   
    
         int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                 MeasureSpec.AT_MOST);
         super.onMeasure(widthMeasureSpec, expandSpec);   
     }   
	
	public void setBottomFlag(boolean flag){
		bottomFlag = flag;
	}
}

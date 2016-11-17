package com.honliv.honlivmall.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 自己动的tv
 * @author wang
 *
 */
public class FocusedTextView extends TextView {

	public FocusedTextView(Context context) {
		super(context);
	}
	
	public FocusedTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	public FocusedTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean isFocused() {
		return true;
	}
}

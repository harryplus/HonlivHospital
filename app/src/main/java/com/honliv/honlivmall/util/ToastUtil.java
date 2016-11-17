package com.honliv.honlivmall.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import com.honliv.honlivmall.application.MyApplication;

/**
 * 用来全局显示Toast的类
 * @author wang
 *
 */
public class ToastUtil {

	 private static Toast mToast = null;

	    /** 保证在UI线程中显示Toast */
	    private static Handler mHandler = new Handler(Looper.getMainLooper()) {

	        @Override
	        public void handleMessage(Message msg) {
	            if (mToast != null) {
	                mToast.cancel();
	            }
	            String text = (String) msg.obj;
	            int duration = msg.arg2;
//	            mToast = Toast.makeText(MyApplication.CONTEXT, text, Toast.LENGTH_SHORT);
//	            mToast.show();
	        }
	    };

	    public static void showShortToast(String text) {
	        showToast(text, Toast.LENGTH_SHORT);
	    }

	    public static void showShortToast(int textResId) {
	        showToast(textResId, Toast.LENGTH_SHORT);
	    }

	    public static void showLongToast(String text) {
	        showToast(text, Toast.LENGTH_LONG);
	    }

	    public static void showLongToast(int textResId) {
	        showToast(textResId, Toast.LENGTH_LONG);
	    }

	    public static void showToast(int textResId, int duration) {
//	        showToast(MyApplication.CONTEXT.getString(textResId), duration);
	    }

	    public static void showToast(String text, int duration) {
	        mHandler.sendMessage(mHandler.obtainMessage(0, 0, duration, text));
	    }
}

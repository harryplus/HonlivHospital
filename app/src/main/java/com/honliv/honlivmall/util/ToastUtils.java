package com.honliv.honlivmall.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

/**
 * Created by Rodin on 2016/11/15.
 */
public class ToastUtils {

    private static Toast mToast;

    /**
     * 非阻塞试显示Toast,防止出现连续点击Toast时的显示问题
     */
    public static void showToast(Context context, CharSequence text, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(context, text, duration);
        } else {
            mToast.setText(text);
            mToast.setDuration(duration);
        }
        mToast.show();
    }

    public static void showToast(Context context, CharSequence text) {
        showToast(context, text, Toast.LENGTH_SHORT);
    }

    /**
     * 保证在UI线程中显示Toast
     */
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

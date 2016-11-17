package com.honliv.honlivmall.util;

import android.content.Context;

import java.text.DecimalFormat;

public class DensityUtil {
    /** 
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素) 
     */  
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    }  
  
    /** 
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp 
     */  
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (pxValue / scale + 0.5f);  
    }  
    
    /**
   	 * 将传入的字符串格式化成 保留两位小数的字符串
   	 * @param data
   	 * @return
   	 */
   	public static String formatToDoubleNumber(String data){

   		return new DecimalFormat("0.00").format(Double.parseDouble(("".equals(data) || "null".equals(data) || data == null)?"0":data));
   	}
}

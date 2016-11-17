package com.honliv.honlivmall.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Rodin on 2016/10/15.
 */
public class ViewUtils {

    private static final String TAG = "ViewUtils";

//    public static void updataTimeTV(TextView timeTV, String dateStr) {
//        try {
//            if (dateStr == null) {
//                dateStr = "2016年12月06日13时00分00秒";
//            }
//
//            long time2time = GroupBuyActivity.getSecondMatchCurrentTime(dateStr);
//
//            String str = GroupBuyActivity.getLasttime(time2time + "");
//            str = "抢购:" + str;
//
//            int dayPreIndex = str.indexOf(":");
//            int dayIndex = str.indexOf("天");
//            int hourIndex = str.indexOf("时");
//            int minuteIndex = str.indexOf("分");
//            int secondIndex = str.indexOf("秒");
//
//            SpannableStringBuilder style = new SpannableStringBuilder(str);
//            style.setSpan(new ForegroundColorSpan(Color.RED), dayPreIndex + 1, dayIndex, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
//            style.setSpan(new StyleSpan(android.graphics.Typeface.BOLD_ITALIC),
//                    dayPreIndex + 1, dayIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);   //设置斜体
//            style.setSpan(new ForegroundColorSpan(Color.RED), dayIndex + 1, hourIndex, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
//            style.setSpan(new StyleSpan(android.graphics.Typeface.BOLD_ITALIC),
//                    dayIndex + 1, hourIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);   //设置斜体
//            style.setSpan(new ForegroundColorSpan(Color.RED), hourIndex + 1, minuteIndex, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
//            style.setSpan(new StyleSpan(android.graphics.Typeface.BOLD_ITALIC),
//                    hourIndex + 1, minuteIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);   //设置斜体
//            style.setSpan(new ForegroundColorSpan(Color.RED), minuteIndex + 1, secondIndex, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
//            style.setSpan(new StyleSpan(android.graphics.Typeface.BOLD_ITALIC),
//                    minuteIndex + 1, secondIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);   //设置斜体
//            timeTV.setText(style);
//        } catch (Exception e) {
//            Log.i(TAG, e.toString());
//        }
//    }

    /**
     * 通过传入一点格式的日期时间设定，得到和当前时间相隔的秒数
     *
     * @param timeStr "yyyy年MM月dd日HH时mm分ss秒"
     * @return
     */
    public static long getSecondMatchCurrentTime(String timeStr) {
        DateFormat dateFormat = DateFormat.getDateInstance();
        dateFormat = new SimpleDateFormat("yyyy年MM月dd天HH时mm分ss秒");

        Date date2 = null;
        try {
            date2 = dateFormat.parse(timeStr);// String格式日期转成日期对象
            long time1 = new Date().getTime();// 1.现在的时间点

            long time2 = date2.getTime();// 2.通过日期对象获取毫秒数

            long time = Math.abs(time1 - time2);// 相隔的正数

            long second = (time / 1000);// 3.通过毫秒数得到秒数

            return second;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 将秒时间转换成日时分秒格式
     *
     * @param lasttime
     * @return
     */
//    public static String getLasttime(String lasttime) {
//        StringBuffer result = new StringBuffer();
//        if (StringUtils.isNumericSpace(lasttime)) {
//            int time = Integer.parseInt(lasttime);
//            int day = time / (24 * 60 * 60);
//            result.append(day).append("天");
//            if (day > 0) {
//                time = time - day * 24 * 60 * 60;
//            }
//            int hour = time / 3600;
//            result.append(hour).append("时");
//            if (hour > 0) {
//                time = time - hour * 60 * 60;
//            }
//            int minute = time / 60;
//            result.append(minute).append("分");
//            if (minute > 0) {
//                time = time - minute * 60;
//            }
//            result.append(time).append("秒");
//        }
//        return result.toString();
//    }
}

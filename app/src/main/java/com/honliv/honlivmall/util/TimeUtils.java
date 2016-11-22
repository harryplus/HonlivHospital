package com.honliv.honlivmall.util;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Rodin on 2016/11/21.
 */
public class TimeUtils {

    /**
     * 通过传入一点格式的日期时间设定，得到和当前时间相隔的秒数
     *
     * @param timeStr
     *            "yyyy年MM月dd日HH时mm分ss秒"
     * @return
     */
    public static long getSecondMatchCurrentTime(String timeStr) {
        DateFormat dateFormat = DateFormat.getDateInstance();
        dateFormat = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");

        Date date2 = null;
        try {
            date2 = dateFormat.parse(timeStr);// String格式日期转成日期对象
            long time1 = new Date().getTime();// 1.现在的时间点

            long time2 = date2.getTime();// 2.通过日期对象获取毫秒数

            long time = Math.abs(time1 - time2);// 相隔的正数

            long second = (time / 1000);// 3.通过毫秒数得到秒数

            return second;
        }catch(ParseException e){
            LogUtil.info("转化执行执行异常。。。。。");
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
    public static String getLasttime(String lasttime) {
        StringBuffer result = new StringBuffer();
        if (StringUtils.isNumericSpace(lasttime)) {
            int time = Integer.parseInt(lasttime);
            int day = time / (24 * 60 * 60);
            result.append(day).append("天");
            if (day > 0) {
                time = time - day * 24 * 60 * 60;
            }
            int hour = time / 3600;
            result.append(hour).append("时");
            if (hour > 0) {
                time = time - hour * 60 * 60;
            }
            int minute = time / 60;
            result.append(minute).append("分");
            if (minute > 0) {
                time = time - minute * 60;
            }
            result.append(time).append("秒");
        }
        return result.toString();
    }

}

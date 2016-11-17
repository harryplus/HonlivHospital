package com.honliv.honlivmall.util;

import android.os.Environment;
import android.util.Log;

import com.honliv.honlivmall.application.MyApplication;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * 日志记录
 * 
 */
public class LogUtil {
	/**
	 * 开发阶段
	 */
	private static final int DEVELOP = 0;
	/**
	 * 内部测试阶段
	 */
	private static final int DEBUG = 1;
	/**
	 * 公开测试
	 */
	private static final int BATE = 2;
	/**
	 * 正式版
	 */
	private static final int RELEASE = 3;

	/**
	 * 当前阶段标示
	 */
	private static int currentStage = RELEASE;

	private static FileOutputStream outputStream;
	private static String pattern = "yyyy-MM-dd HH:mm:ss";

	static {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			try {
				outputStream = new FileOutputStream(MyApplication.SaveFile, true);
			} catch (FileNotFoundException e) {

			}
		} else {

		}
	}

	public static void info(String msg) {
		info(LogUtil.class, msg);
	}

	public static void info(Class clazz, String msg) {
		switch (currentStage) {
			case DEVELOP:
				// 控制台输出
				Log.i(clazz.getSimpleName(), msg);
				break;
			case DEBUG:
				// 在应用下面创建目录存放日志

				break;
			case BATE:
				// 写日志到sdcard
				Date date = new Date();
				String time = DateFormatUtils.format(date, pattern);
				if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
					if (outputStream != null) {
						try {
							outputStream.write(time.getBytes());
							String className = "";
							if (clazz != null) {
								className = clazz.getSimpleName();
							}
							outputStream.write(("    " + className + "\r\n").getBytes());

							outputStream.write(msg.getBytes());
							outputStream.write("\r\n".getBytes());
							outputStream.flush();
						} catch (IOException e) {

						}
					} else {
						Log.i("SDCAEDTAG", "file is null");
					}
				}
				break;
			case RELEASE:
				// 一般不做日志记录
				break;
		}
	}
}

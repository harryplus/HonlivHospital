package com.honliv.honlivmall.util;

import java.io.IOException;
import java.util.Properties;

/**
 * 依据传递内容创建具体实例
 * 
 * @author Administrator
 * 
 */
public class BeanFactory {

	private static Properties properties;
	//只需要加载一次
	static {
		properties = new Properties();
		try {
			properties.load(BeanFactory.class.getClassLoader().
					getResourceAsStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取指定类型的实例
	 */
	public static <T> T getImpl(Class<T> clazz) {
		String key = clazz.getSimpleName();
		String className = properties.getProperty(key);
		try {
			return  (T) Class.forName(className).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

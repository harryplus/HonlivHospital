package com.honliv.honlivmall.bean;

import java.io.Serializable;

/**
 * 把历史搜索的关键词存入到数据
 * @author Administrator
 *
 */
public class SearchKey implements Serializable {
	private int id;
	private String key;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	@Override
	public String toString() {
		return "SearchKey [id=" + id + ", key=" + key + "]";
	}
}

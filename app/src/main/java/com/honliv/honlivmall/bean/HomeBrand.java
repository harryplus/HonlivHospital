package com.honliv.honlivmall.bean;

import java.io.Serializable;

/**
 * 首页品牌
 * @author Administrator
 *
 */
public class HomeBrand implements Serializable {
	private int id; 
	private String pic;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	@Override
	public String toString() {
		return "HomeBrand [id=" + id + ", pic=" + pic + "]";
	}
}

package com.honliv.honlivmall.bean;

import java.io.Serializable;

/**
 * 属性
 * @author Administrator
 *
 */

public class ProductPropertyFilter implements Serializable {

	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

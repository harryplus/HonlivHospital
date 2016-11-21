package com.honliv.honlivmall.bean;

import java.io.Serializable;
import java.util.List;

public class GalleryProduct implements Serializable {

	private String name;
	private String name2;
	private List<Product> productlist;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public List<Product> getProductlist() {
		return productlist;
	}
	public void setProductlist(List<Product> productlist) {
		this.productlist = productlist;
	}
}

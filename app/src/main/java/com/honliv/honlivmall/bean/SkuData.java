package com.honliv.honlivmall.bean;

import java.io.Serializable;

public class SkuData implements Serializable {
	private String sku;
	private int count;
	private int price;
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "SkuData [sku=" + sku + ", count=" + count + ", price=" + price
				+ "]";
	}
}

package com.honliv.honlivmall.bean;

import java.io.Serializable;

public class DefaultPrice implements Serializable {
	private int minPrice;
	private int maxPrice;
	public int getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}
	public int getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}
	@Override
	public String toString() {
		return "DefaultPrice [minPrice=" + minPrice + ", maxPrice=" + maxPrice
				+ "]";
	}
}

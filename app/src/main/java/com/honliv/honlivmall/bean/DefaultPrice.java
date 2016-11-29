package com.honliv.honlivmall.bean;

import java.io.Serializable;

public class DefaultPrice implements Serializable {
	private float minPrice;
	private float maxPrice;
	public float getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(float minPrice) {
		this.minPrice = minPrice;
	}
	public float getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(float maxPrice) {
		this.maxPrice = maxPrice;
	}
	@Override
	public String toString() {
		return "DefaultPrice [minPrice=" + minPrice + ", maxPrice=" + maxPrice
				+ "]";
	}
}

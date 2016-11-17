package com.honliv.honlivmall.bean;

import java.io.Serializable;
import java.util.List;


public class GifInfo implements Serializable {

	private List<Product> productList;//赠送的商品
	private List<Product> couponList; //赠送的优惠卷
	private List<Product> notStockProdList;//赠送的商品没有库存
	
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	public List<Product> getCouponList() {
		return couponList;
	}
	public void setCouponList(List<Product> couponList) {
		this.couponList = couponList;
	}
	
	public List<Product> getNotStockProdList() {
		return notStockProdList;
	}
	public void setNotStockProdList(List<Product> notStockProdList) {
		this.notStockProdList = notStockProdList;
	}
	@Override
	public String toString() {
		return "GifInfo [productList=" + productList + ", couponList="
				+ couponList + ", notStockProdList=" + notStockProdList + "]";
	}
}

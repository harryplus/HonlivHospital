package com.honliv.honlivmall.bean;

import java.util.List;

/**
 * 商品列表，加上回传属性
 * @author Administrator
 */
public class ProductListFilter {
	private int list_count;
	private List<Product> productlist;
	private List<ProductProperty> list_filter;
	
	public int getList_count() {
		return list_count;
	}
	public void setList_count(int list_count) {
		this.list_count = list_count;
	}
	public List<Product> getProductlist() {
		return productlist;
	}
	public void setProductlist(List<Product> productlist) {
		this.productlist = productlist;
	}
	public List<ProductProperty> getList_filter() {
		return list_filter;
	}
	public void setList_filter(List<ProductProperty> list_filter) {
		this.list_filter = list_filter;
	}
	@Override
	public String toString() {
		return "ProductListFilter [list_count=" + list_count + ", productlist="
				+ productlist + ", list_filter=" + list_filter + "]";
	}
}

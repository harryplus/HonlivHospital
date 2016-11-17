package com.honliv.honlivmall.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 属性
 * @author Administrator
 *
 */

public class ProductProperty implements Serializable {

	private int id;
	private String key;
	private String value;
	
	private List<ProductPropertyFilter> values;
	
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public List<ProductPropertyFilter> getValues() {
		return values;
	}
	public void setValues(List<ProductPropertyFilter> values) {
		this.values = values;
	}
	@Override
	public String toString() {
		return "ProductProperty [id=" + id + ", key=" + key + ", value="
				+ value + ", values=" + values + "]";
	}
}

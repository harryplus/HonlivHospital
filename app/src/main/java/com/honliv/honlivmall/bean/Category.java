package com.honliv.honlivmall.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 商品类别
 * @author wang
 *
 */
public class Category implements Serializable {
	private int id;
	private String title;
	private String pic;
	private int parentId;
	private boolean haschild;
	private String description;
	private List<Category> childlist;
	
	public List<Category> getChildlist() {
		return childlist;
	}
	public void setChildlist(List<Category> childlist) {
		this.childlist = childlist;
	}
	public boolean isHaschild() {
		return haschild;
	}
	public void setHaschild(boolean haschild) {
		this.haschild = haschild;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", title=" + title + ", pic=" + pic
				+ ", parentId=" + parentId + ", haschild=" + haschild
				+ ", tag=" + description + ", childlist=" + childlist + "]";
	}
	
	
}

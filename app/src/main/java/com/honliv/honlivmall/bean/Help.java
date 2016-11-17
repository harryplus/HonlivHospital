package com.honliv.honlivmall.bean;

import java.io.Serializable;
import java.util.List;

public class Help implements Serializable {
	private int id;
	private int parentid;
	private String title;
	private int version;
	private List<Help> childlist;
	private String content;//帮助内容
	
	/**
	 * @roseuid 521EEBA2006D
	 */
	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
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

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	public List<Help> getChildlist() {
		return childlist;
	}
	public void setChildlist(List<Help> childlist) {
		this.childlist = childlist;
	}
	
	
	public int getParentid() {
		return parentid;
	}
	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
	/**
	 * toString为了打印方便
	 */
	@Override
	public String toString() {
		return "Help [id=" + id + ", parentid=" + parentid + ", title=" + title
				+ ", version=" + version + ", childlist=" + childlist
				+ ", content=" + content + "]";
	}
}

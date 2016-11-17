package com.honliv.honlivmall.bean;

import java.io.Serializable;

public class PayShip implements Serializable {
//[{"id":1,"name":"动软卓越配送","description":""}]}
	
	private int id;
	private String name;
	private String description;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "PayShip [id=" + id + ", name=" + name + ", description="
				+ description + "]";
	}
}

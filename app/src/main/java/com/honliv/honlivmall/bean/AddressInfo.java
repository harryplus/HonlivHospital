package com.honliv.honlivmall.bean;

import java.io.Serializable;
import java.util.List;

public class AddressInfo implements Serializable {
	
	private int userId;
	private int lineId;//线路id
	private String addressArea; //市区 addressArea
	
	//线路列表
	private String address;
	private int supplierId;
	
	//确定要的
	private int id;
	private String name;
	private int depth;//层次
	private int parentid;
	private List<AddressInfo> childlist;
	private String areaDetail; //详细地址
	private boolean hasDefault;//isDefault 
	private int regionId;//地区id
	private String phone;
	
	
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getParentid() {
		return parentid;
	}
	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
	public List<AddressInfo> getChildlist() {
		return childlist;
	}
	public void setChildlist(List<AddressInfo> childlist) {
		this.childlist = childlist;
	}
	public int getLineId() {
		return lineId;
	}
	public void setLineId(int lineId) {
		this.lineId = lineId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRegionId() {
		return regionId;
	}
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	public boolean isHasDefault() {
		return hasDefault;
	}
	public void setHasDefault(boolean hasDefault) {
		this.hasDefault = hasDefault;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddressArea() {
		return addressArea;
	}
	public void setAddressArea(String addressArea) {
		this.addressArea = addressArea;
	}
	public String getAreaDetail() {
		return areaDetail;
	}
	public void setAreaDetail(String areaDetail) {
		this.areaDetail = areaDetail;
	}
	@Override
	public String toString() {
		return "AddressInfo [userId=" + userId + ", id=" + id + ", regionId="
				+ regionId + ", lineId=" + lineId + ", name=" + name
				+ ", phone=" + phone + ", addressArea=" + addressArea
				+ ", areaDetail=" + areaDetail + ", hasDefault=" + hasDefault
				+ ", address=" + address + ", supplierId=" + supplierId + "]";
	}
}

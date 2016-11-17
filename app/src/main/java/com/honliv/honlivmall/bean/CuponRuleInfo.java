package com.honliv.honlivmall.bean;
/**
 * 
 * @author Wangmf
 * 日期：2014年5月28日
 */
public class CuponRuleInfo {
//    "cuponId": 16,
//    "name": "2元抵扣券",
//    "needpoint": 200
	private int cuponId;
	private String name;
	private int needpoint;
	
	
	public CuponRuleInfo() {
		super();
	}
	public CuponRuleInfo(int cuponId, String name, int needpoint) {
		super();
		this.cuponId = cuponId;
		this.name = name;
		this.needpoint = needpoint;
	}
	public int getCuponId() {
		return cuponId;
	}
	public void setCuponId(int cuponId) {
		this.cuponId = cuponId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNeedpoint() {
		return needpoint;
	}
	public void setNeedpoint(int needpoint) {
		this.needpoint = needpoint;
	}
	@Override
	public String toString() {
		return "CuponRuleInfo [cuponId=" + cuponId + ", name=" + name
				+ ", needpoint=" + needpoint + "]";
	}

}

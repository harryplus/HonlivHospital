package com.honliv.honlivmall.bean;

import java.util.List;

/**
 * 物流
 * @author Administrator
 *
 */
public class Logistics {
	private List<String> list;
	private String expressway;   //快递
	private long logisticsid;    //运单编号
	private String logisticscorp;  //顺丰"
	
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public String getExpressway() {
		return expressway;
	}
	public void setExpressway(String expressway) {
		this.expressway = expressway;
	}
	public long getLogisticsid() {
		return logisticsid;
	}
	public void setLogisticsid(long logisticsid) {
		this.logisticsid = logisticsid;
	}
	public String getLogisticscorp() {
		return logisticscorp;
	}
	public void setLogisticscorp(String logisticscorp) {
		this.logisticscorp = logisticscorp;
	}
	
}

package com.honliv.honlivmall.bean;

import java.io.Serializable;

/**
 * 订单跟踪
 * @author Administrator
 *
 */
public class OrderFollow implements Serializable {
	private String time;   //时间
	private String operation;   //操作
	private String createduser;   //作者
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getCreateduser() {
		return createduser;
	}
	public void setCreateduser(String createduser) {
		this.createduser = createduser;
	}
}

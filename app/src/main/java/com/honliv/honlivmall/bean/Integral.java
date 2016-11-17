package com.honliv.honlivmall.bean;

import java.io.Serializable;

/**
 * 积分
 * @author Administrator
 *
 */
public class Integral implements Serializable {
	private String time;    //登陆时间
	private String type;    //类型
	private String typeStr;    //类型
	private int integralvalue; //积分值
	private String describe;//说明  "登录操作"
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTypeStr() {
		return typeStr;
	}
	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}
	public int getIntegralvalue() {
		return integralvalue;
	}
	public void setIntegralvalue(int integralvalue) {
		this.integralvalue = integralvalue;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
}
